package com.example.wzs.myapplication.network;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.event.MessageEvent;
import com.example.wzs.myapplication.model.HYXX;
import com.example.wzs.myapplication.model.UserLoginInfo;
import com.example.wzs.myapplication.model.YZXX;
import com.example.wzs.myapplication.utils.JsonBinder;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.google.gson.Gson;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by hp1 on 2017-07-26.
 */

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    public static final String TAG = "MyClientHandler";

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
        JsonNode header = jsonNode.get("header");
        JsonNode body = jsonNode.get("body");


        if (header.getTextValue().equals("HXCS-JC-YHDL") && body.get("isSuccessful").asBoolean()) {
            JsonNode resultData = jsonNode.get("body").get("resultData");
            UserLoginInfo userLoginInfo = objectMapper.readValue(resultData.toString(), UserLoginInfo.class);
            HXApplication.isLogin = userLoginInfo.isIsLogined();
            SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token", userLoginInfo.getToken());
        } else if (header.getTextValue().equals("HXCS-JC-XTJC")) {
            //心跳包检测
        } else if (header.getTextValue().equals("HXCS-JC-YZXX")) {
            //好友验证
            objectMapper.readValue(body.toString(), YZXX.class);


        } else if (header.getTextValue().equals("HXCS-JC-HYXX")) {
            //好友信息
            HYXX hyxx = objectMapper.readValue(body.toString(), HYXX.class);
            String friendUserId = body.get("friendUserId").getTextValue();
            //processCustomMessage(friendUserId,hyxx);
            //EventBus.getDefault().post(new MessageEvent(friendUserId, hyxx));
            Intent intent = new Intent();
            intent.putExtra(friendUserId,hyxx);
            intent.setAction(friendUserId);
            HXApplication.mContext.sendBroadcast(intent);
        }
        LogUtil.e("header", header.toString());
        LogUtil.e("body", body.toString());

    }


    private void processCustomMessage(String msgID, Object messageContent ) {
        EventBus.getDefault().post(new MessageEvent(msgID, messageContent));
    }

}

