package com.instant.instantgcm.net;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/21/2016.
 */
public interface GCMRegListener {

    void onGCMRegIdReceived(String gcmRegId);
    void onGCMRegIdFailure(String errorMsg);
}
