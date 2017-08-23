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
    private static ChannelFutureListener channelFutureListener = null;


   // 连接到Socket服务端
    public static void connected() {
        new Thread() {
            @Override
            public void run() {
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
                    channelFutureListener = new ChannelFutureListener() {
                        public void operationComplete(ChannelFuture f) throws Exception {
                            if (f.isSuccess()) {
                                socketChannel = (SocketChannel) f.channel();
                                LogUtil.w("---", "重新连接服务器成功");
                            } else {
                                //  Log.d(Config.TAG, "重新连接服务器失败");
                                //  3秒后重新连接
                                f.channel().eventLoop().schedule(new Runnable() {
                                    @Override
                                    public void run() {
                                        doConnect();

                                    }
                                }, 3, TimeUnit.SECONDS);
                            }
                        }
                    };
                    ChannelFuture future = bootstrap.connect(Constant.HOST, Constant.PORT).sync();
                    if (future.isSuccess()) {
                        future.addListener(channelFutureListener);
                        socketChannel = (SocketChannel) future.channel();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


/*    // 初始化客户端
    public static void initClient() {

        NioEventLoopGroup group = new NioEventLoopGroup();

        // Client服务启动器 3.x的ClientBootstrap
        // 改为Bootstrap，且构造函数变化很大，这里用无参构造。
        bootstrap = new Bootstrap();
        // 指定EventLoopGroup
        bootstrap.group(group);
        // 指定channel类型
        bootstrap.channel(NioSocketChannel.class);
        // 指定Handler
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {

                socketChannel.pipeline().addLast("linecoder", new LineBasedFrameDecoder(100000000));
                socketChannel.pipeline().addLast(new StringEncoder());
                socketChannel.pipeline().addLast(new StringDecoder());
            }
        });
        //设置TCP协议的属性
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_TIMEOUT, 5000);

        channelFutureListener = new ChannelFutureListener() {
            public void operationComplete(ChannelFuture f) throws Exception {

                if (f.isSuccess()) {
                    Log.d("", "重新连接服务器成功");
                    socketChannel = (SocketChannel) f.channel();
                } else {
                    Log.d("", "重新连接服务器失败");
                    //  3秒后重新连接
                    f.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            doConnect();
                        }
                    }, 3, TimeUnit.SECONDS);
                }
            }
        };
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(Constant.HOST, Constant.PORT).sync();
            if (future.isSuccess()) {
                future.addListener(channelFutureListener);
                socketChannel = (SocketChannel) future.channel();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }*/

   /* //  连接到服务端
    public static void doConnect() {
        Log.d(TAG, "doConnect");
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(new InetSocketAddress(
                    Constant.HOST, Constant.PORT));
            future.addListener(channelFutureListener);
            sendMessage("{\"header\":{\"code\":\"HXCS-JC-YHDL\"},\"body\":{\"username\":\"17610652623\",\"password\":\"25f9e794323b453885f5181f1b624d0b\",\"deviceId\":\"865372023884106\",\"ostype\":\"1\"}}");
        } catch (Exception e) {
            e.printStackTrace();
            //future.addListener(channelFutureListener);
            Log.d(TAG, "关闭连接");
        }

    }*/

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

    //  连接到服务端
    public static void doConnect() {
        Log.d("----------", "doConnect");
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(new InetSocketAddress(
                    Constant.HOST, Constant.PORT)).sync();
            future.addListener(channelFutureListener);
            // ChannelFuture future = bootstrap.connect(Constant.HOST, Constant.PORT).sync();
            if (future.isSuccess()) {
                socketChannel = (SocketChannel) future.channel();
                socketChannel.writeAndFlush("{\"header\":{\"code\":\"HXCS-JC-YHDL\"},\"body\":{\"username\":\"17610652623\",\"password\":\"25f9e794323b453885f5181f1b624d0b\",\"deviceId\":\"865372023884106\",\"ostype\":\"1\"}}" + System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            //future.addListener(channelFutureListener);
            //     Log.d(TAG, "关闭连接");
        }

    }


}
