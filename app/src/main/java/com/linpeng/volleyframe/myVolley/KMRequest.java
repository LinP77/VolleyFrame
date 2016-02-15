package com.linpeng.volleyframe.myVolley;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.linpeng.volleyframe.model.NetData;
import com.linpeng.volleyframe.utils.UserUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 》
 * 》Created on 15/10/23 下午1:43
 * 》
 */
public class KMRequest extends Request<NetData> {

    /**
     * 请求头
     */
    private static Map<String, String> mHeader = new HashMap<String, String>();

    private final ParamBuild paramBuild;
    private final RequestListener<NetData> listener;

    public KMRequest(String url,ParamBuild paramBuild, RequestListener<NetData> listener) {
        this(Method.POST, url, paramBuild, listener);
    }

    public KMRequest(int method, String url,ParamBuild paramBuild, RequestListener<NetData> listener) {
        super(method, url, listener);
        this.paramBuild = paramBuild;
        this.listener = listener;
        setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 2, 1.0f));
    }



    @Override
    protected Response<NetData> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            Map<String, String> headers = networkResponse.headers;
            //LogUtils.e("HEADERS", GsonUtils.objectToString(headers));
            UserUtils.saveToken(headers);
            String jsonString = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            NetData data = NetData.parse(jsonString);
            return Response.success(data,
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(NetData netData) {
        listener.onResponse(netData);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        UserUtils.addToken(headers);
        headers.putAll(super.getHeaders());
        return headers;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        try {
            if (paramBuild == null) {
                return null;
            }
            Map<String, String> param = paramBuild.getPrarams();
            return param;
        } catch (Exception e) {
            throw new AuthFailureError("session out of date");
        }
    }

    /**
     * 拼接参数
     * http://7pkm.com/common/sendCode.itf?os=4&imei=*****&v=1.0&phone=150101
     *
     */
    @Override
    public String getUrl() {
        String ps = encodeParameters(paramBuild, getParamsEncoding());
        switch (getMethod()) {
            case Method.GET:
            case Method.DELETE:
                return TextUtils.isEmpty(ps) ? super.getUrl() : String.format(
                        "%s?%s", super.getUrl(), ps);
        }
        return super.getUrl();
    }

    private String encodeParameters(ParamBuild pb, String paramsEncoding) {
        Map<String, String> params;
        try {
            params = pb.getPrarams();
        } catch (Exception e) {
            return null;
        }
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry: params.entrySet()) {
                if (TextUtils.isEmpty(entry.getKey())
                        || TextUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                encodedParams.append(URLEncoder.encode(entry.getKey(),
                        paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(),
                        paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: "
                    + paramsEncoding, uee);
        }
    }
}
