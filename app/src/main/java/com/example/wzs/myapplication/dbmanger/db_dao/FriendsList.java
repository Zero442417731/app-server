package com.example.wzs.myapplication.dbmanger.db_dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hxcs-02 on 2017/8/22.
 */
@Entity
public class FriendsList {
    @Id (autoincrement = true)
    private Long _ID;

    /**
     * id : 单用户登录唯一标示
     * friendId : 好友关系id, 主要用于后续好友之间创建画板
     * userCode : 用户编号
     * mobilePhone : 手机号
     * headImgPath : 用户头像path
     * nickName : 昵称
     * signature : 个性签名
     * sex : 性别 男/女/
     * area : 地区
     */

    private String id;
    private String friendId;
    private String userCode;
    private String mobilePhone;
    private String headImgPath;
    private String nickName;
    private String signature;
    private String sex;
    private String area;

    @Generated(hash = 1852393997)
    public FriendsList(Long _ID, String id, String friendId, String userCode,
            String mobilePhone, String headImgPath, String nickName,
            String signature, String sex, String area) {
        this._ID = _ID;
        this.id = id;
        this.friendId = friendId;
        this.userCode = userCode;
        this.mobilePhone = mobilePhone;
        this.headImgPath = headImgPath;
        this.nickName = nickName;
        this.signature = signature;
        this.sex = sex;
        this.area = area;
    }

    @Generated(hash = 1328136898)
    public FriendsList() {
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

    public Long get_ID() {
        return this._ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }
}
