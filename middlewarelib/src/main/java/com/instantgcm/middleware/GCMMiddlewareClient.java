package com.instantgcm.middleware;

import android.content.Context;

import com.instant.instantgcm.InstantGCMClient;
import com.instant.instantgcm.net.GCMRegListener;
import com.instantgcm.middleware.model.PushPayload;
import com.instantgcm.middleware.model.UpdateFavoriteRequest;
import com.instantgcm.middleware.net.GCMDataInterfaceImpl;
import com.instantgcm.middleware.net.InstantGCMDeregistrationListener;
import com.instantgcm.middleware.net.InstantGCMPostFavListener;
import com.instantgcm.middleware.net.InstantGCMRegistrationListener;
import com.instantgcm.middleware.net.MiddlewareDBClient;
import com.instantgcm.middleware.net.MiddlewareDataInterface;
import com.instantgcm.middleware.util.Constants;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/21/2016.
 */
public class GCMMiddlewareClient {

    private static MiddlewareDBClient dbClient;
    private static String sDeviceId;
    private static InstantGCMRegistrationListener sInstantGCMRegistrationListener;

    public static void register(Context context , MiddlewareDataInterface middlewareDataInterface,
                                InstantGCMRegistrationListener instantGCMRegistrationListener){
        if(dbClient == null){
            dbClient = getPushMiddleWareClient(middlewareDataInterface);
        }
        sDeviceId = middlewareDataInterface.getDeviceId();
        sInstantGCMRegistrationListener = instantGCMRegistrationListener;
        InstantGCMClient.initialise(context, new GCMDataInterfaceImpl(middlewareDataInterface.getProjectId()), mGCMRegListener);
    }

    public static MiddlewareDBClient getPushMiddleWareClient(MiddlewareDataInterface middlewareDataInterface) {
        return MiddlewareDBClient.getInstance(middlewareDataInterface);
    }

    private static GCMRegListener mGCMRegListener = new GCMRegListener() {
        @Override
        public void onGCMRegIdReceived(String gcmRegId) {
           dbClient.registerRequest(new PushPayload(sDeviceId , Constants.PLATFORM , gcmRegId), sInstantGCMRegistrationListener);
        }

        @Override
        public void onGCMRegIdFailure(String errorMsg) {
            sInstantGCMRegistrationListener.onRegisterFailure(errorMsg);
        }

    };


    public static void deRegister(Context context, MiddlewareDataInterface middlewareDataInterface,
                                  InstantGCMDeregistrationListener instantGCMRegistrationListener) {
        if (dbClient == null) {
            dbClient = getPushMiddleWareClient(middlewareDataInterface);
        }
        dbClient.deRegisterRequest(new PushPayload(middlewareDataInterface.getDeviceId(), Constants.PLATFORM), instantGCMRegistrationListener);
    }

    /*
    requestBean forms the runner list
     */
    public static void postFavorites(Context context, UpdateFavoriteRequest requestBean,
                                     MiddlewareDataInterface middlewareDataInterface,
                                     InstantGCMPostFavListener instantGCMPostFavListener) {
        if (dbClient == null) {
            dbClient = getPushMiddleWareClient(middlewareDataInterface);
        }

        requestBean.getFavorites().setLanguage("en");
        requestBean.getFavorites().setPlatform(Constants.PLATFORM);
        requestBean.getFavorites().setUid(middlewareDataInterface.getDeviceId());

        dbClient.postFavoriteRequest(requestBean, instantGCMPostFavListener);
    }
}
