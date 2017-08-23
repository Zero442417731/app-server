package com.example.wzs.myapplication.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hxcs-04 on 2017/8/22.
 * 15.	HXCS-JC-QQLB  用户好友请求列表
 */

public  class RequestList implements Serializable {

    /**
     * header : {"code":"HXCS-JC-QQLB"}
     * body : {"isSuccessful":"调用流程是否成功执行完毕，布尔类型","errorCode":"错误码","errorMsg":"错误信息","resultData":[{"id":"用户id","friendId":"好友关系id, 主要用于后续好友之间创建画板","userCode":"用户编号","mobilePhone":"手机号","headImgPath":"用户头像path","nickName":"昵称","userNickname":"为好友设置的昵称","signature":"个性签名","sex":"性别 男/女/","area":"地区","state":"状态 0:好友关系待验证 1:验证通过 2:屏蔽好友"},{"id":"用户id","friendId":"好友关系id, 主要用于后续好友之间创建画板","userCode":"用户编号","mobilePhone":"手机号","headImgPath":"用户头像path","nickName":"昵称","userNickname":"为好友设置的昵称","signature":"个性签名","sex":"性别 男/女/","area":"地区","state":"状态 0:好友关系待验证 1:验证通过 2:屏蔽好友"}]}
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
         * code : HXCS-JC-QQLB
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
         * resultData : [{"id":"用户id","friendId":"好友关系id, 主要用于后续好友之间创建画板","userCode":"用户编号","mobilePhone":"手机号","headImgPath":"用户头像path","nickName":"昵称","userNickname":"为好友设置的昵称","signature":"个性签名","sex":"性别 男/女/","area":"地区","state":"状态 0:好友关系待验证 1:验证通过 2:屏蔽好友"},{"id":"用户id","friendId":"好友关系id, 主要用于后续好友之间创建画板","userCode":"用户编号","mobilePhone":"手机号","headImgPath":"用户头像path","nickName":"昵称","userNickname":"为好友设置的昵称","signature":"个性签名","sex":"性别 男/女/","area":"地区","state":"状态 0:好友关系待验证 1:验证通过 2:屏蔽好友"}]
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
             * id : 用户id
             * friendId : 好友关系id, 主要用于后续好友之间创建画板
             * userCode : 用户编号
             * mobilePhone : 手机号
             * headImgPath : 用户头像path
             * nickName : 昵称
             * userNickname : 为好友设置的昵称
             * signature : 个性签名
             * sex : 性别 男/女/
             * area : 地区
             * state : 状态 0:好友关系待验证 1:验证通过 2:屏蔽好友
             */

            private String id;
            private String friendId;
            private String userCode;
            private String mobilePhone;
            private String headImgPath;
            private String nickName;
            private String userNickname;
            private String signature;
            private String sex;
            private String area;
            private int state;
            private String remark;

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFriendId() {
                return friendId;
            }

            public void setFriendId(String friendId) {
                this.friendId = friendId;
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

            public String getUserNickname() {
                return userNickname;
            }

            public void setUserNickname(String userNickname) {
                this.userNickname = userNickname;
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


        }
    }
}
