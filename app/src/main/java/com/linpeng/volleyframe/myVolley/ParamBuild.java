package com.linpeng.volleyframe.myVolley;

import com.linpeng.volleyframe.utils.SystemUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * 》
 * 》Created on 15/10/24 下午7:20
 * 》
 */
public class ParamBuild {

    /**
     * 请求参数
     */
    private Map<String, String> param;

    public static ParamBuild create() {
        ParamBuild pb = new ParamBuild();
        pb.param = new HashMap<String, String>();
        return pb;
    }

    public Map<String, String> getPrarams() throws InvalidKeyException,
            NoSuchAlgorithmException, InvalidKeySpecException,
            SignatureException, NoSuchProviderException, IOException {
        param.putAll(getCommonParams());
        return param;
    }

    public ParamBuild add(String key, Object value) {
        param.put(key, String.valueOf(value));
        return this;
    }

    private static Map<String, String> commonPrarams;

    public static Map<String, String> getCommonParams() {
        if (commonPrarams == null) {
            Map<String, String> param = new HashMap<String, String>();
            param.put("v", SystemUtils.getVersoinName());
            param.put("imei", SystemUtils.getImei());
            param.put("os","6");
            commonPrarams = param;
        }
        return commonPrarams;
    }


}
