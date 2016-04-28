package com.instantgcm.middleware.net;

import com.instantgcm.middleware.model.PushResponse;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/21/2016.
 */
public interface InstantGCMDeregistrationListener {

    void onDeregisterSuccess(PushResponse pushResponse);

    void onDeregisterFailure(String failureMsg);
}
