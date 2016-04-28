package com.instantgcm.middleware.model;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sneha Khadatare : 587823
 * on 4/15/2016.
 */

public class Favorites {

    private String platform;
    private String uid;
    private List<Chipcode> chipcode = new ArrayList<Chipcode>();
    private String language;

    public String getPlatform() {
        return platform;
    }


    public void setPlatform(String platform) {
        this.platform = platform;
    }


    public String getUid() {
        return uid;
    }


    public void setUid(String uid) {
        this.uid = uid;
    }


    public List<Chipcode> getChipcode() {
        return chipcode;
    }

    public void setChipcode(List<Chipcode> chipcode) {
        this.chipcode = chipcode;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
