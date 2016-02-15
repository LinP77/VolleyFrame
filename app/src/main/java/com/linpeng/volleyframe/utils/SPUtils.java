package com.linpeng.volleyframe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.linpeng.volleyframe.base.KMApplication;


/**
 * 》
 * 》Created on 15/10/25 下午12:29
 * 》
 */
public class SPUtils {

    public static final String CONFIG = "config";
    public static SharedPreferences sp;

    public static SharedPreferences getSP(){
        if (sp == null)
            sp = KMApplication.getAppContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        return sp;
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        getSP().edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getSP().getBoolean(key, defValue);
    }

    public static void saveStringData(Context context,String key,String value){
        getSP().edit().putString(key, value).commit();
    }

    public static String getStringData(Context context,String key,String defValue){
        return getSP().getString(key, defValue);
    }

    public void clear() {
        getSP().edit().clear().commit();
    }
}
