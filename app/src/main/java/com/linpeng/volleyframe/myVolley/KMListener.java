package com.linpeng.volleyframe.myVolley;

import com.android.volley.Request;
import com.linpeng.volleyframe.model.NetData;

/**
 * 》
 * 》Created on 15/10/27 下午4:34
 * 》
 */
public abstract class KMListener implements RequestManager.Listener<NetData>{
    public KMListener() {
    }

    public abstract void onSucceed(Request<NetData> request,
                                 NetData data);

    public abstract void onFailed(Request<NetData> request,
                                NetData data);

    public abstract void requestError(Request<NetData> request, Exception error);
    /*{
        NetData.handleErrorCode(KMApplication.getAppContext(), data);
    }*/

    @Override
    public void onResponse(Request<NetData> request, NetData data) {
        try {
            if (data == null) {

            } else if (data.code == 0000) {
                onSucceed(request, data);
            } else {
                onFailed(request, data);
            }
        }finally {

        }
    }

    @Override
    public void onError(Request<NetData> request, Exception error) {
        requestError(request, error);
    }
}
