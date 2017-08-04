package com.example.wzs.myapplication.model;

/**
 * Created by hxcs-02 on 2017/8/3.
 */

public class UploadHead {

    /**
     * header : {"code":"HXCS-JC-YHTX"}
     * body : {"isSuccessful":true,"resultData":{"result":"1","url":"http://app.nonecity.com:81/file/20170803/66b7632804964ecd9d6a0931d39f7f06.png"}}
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
         * code : HXCS-JC-YHTX
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
         * resultData : {"result":"1","url":"http://app.nonecity.com:81/file/20170803/66b7632804964ecd9d6a0931d39f7f06.png"}
         */

        private boolean isSuccessful;
        private ResultDataBean resultData;

        public boolean isIsSuccessful() {
            return isSuccessful;
        }

        public void setIsSuccessful(boolean isSuccessful) {
            this.isSuccessful = isSuccessful;
        }

        public ResultDataBean getResultData() {
            return resultData;
        }

        public void setResultData(ResultDataBean resultData) {
            this.resultData = resultData;
        }

        public static class ResultDataBean {
            /**
             * result : 1
             * url : http://app.nonecity.com:81/file/20170803/66b7632804964ecd9d6a0931d39f7f06.png
             */

            private String result;
            private String url;

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
