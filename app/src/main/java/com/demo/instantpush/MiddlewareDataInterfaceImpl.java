package com.demo.instantpush;


import com.instantgcm.middleware.net.MiddlewareDataInterface;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/18/2016.
 */
public class MiddlewareDataInterfaceImpl implements MiddlewareDataInterface {

    @Override
    public String getBaseURL() {
        return Constants.BASE_URL;
    }

    @Override
    public String getDeviceId() {
        return Constants.DEVICE_ID;
    }

    @Override
    public String getProjectId() {
        return Constants.PROJECT_ID;
    }

    @Override
    public boolean isLoggingEnabled() {
        return BuildConfig.IS_LOGGING_ENABLE;
    }
}
