package com.example.wzs.myapplication.network;

/**
 * Created by hxcs-02 on 2017/8/2.
 */

public interface INettyClient {
    void connect(String host, int port);//1. 建立连接

    void sendMessage(int mt, String msg, long delayed);//2. 发送消息

    void addDataReceiveListener(OnDataReceiveListener listener);//3. 为不同的请求添加监听器

    interface OnDataReceiveListener {
        void onDataReceive(int mt, String json);//接收到数据时触发
    }

    interface OnConnectStatusListener {
        void onDisconnected();//连接异常时触发
    }
}



