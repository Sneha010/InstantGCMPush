package com.instantgcm.middleware.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/14/2016.
 */
public class PushPayload {

    @SerializedName("uid")
    private String mUid;


    @SerializedName("platform")
    private String mPlatform;

    @SerializedName("devicetoken")
    private String mDeviceToken;

    public PushPayload(String uid, String platform) {
        mUid = uid;
        mPlatform = platform;
    }


    public PushPayload(String uid, String platform, String deviceToken) {
        mUid = uid;
        mPlatform = platform;
        mDeviceToken = deviceToken;
    }

    public String getPlatform() {
        return mPlatform;
    }

    public void setPlatform(String platform) {
        mPlatform = platform;
    }


    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }


    public String getDeviceToken() {
        return mDeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        mDeviceToken = deviceToken;
    }
}
