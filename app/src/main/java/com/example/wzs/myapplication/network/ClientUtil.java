package com.example.wzs.myapplication.network;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.UserLoginUtils;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;


public class ClientUtil {
    private static final String TAG = "MainActivity";
    private static Context context;
    public static int MSG_REC = 0xabc;
    private static NioEventLoopGroup group;
    private static SocketChannel socketChannel;
    private ChannelFuture cf;
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_REC) {

                Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };
    private static Bootstrap bootstrap;


    // 连接到Socket服务端
    public static void connected() {
        new Thread() {
            @Override
            public void run() {
                loadnetty();
            }
        }.start();
    }

    private static void loadnetty() {
        group = new NioEventLoopGroup();
        try {
            // Client服务启动器 3.x的ClientBootstrap
            // 改为Bootstrap，且构造函数变化很大，这里用无参构造。
            bootstrap = new Bootstrap();
            // 指定EventLoopGroup
            bootstrap.group(group);
            // 指定channel类型
            bootstrap.channel(NioSocketChannel.class);
            // 指定Handler
            //bootstrap.handler((ChannelHandler) new MyClientInitializer(context,mHandler));
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.option(ChannelOption.SO_TIMEOUT, 5000);

            bootstrap.remoteAddress(Constant.HOST, Constant.PORT);

            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast("linecoder", new LineBasedFrameDecoder(100000000));
                    socketChannel.pipeline().addLast(new StringEncoder());
                    socketChannel.pipeline().addLast(new StringDecoder());

                    //客户端的逻辑
                    socketChannel.pipeline().addLast("handler", new MyClientHandler());

                }
            });

            ChannelFuture future = bootstrap.connect(Constant.HOST, Constant.PORT).sync();
            if (future.isSuccess()) {
                socketChannel = (SocketChannel) future.channel();
                while (true) {
                    try {
                        if (socketChannel != null && !socketChannel.isActive()) {
                            ChannelFuture channelFuture = bootstrap.connect(Constant.HOST, Constant.PORT).sync();
                            socketChannel = (SocketChannel) channelFuture.channel();
                            socketChannel.writeAndFlush(UserLoginUtils.setLogin()+System.getProperty("line.separator"));
                        }
                        TimeUnit.SECONDS.sleep(5);
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //发送数据
    public static void sendMessage(Object o) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    // Log.i(TAG, "mChannel.write sth & " + mChannel.isOpen());
                    socketChannel.writeAndFlush(o + System.getProperty("line.separator"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
