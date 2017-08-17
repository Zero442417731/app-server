package com.example.wzs.myapplication.model;

/**
 * Created by hxcs-02 on 2017/8/17.
 */

public class AddFriend {

    /**
     * header : {"code":"HXCS-JC-HYTJ"}
     * body : {"isSuccessful":"调用流程是否成功执行完毕，布尔类型","errorCode":"错误码","errorMsg":"错误信息","resultData":"结果反馈。 0：添加失败；1：添加成功；2：好友之前已被添加"}
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
         * code : HXCS-JC-HYTJ
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
         * resultData : 结果反馈。 0：添加失败；1：添加成功；
         * 2：好友之前已被添加，3：好友信息不存在，4: 好友验证信息已发送
         */

        private boolean isSuccessful;
        private String errorCode;
        private String errorMsg;
        private String resultData;

        public boolean isSuccessful() {
            return isSuccessful;
        }

        public void setSuccessful(boolean successful) {
            isSuccessful = successful;
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
