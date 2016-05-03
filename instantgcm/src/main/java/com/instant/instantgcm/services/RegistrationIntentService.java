/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.instant.instantgcm.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.instant.instantgcm.utils.GCMRegisterUtils;

import java.io.IOException;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String projectId = null;
        String tokenValue = null;

        if(intent.getExtras()!=null){
            projectId = intent.getStringExtra(InstantGCMPreferences.PROJECT_ID);
        }

        try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            // [START get_token]
            if(TextUtils.isEmpty(GCMRegisterUtils.getGCMRegistrationId(getApplicationContext())) ||
                    !GCMRegisterUtils.getSavedAppVersion(getApplicationContext()).equals(GCMRegisterUtils.getCurrentAppVersion(getApplicationContext()))){
                InstanceID instanceID = InstanceID.getInstance(this);
                tokenValue = instanceID.getToken(projectId,
                        GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                GCMRegisterUtils.saveAppVersion(getApplicationContext());
            }else{
                tokenValue = GCMRegisterUtils.getGCMRegistrationId(getApplicationContext());
            }

            // [END get_token]
            Log.i(TAG, "GCM Registration Token: " + tokenValue);

            GCMRegisterUtils.saveGCMRegistrationId(getApplicationContext() , tokenValue);
            // Subscribe to topic channels
            subscribeTopics(tokenValue);

        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);

        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(InstantGCMPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }



    /**
     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
     *
     * @param token GCM token
     * @throws IOException if unable to reach the GCM PubSub service
     */
    // [START subscribe_topics]
    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
    // [END subscribe_topics]

}
