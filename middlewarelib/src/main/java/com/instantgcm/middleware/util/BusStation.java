package com.instantgcm.middleware.util;

import com.squareup.otto.Bus;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/14/2016.
 */
public class BusStation {

    private static final Bus bus = new Bus();

    public static Bus getBus(){
        return bus;
    }
}
