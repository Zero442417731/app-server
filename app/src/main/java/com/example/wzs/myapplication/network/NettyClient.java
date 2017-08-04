package com.example.wzs.myapplication.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.ToastUtil;
import com.lljjcoder.citylist.Toast.ToastUtils;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import com.example.wzs.myapplication.config.Constant;
/**
 * Created by hxcs-02 on 2017/8/2.
 */

public class NettyClient implements INettyClient {
    private final String TAG = NettyClient.class.getSimpleName();
    private static NettyClient mInstance;
    private Bootstrap bootstrap;
    private Channel channel;
    private String host;
    private int port;
    private HandlerThread workThread = null;
    private Handler mWorkHandler = null;
    private NettyClientHandler nettyClientHandler;
    private final String ACTION_SEND_TYPE = "action_send_type";
    private final String ACTION_SEND_MSG = "action_send_msg";
    private final int MESSAGE_INIT = 0x1;
    private final int MESSAGE_CONNECT = 0x2;
    private final int MESSAGE_SEND = 0x3;
    private Handler.Callback mWorkHandlerCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_INIT: {
                    NioEventLoopGroup group = new NioEventLoopGroup();
                    bootstrap = new Bootstrap();
                    bootstrap.channel(NioSocketChannel.class);
                    bootstrap.group(group);
                    bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new LineBasedFrameDecoder(Integer.MAX_VALUE));
                            pipeline.addLast(nettyClientHandler);
                        }
                    });
                    break;
                }
                case MESSAGE_CONNECT: {
                    try {
                        if (TextUtils.isEmpty(host) || port == 0) {
                            Exception exception = new Exception("Netty host | port is invalid");
                            throw exception;
                        }
                        channel = bootstrap.connect(new InetSocketAddress(host, port)).sync().channel();
                    } catch (Exception e) {
                        ToastUtil.showToast("服务器连接失败");
                        e.printStackTrace();
                        sendReconnectMessage();
                    }
                    break;
                }
                case MESSAGE_SEND: {
                    String sendMsg = msg.getData().getString(ACTION_SEND_MSG);
                    int mt = msg.getData().getInt(ACTION_SEND_TYPE);
                    try {
                        if (channel != null && channel.isOpen()) {
                            channel.writeAndFlush(constructMessage(sendMsg)).sync();
                            Log.d(TAG, "send succeed " + constructMessage(sendMsg));
                        } else {
                            throw new Exception("channel is null | closed");
                        }
                    } catch (Exception e) {
                        LogUtil.e(TAG, "send failed " + e.getMessage());
                        sendReconnectMessage();
                        e.printStackTrace();
                    } finally {

                            sendMessage(mt, sendMsg, Constant.DELAY_HAND_SHAKE);
                    }
                    break;
                }
            }
            return true;
        }
    };

    private NettyClient() {
        init();
    }

    public synchronized static NettyClient getInstance() {
        if (mInstance == null) mInstance = new NettyClient();
        return mInstance;
    }

    private void init() {
        workThread = new HandlerThread(NettyClient.class.getName());
        workThread.start();
        mWorkHandler = new Handler(workThread.getLooper(), mWorkHandlerCallback);
        nettyClientHandler = new NettyClientHandler();
        nettyClientHandler.setConnectStatusListener(new OnConnectStatusListener() {
            @Override
            public void onDisconnected() {
                sendReconnectMessage();
            }
        });
        mWorkHandler.sendEmptyMessage(MESSAGE_INIT);
    }

    @Override
    public void connect(String host, int port) {
        this.host = host;
        this.port = port;
        mWorkHandler.sendEmptyMessage(MESSAGE_CONNECT);
    }

    @Override
    public void addDataReceiveListener(OnDataReceiveListener listener) {
        if (nettyClientHandler != null) nettyClientHandler.addDataReceiveListener(listener);
    }

    private void sendReconnectMessage() {
        mWorkHandler.sendEmptyMessageDelayed(MESSAGE_CONNECT, Constant.DELAY_CONNECT);
    }

    @Override
    public void sendMessage(int mt, String msg, long delayed) {
        if (TextUtils.isEmpty(msg)) return;
        Message message = new Message();
        Bundle bundle = new Bundle();
        message.what = MESSAGE_SEND;
        bundle.putString(ACTION_SEND_MSG, msg);
        bundle.putInt(ACTION_SEND_TYPE, mt);
        message.setData(bundle);
        mWorkHandler.sendMessageDelayed(message, delayed);
    }

    private String constructMessage(String json) {
        String message = null; //与后台协议好，如何设置校验部分，然后和json一起发给服务器

        return message;
    }
}



