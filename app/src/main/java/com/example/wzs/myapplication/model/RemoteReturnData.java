package com.example.wzs.myapplication.model;

import java.io.Serializable;

/**
 * Created by hxcs-02 on 2017/7/25.
 */


public class RemoteReturnData<DataType> implements Serializable {

    /**
     * header : {"code":"服务编号"}
     * body : {"isSuccessful":"调用流程是否成功执行完毕，布尔类型","errorCode":"错误码,参见附录-错误码定义","errorMsg":"错误信息,参见附录-错误码定义","resultData":"数据内容，参数由具体的接口定义\u2026\u2026\u201d"}
     */

    private HeaderBean header;
    private BodyBean body;
    private DataType data;

    public DataType getData() {
        return data;
    }

    public void setData(DataType data) {
        this.data = data;
    }

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
         * code : 服务编号
         */

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class BodyBean<T> {
        /**
         * isSuccessful : 调用流程是否成功执行完毕，布尔类型
         * errorCode : 错误码,参见附录-错误码定义
         * errorMsg : 错误信息,参见附录-错误码定义
         * resultData : 数据内容，参数由具体的接口定义……”
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
