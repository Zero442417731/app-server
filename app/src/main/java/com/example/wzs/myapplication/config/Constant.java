package com.example.wzs.myapplication.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hxcs-02 on 2017/7/25.
 */

public class Constant {


    public static final Long DELAY_CONNECT = 5l;
    public static final Long DELAY_HAND_SHAKE = 5l;


    public static final String HOST = "netty.nonecity.com";
    public static final int PORT = 8999;
    public static final String URL_SERVICE_CENTER = "http://app.nonecity.com:81/hxcs-server/app/";
    public static final String FLAG_MT = "1";
    public static final String FIRST_OPEN = "first_open";

    /******************************************sharedprefrence******************************************/
    public static String CONFIG_SHAREDPREFRENCE_CONFIG = "sharedprefrence_config";
    public static String CONFIG_SHAREDPREFRENCE_USER = "sharedprefrence_user";
    public static String CONFIG_SHAREDPREFRENCE_USER_PWD = "sharedprefrence_user_pwd";
    /***
     * 接口编号
     */

    public static String HXCS_JC_YHCZ = "HXCS-JC-YHCZ";
    public static String HXCS_JC_HYTJ = "HXCS-JC-HYTJ";
    public static String HXCS_JC_HCXX = "HXCS-JC-HCXX";//缓存消息
    public static String HXCS_JC_YZXX = "HXCS-JC-YZXX";
    public static String HXCS_JC_YZTG = "HXCS-JC-YZTG";
    /**
     * 用于存储sharedpreferences 变量名
     */
    public static String PUSH_MESSAGE_NUMBER = "push_message_number";

}
