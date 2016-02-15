package com.linpeng.volleyframe.myVolley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.linpeng.volleyframe.base.KMApplication;
import com.linpeng.volleyframe.model.NetData;

/**
 * 》
 * 》Created on 15/10/25 上午10:17
 * 》
 */
public class RequestManager {
    public interface Listener<T> {
        void onResponse(Request<T> request, T data);

        void onError(Request<T> request, Exception error);
    }

    public class ListenerWrap<T> implements RequestListener <T>{

        private Listener<T> listener;

        private Request<T> request;

        public ListenerWrap(Listener<T> listener) {
            this.listener = listener;
        }

        @Override
        public void setRequest(Request<T> request) {
            this.request = request;
        }

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            listener.onError(request,volleyError);
        }

        @Override
        public void onResponse(T response) {
            listener.onResponse(request,response);
        }
    }
    private static RequestQueue requestQueue;

    public static RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(KMApplication.getAppContext());
        }
        return requestQueue;
    }

    public void addToQueue(Request<?> request) {
        getRequestQueue().add(request);
    }

    protected KMRequest startRequest(String url, ParamBuild param,
                                     Listener<NetData> listener) {
        return startRequest(Request.Method.POST, url, param, listener);
    }

    public KMRequest startRequest(int method, String url,ParamBuild param, Listener<NetData> listener){
        ListenerWrap<NetData> wrap = new ListenerWrap<NetData>(listener);
        KMRequest request = new KMRequest(method,url,param,wrap);
        wrap.setRequest(request);
        addToQueue(request);
        return request;
    }

    public void stopAll() {
        if (requestQueue != null) {
            requestQueue.stop();
        }
    }

    public void cancelAll() {
        if (requestQueue != null) {
            requestQueue.cancelAll(new RequestQueue.RequestFilter() {

                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });
        }
    }

}
