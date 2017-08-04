package com.example.wzs.myapplication.model;

/**
 * Created by hxcs-02 on 2017/8/4.
 */

public class YZXX {

    /**
     * friendId : 好友关系id
     * id : 单用户登录唯一标示
     * userCode : 用户编号
     * mobilePhone : 手机号
     * headImgPath : 用户头像path
     * nickName : 昵称
     * signature : 个性签名
     * sex : 性别 男/女/
     * area : 地区
     */

    private String friendId;
    private String id;
    private String userCode;
    private String mobilePhone;
    private String headImgPath;
    private String nickName;
    private String signature;
    private String sex;
    private String area;

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

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
}
