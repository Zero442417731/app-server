package com.example.wzs.myapplication.model;

import java.io.Serializable;

/**
 * Created by hxcs-02 on 2017/8/10.
 */

//好友查找
public class UserSearch implements Serializable{


    /**
     * header : {"code":"HXCS-JC-YHCZ"}
     * body : {"isSuccessful":"调用流程是否成功执行完毕，布尔类型","errorCode":"错误码","errorMsg":"错误信息","resultData":{"id":"单用户登录唯一标示","userCode":"用户编号","mobilePhone":"手机号","headImgPath":"用户头像path","nickName":"昵称","signature":"个性签名","sex":"性别 男/女/","area":"地区","onLine":"是否在线，0:不在线，1:在线"}}
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
         * code : HXCS-JC-YHCZ
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
         * resultData : {"id":"单用户登录唯一标示","userCode":"用户编号","mobilePhone":"手机号","headImgPath":"用户头像path","nickName":"昵称","signature":"个性签名","sex":"性别 男/女/","area":"地区","onLine":"是否在线，0:不在线，1:在线"}
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
             * id : 单用户登录唯一标示
             * userCode : 用户编号
             * mobilePhone : 手机号
             * headImgPath : 用户头像path
             * nickName : 昵称
             * signature : 个性签名
             * sex : 性别 男/女/
             * area : 地区
             * onLine : 是否在线，0:不在线，1:在线
             */

            private String id;
            private String userCode;
            private String mobilePhone;
            private String headImgPath;
            private String nickName;
            private String signature;
            private String sex;
            private String area;
            private String onLine;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getMobilePhone() {
                return mobilePhone;
            }

            public void setMobilePhone(String mobilePhone) {
                this.mobilePhone = mobilePhone;
            }

            public String getHeadImgPath() {
                return headImgPath;
            }

            public void setHeadImgPath(String headImgPath) {
                this.headImgPath = headImgPath;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
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

            public String getOnLine() {
                return onLine;
            }

            public void setOnLine(String onLine) {
                this.onLine = onLine;
            }
        }
    }
}
