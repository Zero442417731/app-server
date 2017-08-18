package com.example.wzs.myapplication.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hxcs-04 on 2017/8/17.
 * 14.	HXCS-JC-HCXX  缓存消息读取(离线消息读取)
 */

public class ReadCacheMessage implements Serializable {

    /**
     * header : {"code":"HXCS-JC-HCXX"}
     * body : {"isSuccessful":"调用流程是否成功执行完毕，布尔类型","errorCode":"错误码","errorMsg":"错误信息","resultData":[{"msg":"消息内容字符串","msgType":"消息类型,S:字符串,B: byte base64后字符串"},{"msg":"消息内容字符串","msgType":"消息类型,S:字符串, B: byte base64后字符串"},{"msg":"消息内容字符串","msgType":"消息类型,S:字符串, B: byte base64后字符串"}]}
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
         * code : HXCS-JC-HCXX
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
         * resultData : [{"msg":"消息内容字符串","msgType":"消息类型,S:字符串,B: byte base64后字符串"},{"msg":"消息内容字符串","msgType":"消息类型,S:字符串, B: byte base64后字符串"},{"msg":"消息内容字符串","msgType":"消息类型,S:字符串, B: byte base64后字符串"}]
         */

        private boolean isSuccessful;
        private String errorCode;
        private String errorMsg;
        private List<ResultDataBean> resultData;

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

        public List<ResultDataBean> getResultData() {
            return resultData;
        }

        public void setResultData(List<ResultDataBean> resultData) {
            this.resultData = resultData;
        }

        public static class ResultDataBean {
            /**
             * msg : 消息内容字符串
             * msgType : 消息类型,S:字符串,B: byte base64后字符串
             */

            private String msg;
            private String msgType;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getMsgType() {
                return msgType;
            }

            public void setMsgType(String msgType) {
                this.msgType = msgType;
            }
        }
    }
}
