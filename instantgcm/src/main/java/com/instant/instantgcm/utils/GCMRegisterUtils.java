package com.instant.instantgcm.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/21/2016.
 */
public class GCMRegisterUtils {

    private static final String PREF_NAME = "PREF_NAME";
    private static final String REG_KEY = "REG_KEY";
    private static final String APP_VERSION = "APP_VERSION";
    private static final String IS_REGISTERED_ON_SERVER = "IS_REGISTERED_ON_SERVER";

    private static SharedPreferences getPreferences(Context context){
        SharedPreferences prefs = context.getSharedPreferences(
                         PREF_NAME, Context.MODE_PRIVATE);
        return prefs;
    }

    public static void clearGCMRegId(Context context){
        saveGCMRegistrationId(context , "");
    }

    public static String getGCMRegistrationId(Context context){
        return getPreferences(context).getString(REG_KEY , "");
    }

    public static void saveGCMRegistrationId(Context context,
                                            String data) {

        getPreferences(context).edit().putString(REG_KEY, data).commit();
    }

    public static void saveAppVersion(Context context) {

        getPreferences(context).edit().putString(APP_VERSION, getCurrentAppVersion(context)).commit();
    }

    public static String getSavedAppVersion(Context context){
        return getPreferences(context).getString(APP_VERSION , "");
    }



    public static String getCurrentAppVersion(Context context){
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void setRegisteredOnServer(Context context ,boolean isRegistered){
        getPreferences(context).edit().putBoolean(IS_REGISTERED_ON_SERVER , isRegistered);
    }

    public static boolean isRegisteredOnServer(Context context){
        return getPreferences(context).getBoolean(IS_REGISTERED_ON_SERVER , false);
    }


}
