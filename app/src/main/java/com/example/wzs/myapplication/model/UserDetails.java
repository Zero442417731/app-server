package com.example.wzs.myapplication.model;

import java.io.Serializable;

/**
 * Created by hxcs-02 on 2017/8/15.
 */

public class UserDetails implements Serializable {


    /**
     * header : {"code":"HXCS-JC-YHXX"}
     * body : {"isSuccessful":"调用流程是否成功执行完毕，布尔类型","errorCode":"错误码","errorMsg":"错误信息","resultData":{"id":"用户id","nickName":"昵称","userCode":"用户编码","signature":"个性签名","sex":"性别 男/女/","area":"地区","addVerify":"被添加时是否需要验证，0:不需要 1:需要"}}
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
         * code : HXCS-JC-YHXX
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
         * resultData : {"id":"用户id","nickName":"昵称","userCode":"用户编码","signature":"个性签名","sex":"性别 男/女/","area":"地区","addVerify":"被添加时是否需要验证，0:不需要 1:需要"}
         */

        private String isSuccessful;
        private String errorCode;
        private String errorMsg;
        private ResultDataBean resultData;

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

        public ResultDataBean getResultData() {
            return resultData;
        }

        public void setResultData(ResultDataBean resultData) {
            this.resultData = resultData;
        }


        public static class ResultDataBean {
            /**
             * id : 用户id
             * nickName : 昵称
             * userCode : 用户编码
             * signature : 个性签名
             * sex : 性别男/女/
             * area : 地区
             * mobile: 手机号
             * picUrl: 头像url
             * addVerify : 被添加时是否需要验证，0: 不需要1: 需要
             */

            private String id;
            private String mobile;
            private String picUrl;
            private String signature;
            private String sex;
            private String area;
            private String addVerify;
            private String nickName;
            private String userCode;

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddVerify() {
                return addVerify;
            }

            public void setAddVerify(String addVerify) {
                this.addVerify = addVerify;
            }
        }
    }
}
