package com.example.wzs.myapplication.model;

/**
 * Created by hxcs-02 on 2017/8/14.
 */

public class UserChangeModel {


    /**
     * header : {"code":"HXCS-JC-YHXG"}
     * body : {"isSuccessful":true,"errorCode":"错误码","errorMsg":"错误信息","resultData":"结果反馈。 0：修改失败；1：修改成功"}
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
         * code : HXCS-JC-YHXG
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
         * errorCode : 错误码
         * errorMsg : 错误信息
         * resultData : 结果反馈。 0：修改失败；1：修改成功
         */

        private boolean isSuccessful;
        private String errorCode;
        private String errorMsg;
        private String resultData;

        public boolean isIsSuccessful() {
            return isSuccessful;
        }

        public void setIsSuccessful(boolean isSuccessful) {
            this.isSuccessful = isSuccessful;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getResultData() {
            return resultData;
        }

        public void setResultData(String resultData) {
            this.resultData = resultData;
        }
    }
}
