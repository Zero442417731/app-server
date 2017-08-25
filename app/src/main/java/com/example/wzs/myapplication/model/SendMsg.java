package com.example.wzs.myapplication.model;

import com.example.wzs.myapplication.network.ClientUtil;
import com.example.wzs.myapplication.utils.JsonBinder;

import java.io.Serializable;

public class SendMsg implements Serializable {
    private HeaderBean header;
    private  Object  body;
    public HeaderBean getHeader() {
        return header;
    }
    public void setHeader(HeaderBean header) {
        this.header = header;
    }
    public Object getBody() {
        return body;
    }
    public void setBody(Object body) {
        this.body = body;
    }

    public SendMsg() {
    }

    public SendMsg(String code) {
        header=new HeaderBean();
        header.setCode(code);
    }


    /**
     * 转 json  Str
     * @return  json Str
     */
    public  String StrFromData() {
        JsonBinder jsonBinder = JsonBinder.buildNormalBinder();
        return jsonBinder.toJson(this);
    }

    /**
     * 直接调用发送方法发送
     * @return Str
     */
    public void sendMessage(){
        String Str=StrFromData();
        ClientUtil.sendMessage(Str);
    }


    public static class HeaderBean {
        private String code;
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }

    }
}
