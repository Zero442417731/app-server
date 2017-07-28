package com.example.wzs.myapplication.model;

/**
 * Created by hxcs-02 on 2017/7/28.
 */

public class GetSMS {

    /**
     * header : {"code":"HXCS-JC-FSDX"}
     * body : {"isSuccessful":true,"resultData":"0"}
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
         * isSuccessful : true
         * resultData : 0
         */

        private boolean isSuccessful;
        private String resultData;

        public boolean isIsSuccessful() {
            return isSuccessful;
        }

        public void setIsSuccessful(boolean isSuccessful) {
            this.isSuccessful = isSuccessful;
        }

        public String getResultData() {
            return resultData;
        }

        public void setResultData(String resultData) {
            this.resultData = resultData;
        }
    }
}
