package com.instantgcm.middleware.net;

import com.instantgcm.middleware.model.PushResponse;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/21/2016.
 */
public interface InstantGCMRegistrationListener {


    void onRegisterSuccess(PushResponse pushResponse);


    void onRegisterFailure(String failureMsg);

}
