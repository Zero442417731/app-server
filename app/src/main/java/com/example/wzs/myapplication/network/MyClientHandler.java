package com.example.wzs.myapplication.network;

import android.util.Log;


import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.UserLoginUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by hp1 on 2017-07-26.
 */

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    public static final String TAG = "MyClientHandler";
    private int UNCONNECT_NUM = 0;

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
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);

    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        LogUtil.f("MSG----", msg);
        DispatchMessage.getInstance().getMsg(msg);
    }

}

