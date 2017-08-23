package com.example.wzs.myapplication.model;

import java.io.Serializable;

/**
 * Created by hxcs-04 on 2017/8/18.
 * 好友验证通过/不通过
 */

public class AddFriendsResponce implements Serializable {

    /**
     * header : {"code":"HXCS-JC-YZTG"}
     * body : {"isSuccessful":"调用流程是否成功执行完毕，布尔类型","errorCode":"错误码","errorMsg":"错误信息","resultData":"结果反馈。 0：失败；1:成功 "}
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
         * code : HXCS-JC-YZTG
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
         * resultData : 结果反馈。 0：失败；1:成功
         */

        private boolean isSuccessful;
        private String errorCode;
        private String errorMsg;
        private int resultData;

        public int getResultData() {
            return resultData;
        }

        public void setResultData(int resultData) {
            this.resultData = resultData;
        }

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

    }
}
