package com.instantgcm.middleware.net;

import com.instant.instantgcm.net.GCMDataInterface;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/22/2016.
 */
public class GCMDataInterfaceImpl implements GCMDataInterface {

    private String mProjectId;

    public GCMDataInterfaceImpl(String projectId) {
        mProjectId = projectId;
    }

    @Override
    public String getProjectId() {
        return mProjectId;
    }
}
