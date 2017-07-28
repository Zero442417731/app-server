package com.example.wzs.myapplication.model;

/**
 * Created by hxcs-02 on 2017/7/28.
 */

public class SendSMS {

    /**
     * header : {"code":"HXCS-JC-FSDX"}
     * body : {"mobile":"手机号，必需","type":"1：注册；2：找回密码； "}
     */

    private HeaderBean header;
    private BodyBean body;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * code : HXCS-JC-FSDX
         */

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class BodyBean {
        /**
         * mobile : 手机号，必需
         * type : 1：注册；2：找回密码；
         */

        private String mobile;
        private String type;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
