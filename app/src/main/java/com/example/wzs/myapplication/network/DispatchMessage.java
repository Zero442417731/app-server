package com.example.wzs.myapplication.network;

import android.util.Log;

import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.event.EventId;
import com.example.wzs.myapplication.event.MessageEvent;
import com.example.wzs.myapplication.model.UserLoginInfo;
import com.example.wzs.myapplication.model.XTJC;
import com.example.wzs.myapplication.model.friendMsg.HYXX;
import com.example.wzs.myapplication.model.friendMsg.YZXX;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.example.wzs.myapplication.utils.UserLoginUtils;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by hxcs-02 on 2017/8/24.
 */

public class DispatchMessage {

    private UserLoginInfo userLoginInfo;

    private static DispatchMessage dispathMessage = null;

    public static DispatchMessage getInstance() {
        if (dispathMessage == null) {
            dispathMessage = new DispatchMessage();
        }
        return dispathMessage;
    }

    private DispatchMessage() {

    }

    public void getMsg(String msg) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(msg);
        String code = jsonNode.get("header").get("code").getTextValue();
        JsonNode body = jsonNode.get("body");


        switch (code) {
            case Constant.YHDL:
                //用户登录
                if (body.get("isSuccessful").asBoolean()) {
                    JsonNode resultData = jsonNode.get("body").get("resultData");
                    userLoginInfo = objectMapper.readValue(resultData.toString(), UserLoginInfo.class);
                    HXApplication.isLogin = userLoginInfo.isIsLogined();

                    SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token", userLoginInfo.getToken());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            while (true) {
                                try {
                                    ClientUtil.sendMessage("{\"header\" : {\"code\" : \"HXCS-JC-XTJC\"},\"body\" : {\" heartState\" : \"1\"}}");
                                    TimeUnit.SECONDS.sleep(10);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                    setEventBus(EventId.USERLOGIN_SUSSES, userLoginInfo.isIsLogined());

                } else {
                    setEventBus(EventId.USERLOGIN_ERROR, body.get("errorMsg").getTextValue());
                }
                break;
            case Constant.YZXX:
                YZXX yzxx = objectMapper.readValue(body.toString(), YZXX.class);
                setEventBus(EventId.USER_TS, yzxx);
                Log.d("body", yzxx.getFriendId());
                break;
            case Constant.HYXX:
                HYXX hyxx = objectMapper.readValue(body.toString(), HYXX.class);
                LogUtil.e("好友消息----", "------------" + hyxx.toString());
                setEventBus(hyxx.getFriendUserId(), hyxx);
                break;
            case Constant.XTJC:
                LogUtil.e("心跳----", body.toString());
                XTJC test = objectMapper.readValue(body.toString(), XTJC.class);
                setEventBus(EventId.TEST, test);
                break;
        }

    }

    private <T> void setEventBus(String ID, T msg) {
        EventBus.getDefault().post(new MessageEvent<T>(ID, msg));

    }
}
