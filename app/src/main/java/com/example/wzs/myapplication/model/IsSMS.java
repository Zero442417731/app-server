package com.example.wzs.myapplication.model;

/**
 * Created by hxcs-02 on 2017/7/28.
 */

public class IsSMS {


 /**
     * header : {"code":"HXCS-JC-DXYZ"}
     * body : {"isSuccessful":"调用流程是否成功执行完毕，布尔类型","errorCode":"错误码","errorMsg":"错误信息","resultData":"是否验证成功，布尔类型"}
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
         * isSuccessful : 调用流程是否成功执行完毕，布尔类型
         * errorCode : 错误码
         * errorMsg : 错误信息
         * resultData : 是否验证成功，布尔类型
         */

        private String isSuccessful;
        private String errorCode;
        private String errorMsg;
        private String resultData;

        public String getIsSuccessful() {
            return isSuccessful;
        }

        public void setIsSuccessful(String isSuccessful) {
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
