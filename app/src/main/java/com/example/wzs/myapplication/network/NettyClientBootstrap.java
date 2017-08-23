package com.example.wzs.myapplication.network;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.wzs.myapplication.config.Constant;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by hxcs-02 on 2017/8/23.
 */

//netty 客户端入口程序
public class NettyClientBootstrap {
    public static final int DISCONNECTION = 0;
    public static final int CONNECTING = 1;
    public static final int CONNECTED = 2;

    private EventLoopGroup group = null;
    private Bootstrap bootstrap = null;
    private static ChannelFuture channelFuture = null;
    private static NettyClientBootstrap nettyClient = null;
    private ArrayBlockingQueue<String> sendQueue = new ArrayBlockingQueue<String>(5000);
    private boolean sendFlag = true;
    private SendThread sendThread = new SendThread();

    private int connectState = DISCONNECTION;
    private boolean flag = true;

    public static NettyClientBootstrap getInstance() {
        if (nettyClient == null) {
            nettyClient = new NettyClientBootstrap();
        }
        return nettyClient;
    }

    private NettyClientBootstrap() {
        init();
    }

    private void init() {
        setConnectState(DISCONNECTION);
        bootstrap = new Bootstrap();
        group = new NioEventLoopGroup();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {

                //心跳包的添加
                socketChannel.pipeline().addLast("idleStateHandler", new IdleStateHandler(60, 60, 0));
                //对消息格式进行验证（MessageDecoder为自定义的解析验证类因协议规定而定）
                socketChannel.pipeline().addLast("linecoder", new LineBasedFrameDecoder(100000000));
                socketChannel.pipeline().addLast(new StringEncoder());
                socketChannel.pipeline().addLast(new StringDecoder());
                //客户端的逻辑
                socketChannel.pipeline().addLast("handler", new NettyClientHandler(nettyClient));
            }
        });
        startSendThread();
    }

    public void uninit() {
        stopSendThread();
        if (channelFuture != null) {
            channelFuture.channel().closeFuture();
            channelFuture.channel().close();
            channelFuture = null;
        }
        if (group != null) {
            group.shutdownGracefully();
            group = null;
            nettyClient = null;
            bootstrap = null;
        }
        setConnectState(DISCONNECTION);
        flag = false;
    }

    public void insertCmd(String cmd) {
        sendQueue.offer(cmd);
    }

    private void stopSendThread() {
        sendQueue.clear();
        sendFlag = false;
        sendThread.interrupt();
    }

    private void startSendThread() {
        sendQueue.clear();
        sendFlag = true;
        sendThread.start();
    }

    public void connect() {
        if (getConnectState() != CONNECTED) {
            setConnectState(CONNECTING);
            ChannelFuture f = bootstrap.connect(Constant.HOST, Constant.PORT);
            f.addListener(listener);
        }
    }

    private ChannelFutureListener listener = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            if (future.isSuccess()) {
                channelFuture = future;
                setConnectState(CONNECTED);
            } else {
                setConnectState(DISCONNECTION);
                future.channel().eventLoop().schedule(new Runnable() {
                    @Override
                    public void run() {
                        if (flag) {
                            connect();
                        }
                    }
                }, 3L, TimeUnit.SECONDS);
            }
        }
    };

    public void setConnectState(int connectState) {
        this.connectState = connectState;
    }

    public int getConnectState() {
        return connectState;
    }

    /**
     * 发送消息的线程
     */
    private class SendThread extends Thread {
        @Override
        public void run() {
            while (sendFlag) {
                try {
                    String cmd = sendQueue.take();
                    if (channelFuture != null && cmd != null) {

                  //      channelFuture.channel().writeAndFlush(o + System.getProperty("line.separator"));

                    }
                } catch (InterruptedException e) {
                    sendThread.interrupt();
                }
            }
        }
    }

    //发送数据
    public static void sendMessage(Object o) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    // Log.i(TAG, "mChannel.write sth & " + mChannel.isOpen());
                    channelFuture.channel().writeAndFlush(o + System.getProperty("line.separator"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}