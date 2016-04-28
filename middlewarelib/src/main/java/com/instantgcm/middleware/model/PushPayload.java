package com.instantgcm.middleware.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/14/2016.
 */
public class PushPayload {

    @SerializedName("uid")
    private String mUid;

    @SerializedName("devicetype")
    private String mDeviceType;

    @SerializedName("devicetoken")
    private String mDeviceToken;

    public PushPayload(String uid, String deviceType, String deviceToken) {
        mUid = uid;
        mDeviceType = deviceType;
        mDeviceToken = deviceToken;
    }

    public PushPayload(String uid, String deviceType) {
        mUid = uid;
        mDeviceType = deviceType;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }

    public String getDeviceType() {
        return mDeviceType;
    }

    public void setDeviceType(String deviceType) {
        mDeviceType = deviceType;
    }

    public String getDeviceToken() {
        return mDeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        mDeviceToken = deviceToken;
    }
}
