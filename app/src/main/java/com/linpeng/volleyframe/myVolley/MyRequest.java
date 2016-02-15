package com.linpeng.volleyframe.myVolley;


import com.android.volley.Request;
import com.linpeng.volleyframe.model.NetData;

/**
 * 》
 * 》Created on 15/10/25 下午12:45
 * 》
 */
public class MyRequest extends RequestManager{
    /***
     *获取验证码
     */
    public KMRequest getAuthCode(String phoneNum, Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("phone", phoneNum);
        return startRequest("KMURL.authCode", param, listener);
    }

    /***
     * 登陆
     */
    public KMRequest login (String phoneNum ,String code,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("phone", phoneNum).add("code", code);
        return startRequest("KMURL.login",param, listener);
    }

    /***
     *获取验证码
     */
    public KMRequest isLogin(Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create();
        return startRequest("KMURL.isLogin", param, listener);
    }

    /***
     * 首页
     */
    public KMRequest home (Listener<NetData> listener) {
        return startRequest(Request.Method.GET,"KMURL.home", null,listener);
    }

    /***
     * 查询默认爱车
     */
    public KMRequest qureyDefault (Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("id", "");
        return startRequest(Request.Method.GET,"KMURL.query_default", param,listener);
    }

    /***
     *查询服务详情
     */
    public KMRequest getServeDetails (String modelId, String serviceCode,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("modelId", modelId).add("serviceCode",serviceCode);
        return startRequest(Request.Method.GET,"KMURL.service_detail",param, listener);
    }


    /***
     *立即预约
     */
    public KMRequest bespeakCreate (String memberModelId, String serviceCode,String companyId,String serviceTime,String remark,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("memberModelId", memberModelId).add("serviceCode",serviceCode).add("companyId", companyId).add("serviceTime", serviceTime).add("remark",remark);
        return startRequest(Request.Method.POST,"KMURL.bespeak_create",param, listener);
    }



    /**
     * 预约单消息（个人中心-消息使用）
     */
    public KMRequest getMessage ( Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("pageIndex", 0).add("pageSize",20);
        return startRequest(Request.Method.GET,"KMURL.message",param, listener);
    }

    /***
     * 反馈信息
     */
    public KMRequest backInfo (String content,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("content", content);
        return startRequest(Request.Method.GET,"KMURL.backInfo",param, listener);
    }

    /***
     *查询车系，排量+年份
     */
    public KMRequest getCarDetails (String id,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("id", id);
        return startRequest(Request.Method.GET, "KMURL.carDetails",param, listener);
    }

    /***
     *获取车辆所有品牌
     */
    public KMRequest getAllCarList (Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create();
        return startRequest(Request.Method.GET, "KMURL.allCar",param, listener);
    }

    /***
     *添加我的爱车
     */
    public KMRequest saveMyCar (String modelId,String modelGroupName,String region,String license, String mileage, String buyDate,String vin,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("modelId", modelId).add("modelGroupName", modelGroupName)
                .add("region", region).add("license", license).add("mileage", mileage).add("buyDate", buyDate).add("vin", vin);
        return startRequest("KMURL.saveMyCar",param, listener);
    }


    /***
     *行驶证扫描
     */
    public KMRequest drivingLicense (String file,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("file", file);
        return startRequest("KMURL.drivingLicense",param, listener);
    }

    /***
     *4s会员卡上传照片
     */
    public KMRequest imageUpload (String file,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("file",file );
        return startRequest("KMURL.imageUpload",param, listener);
    }

    /***
     *招行会员卡查看
     */
    public KMRequest cardInfo (Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create();
        return startRequest(Request.Method.GET,"KMURL.cardInfo"+"?date="+Math.random(),param, listener);
    }


    /***
     *添加我的爱车
     */
    public KMRequest memberModel (String license,String vehicleType,String name,String address, String model, String useCharacte,String engineNo,String vin,String registerDate,String issueDate,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("license", license).add("vehicleType", vehicleType)
                .add("name", name).add("address", address).add("model", model).add("useCharacte", useCharacte)
                .add("engineNo", engineNo).add("vin",vin).add("registerDate", registerDate).add("issueDate", issueDate);
        return startRequest("KMURL.memberModel",param, listener);
    }


    /***
     *红包及优惠券
     */
    public KMRequest memberTicket (Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create();
        return startRequest(Request.Method.GET, "KMURL.memberTicket",param, listener);
    }

    /***
     *预约单详情
     */
    public KMRequest bespeakView (String bespeakSn, Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("bespeakSn", bespeakSn);
        return startRequest(Request.Method.GET, "KMURL.bespeakView",param, listener);
    }

    /***
     *查询会员信息
     */
    public KMRequest memberShow (Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create();
        return startRequest(Request.Method.GET, "KMURL.memberShow",param, listener);
    }


    /***
     *修改会员信息
     */
    public KMRequest memberUpdate (String name,String sex, String ico,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("name", name).add("sex",sex).add("ico", ico);
        return startRequest("KMURL.memberUpdate",param, listener);
    }


    /***
     *删除车辆
     */
    public KMRequest deleteCar (String id,String defaultFlag,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("id", id).add("defaultFlag",defaultFlag);
        return startRequest("KMURL.deleteCar",param, listener);
    }

    /***
     *编辑车辆
     */
    public KMRequest updateCar (String id,String modelId,String modelGroupName,String region,String license,String mileage,String buyDate, String vin,Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("id", id).add("modelId", modelId).add("modelGroupName", modelGroupName).add("region", region)
                .add("license", license).add("mileage", mileage).add("buyDate", buyDate).add("vin", vin);
        return startRequest("KMURL.updateCar",param, listener);
    }

    /**
     *定位
     */
    public KMRequest getLocation (String longitude,String latitude , Listener<NetData> listener) {
        ParamBuild param = ParamBuild.create().add("longitude", longitude).add("latitude",latitude);
        return startRequest(Request.Method.GET,"KMURL.location",param, listener);
    }

}
