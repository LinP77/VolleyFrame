package com.linpeng.volleyframe.model;

import android.content.Context;

import com.google.gson.JsonElement;
import com.linpeng.volleyframe.utils.GsonUtils;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 》
 * 》Created on 15/10/24 下午7:10
 * 》
 */
public class NetData implements Serializable {
    private static final long serialVersionUID = 3586720079249123472L;

    public static NetData parse(String jsonString) throws Exception {
        return GsonUtils.fromJson(jsonString, NetData.class);
    }

    public int code;

    public JsonElement msg;

    public static void handleErrorCode(final Context context, NetData data) {
        switch (data.code) {
            case 200:
                // OK
                break;
            case 404:
            case 500:
            default:
                // ToastUtil.toastError(f, data);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Field[] fields = ((Object)this).getClass().getDeclaredFields();
        sb.append("{");
        for (Field f: fields) {
            f.setAccessible(true);
            sb.append("\"");
            sb.append(f.getName());
            sb.append("\":\"");
            try {
                sb.append(f.get(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
            sb.append("\", ");
        }
        sb.append("}");
        return sb.toString();
    }
}
