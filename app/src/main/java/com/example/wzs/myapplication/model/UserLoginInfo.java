package com.example.wzs.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hxcs-02 on 2017/7/25.
 */

public class UserLoginInfo implements Serializable {


    /**
     * isLogined : true
     * userid : 11
     * username : 13720018370
     * token : BLVs0xkvW41/wDEPrKPvRNicgurE64gesVcncaThdn8X/ok7FQWBQVF295hRJl1k
     */

    private boolean isLogined;
    private String userid;
    private String username;
    private String token;
    /**
     * header : {"code":"HXCS-JC-YHDL"}
     * body : {"isSuccessful":"调用流程是否成功执行完毕，布尔类型","errorCode":"错误码","errorMsg":"错误信息","resultData":{"isLogined":"是否登录成功，布尔类型","username":"注册用户登录名","userid":"注册用户ID","token":"单用户登录唯一标示"}}
     */

    private HeaderBean header;
    private BodyBean body;

    public boolean isIsLogined() {
        return isLogined;
    }

    public void setIsLogined(boolean isLogined) {
        this.isLogined = isLogined;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
         * code : HXCS-JC-YHDL
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
         * resultData : {"isLogined":"是否登录成功，布尔类型","username":"注册用户登录名","userid":"注册用户ID","token":"单用户登录唯一标示"}
         */

        private boolean isSuccessful;
        private String errorCode;
        private String errorMsg;
        private ResultDataBean resultData;

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

        public ResultDataBean getResultData() {
            return resultData;
        }

        public void setResultData(ResultDataBean resultData) {
            this.resultData = resultData;
        }

        public static class ResultDataBean {
            /**
             * isLogined : 是否登录成功，布尔类型
             * username : 注册用户登录名
             * userid : 注册用户ID
             * token : 单用户登录唯一标示
             */

            @SerializedName("isLogined")
            private boolean isLoginedX;
            @SerializedName("username")
            private String usernameX;
            @SerializedName("userid")
            private String useridX;
            @SerializedName("token")
            private String tokenX;

            public boolean isLoginedX() {
                return isLoginedX;
            }

            public void setLoginedX(boolean loginedX) {
                isLoginedX = loginedX;
            }

            public String getUsernameX() {
                return usernameX;
            }

            public void setUsernameX(String usernameX) {
                this.usernameX = usernameX;
            }

            public String getUseridX() {
                return useridX;
            }

            public void setUseridX(String useridX) {
                this.useridX = useridX;
            }

            public String getTokenX() {
                return tokenX;
            }

            public void setTokenX(String tokenX) {
                this.tokenX = tokenX;
            }
        }
    }
}

