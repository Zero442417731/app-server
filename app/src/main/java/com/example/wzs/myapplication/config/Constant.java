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

    /*************************************** 接口编号************************************/


    public static final String BASE = "HXCS-JC-";
    //1.	HXCS-JC-YHZC  用户注册
    public static final String YHZC = BASE + "YHZC";
    //2.	HXCS-JC-FSDX  发送短信验证码
    public static final String FSDX = BASE + "FSDX";
    // 3.	HXCS-JC-DXYZ  短信验证码验证
    public static final String DXYZ = BASE + "DXYZ";
    //4.	HXCS-JC-CZMM  重置密码提交
    public static final String CZMM = BASE + "CZMM";
    //5.	HXCS-JC-YHXX  用户信息获取
    public static final String YHXX = BASE + "YHXX";
    //6.	HXCS-JC-YHXG  用户基本信息修改
    public static final String YHXG = BASE + "YHXG";
    //7.	HXCS-JC-YHTX  用户头像上传
    public static final String YHTX = BASE + "YHTX";
    //8.	HXCS-JC-QZTX  群组头像上传
    public static final String QZTX = BASE + "QZTX";
    //9.	HXCS-JC-HYLB  用户好友列表
    public static final String HYLB = BASE + "HYLB";
    //10.	HXCS-JC-YHCZ  用户查找
    public static final String YHCZ = BASE + "YHZC";
    //11.	HXCS-JC-HYTJ  用户好友添加
    public static final String HYTJ = BASE + "HYTJ";
    //12.	HXCS-JC-YZTG  好友验证通过(不通过)
    public static final String YZTG = BASE + "YZTG";
    //13.	HXCS-JC-HYSC  删除好友
    public static final String HYSC = BASE + "HYSC";
    //14.	HXCS-JC-HYNC  好友昵称设置
    public static final String HYNC = BASE + "HYNC";
    //15.	HXCS-JC-QQLB  用户好友请求列表
    public static final String QQLB = BASE + "QQLB";
    //16.	HXCS-JC-HCXX  缓存消息读取(离线消息读取)
    public static final String HCXX = BASE + "HCXX";
    //17.	HXCS-JC-HBLX  画板类型信息
    public static final String HBLX = BASE + "HBLX";
    //18.	HXCS-JC-CJHB  创建画板信息
    public static final String CJHB = BASE + "CJHB";
    //19.	HXCS-JC-HBXX  画板信息加载
    public static final String HBXX = BASE + "HBXX";
    //20.	HXCS-JC-HYQX  好友画板权限设置
    public static final String HYQX = BASE + "HYQX";
    //21.	HXCS-JC-BBJC  APP版本检测
    public static final String BBJC = BASE + "BBJC";
    //22.	HXCS-JC-YHDL  手机号登录(客户端调服务端)
    public static final String YHDL = BASE + "YHDL";
    //23.	HXCS-JC-YZXX  好友验证推送(服务端调客户端)
    public static final String YZXX = BASE + "YZXX";
    //24.	HXCS-JC-HYXX  好友消息
    public static final String HYXX = BASE + "HYXX";
    //25.	HXCS-JC-XTJC  心跳包检测(5秒)
    public static final String XTJC = BASE + "XTJC";





    public static String HXCS_JC_YHCZ = "HXCS-JC-YHCZ";

    public static String HXCS_JC_HYTJ = "HXCS-JC-HYTJ";
    public static String HXCS_JC_HCXX = "HXCS-JC-HCXX";//缓存消息
    public static String HXCS_JC_YZXX = "HXCS-JC-YZXX";
    public static String HXCS_JC_YZTG = "HXCS-JC-YZTG";
    public static String HXCS_JC_QQLB = "HXCS-JC-QQLB";


    /**
     * 用于存储sharedpreferences 变量名
     */
    public static String PUSH_MESSAGE_NUMBER = "push_message_number";

}
