package com.linpeng.volleyframe.utils;

import android.text.TextUtils;

import com.linpeng.volleyframe.base.KMApplication;

import java.util.Map;

/**
 * 》
 * 》Created on 15/11/2 下午1:55
 * 》
 */
public class UserUtils {
    public static final String TOKEN = "Token";
    public static void saveToken(Map<String, String> headers) {
        if (headers == null) {
            return;
        }
        String token = headers.get(TOKEN);
        if (!TextUtils.isEmpty(token)) {
            SPUtils.saveStringData(KMApplication.getAppContext(),TOKEN,token);
        }
    }

    public static void addToken(Map<String, String> headers) {
        if (headers == null) {
            return;
        }
        String token = SPUtils.getStringData(KMApplication.getAppContext(),TOKEN,"");
        if (!TextUtils.isEmpty(token)) {
            headers.put(TOKEN, token);
        }
    }
}
