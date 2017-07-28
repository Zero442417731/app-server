package com.example.wzs.myapplication.model;

/**
 * Created by hxcs-02 on 2017/7/28.
 */

public class SendIsSMS {

    /**
     * header : {"code":"HXCS-JC-DXYZ"}
     * body : {"mobilePhone":"手机号，必需，最大长度20个字符","smsValidCode":"短信验证码，必需"}
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
         * code : HXCS-JC-DXYZ
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
         * mobilePhone : 手机号，必需，最大长度20个字符
         * smsValidCode : 短信验证码，必需
         */

        private String mobilePhone;
        private String smsValidCode;

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getSmsValidCode() {
            return smsValidCode;
        }

        public void setSmsValidCode(String smsValidCode) {
            this.smsValidCode = smsValidCode;
        }
    }
}
