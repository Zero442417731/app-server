package com.example.wzs.myapplication.network;

import android.content.Intent;
import android.util.Log;

import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.event.EventId;
import com.example.wzs.myapplication.event.MessageEvent;
import com.example.wzs.myapplication.model.HYXX;
import com.example.wzs.myapplication.model.UserLoginInfo;
import com.example.wzs.myapplication.model.YZXX;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by hp1 on 2017-07-26.
 */

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    public static final String TAG = "MyClientHandler";
    private UserLoginInfo userLoginInfo;


    @Override

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Log.d(TAG, "Client active");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client close ");
        super.channelInactive(ctx);
    }


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        Log.d(TAG, msg);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(msg);
        String code = jsonNode.get("header").get("code").getTextValue();
        JsonNode body = jsonNode.get("body");

        //Log.d("header",header.get("code").getTextValue().toString());
        // Log.d("body",body.get("isSuccessful").getBooleanValue()+"");


        if (code.equals("HXCS-JC-YHDL")) {
            //用户登录
            if (body.get("isSuccessful").asBoolean()) {
                JsonNode resultData = jsonNode.get("body").get("resultData");
                userLoginInfo = objectMapper.readValue(resultData.toString(), UserLoginInfo.class);
                HXApplication.isLogin = userLoginInfo.isIsLogined();
                SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token", userLoginInfo.getToken());
                EventBus.getDefault().post(new MessageEvent(EventId.USERLOGIN_SUSSES, userLoginInfo.isIsLogined()));
            } else {
                EventBus.getDefault().post(new MessageEvent(EventId.USERLOGIN_ERROR, body.get("errorMsg").getTextValue()));
            }
        } else if (code.equals("HXCS-JC-XTJC")) {
            //心跳包检测
        } else if (code.equals("HXCS-JC-YZXX")) {
            //好友验证
            YZXX yzxx = objectMapper.readValue(body.toString(), YZXX.class);
            EventBus.getDefault().post(new MessageEvent<YZXX>(EventId.USER_TS,yzxx));

        } else if (code.equals("HXCS-JC-HYXX")) {
            //好友信息
            HYXX hyxx = objectMapper.readValue(body.toString(), HYXX.class);
            String friendUserId = body.get("friendUserId").getTextValue();
            //processCustomMessage(friendUserId,hyxx);
            //EventBus.getDefault().post(new MessageEvent(friendUserId, hyxx));
            Intent intent = new Intent();
            intent.putExtra(friendUserId, hyxx);
            intent.setAction(friendUserId);
            HXApplication.mContext.sendBroadcast(intent);
        }


    }


}
