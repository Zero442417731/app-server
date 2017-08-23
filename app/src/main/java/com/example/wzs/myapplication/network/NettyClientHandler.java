package com.example.wzs.myapplication.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.utils.LogUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by hxcs-02 on 2017/8/2.
 */

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private NettyClientBootstrap nettyClient = null;

    public NettyClientHandler(NettyClientBootstrap nettyClient) {
        super();
        this.nettyClient = nettyClient;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String strMsg = (String) msg;
        Log.d("回复的消息：", strMsg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Log.d("ClientHandler", "-------重连回调------");
        nettyClient.setConnectState(NettyClientBootstrap.DISCONNECTION);
        nettyClient.connect();
       // NettyClientBootstrap.getInstance().
        super.channelInactive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Log.d("NettyClientHandl", "registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Log.d("NettyClientHandler", "=====连接成功回调=====");
        nettyClient.setConnectState(NettyClientBootstrap.CONNECTED);
        super.channelActive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Log.d("NettyClientHandl", "网络异常!");
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}



