package com.instant.instantgcm;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.instant.instantgcm.net.GCMDataInterface;
import com.instant.instantgcm.net.GCMRegListener;
import com.instant.instantgcm.services.InstantGCMPreferences;
import com.instant.instantgcm.services.RegistrationIntentService;
import com.instant.instantgcm.utils.GCMRegisterUtils;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/20/2016.
 */
public class InstantGCMClient {
    private static boolean isReceiverRegistered;

    private static GCMRegListener mGCMRegListener;

    public static void initialise(Context context , GCMDataInterface dataInterface , final GCMRegListener gcmRegListener){
        mGCMRegListener = gcmRegListener;
        Intent intent = new Intent(context , RegistrationIntentService.class);
        intent.putExtra(InstantGCMPreferences.PROJECT_ID , dataInterface.getProjectId());
        context.startService(intent);

        registerReceiver(context);

    }

    private static BroadcastReceiver mGCMTokenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(InstantGCMPreferences.REGISTRATION_COMPLETE)){
                if(!TextUtils.isEmpty(GCMRegisterUtils.getGCMRegistrationId(context))){
                    mGCMRegListener.onGCMRegIdReceived(GCMRegisterUtils.getGCMRegistrationId(context));
                }else
                    mGCMRegListener.onGCMRegIdFailure(context.getResources().getString(R.string.reg_receive_error));
            }


        }

    };

    private static void registerReceiver(Context context){
        if(!isReceiverRegistered) {

            IntentFilter filter = new IntentFilter();
            filter.addAction(InstantGCMPreferences.REGISTRATION_COMPLETE);

            LocalBroadcastManager.getInstance(context).registerReceiver(mGCMTokenReceiver,
                    filter);

            isReceiverRegistered = true;
        }
    }

}
