package com.instantgcm.middleware.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/14/2016.
 */
public class PushResponse {

    @SerializedName("status")
    private int mStatus;

    @SerializedName("message")
    private String mMessage;

    public PushResponse(int status, String message) {
        mStatus = status;
        mMessage = message;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
