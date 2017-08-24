package com.example.wzs.myapplication.utils;

import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.config.Constant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hxcs-02 on 2017/8/23.
 */

public class UserLoginUtils {
    public static String setLogin() {

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject.put("code", "HXCS-JC-YHDL");
            jsonObject1.put("username", SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER_PWD,"name"));
            jsonObject1.put("password", MD5Util.getMD5Str(SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER_PWD,"pwd")));
            jsonObject1.put("deviceId", HXApplication.PHONE_ID);
            jsonObject1.put("ostype", "1");
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }
    public static String setXTJC() {

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject.put("code", "HXCS-JC-YHDL");
            jsonObject1.put("username", SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER_PWD,"name"));
            jsonObject1.put("password", MD5Util.getMD5Str(SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER_PWD,"pwd")));
            jsonObject1.put("deviceId", HXApplication.PHONE_ID);
            jsonObject1.put("ostype", "1");
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }
}
