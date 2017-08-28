package com.example.wzs.myapplication.event;

/**
 * Created by hxcs-02 on 2017/8/11.
 */

public class EventId {

    //测试
    public static final String TEST = "001";

    //登陆验证
    public static final String UDER_NAME = "100";
    public static final String UDER_PWD = "101";
    //登陆验证 成功
    public static final String USERLOGIN_SUSSES = "200";
    //登陆验证 失败
    public static final String USERLOGIN_ERROR = "201";

    //好友验证推送
    public static final String USER_TS = "300";

    //好友刷新
    public static final String FRIENDSREFRESH = "400";

}
