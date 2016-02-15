package com.linpeng.volleyframe.myVolley;

import com.android.volley.Request;
import com.android.volley.Response;

/**
 * 》
 * 》Created on 15/10/25 上午11:34
 * 》
 */
public interface RequestListener <T> extends Response.Listener<T>,
        Response.ErrorListener {
    void setRequest(Request<T> request);
}
