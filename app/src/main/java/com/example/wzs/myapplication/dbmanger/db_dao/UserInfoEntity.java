package com.example.wzs.myapplication.dbmanger.db_dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hxcs-02 on 2017/8/18.
 */
@Entity
public class UserInfoEntity {
    @Id(autoincrement = true)
    private Long _ID;
    private String id;
    private String friendId;
    private String userCode;
    private String mobilePhone;
    private String headImgPath;
    private String nickName;
    private String signature;
    private String sex;
    private String area;
    private String onLine;
    @Generated(hash = 483917703)
    public UserInfoEntity(Long _ID, String id, String friendId, String userCode,
            String mobilePhone, String headImgPath, String nickName,
            String signature, String sex, String area, String onLine) {
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
        this.onLine = onLine;
    }
    @Generated(hash = 2042969639)
    public UserInfoEntity() {
    }
    public Long get_ID() {
        return this._ID;
    }
    public void set_ID(Long _ID) {
        this._ID = _ID;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFriendId() {
        return this.friendId;
    }
    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
    public String getUserCode() {
        return this.userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getMobilePhone() {
        return this.mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getHeadImgPath() {
        return this.headImgPath;
    }
    public void setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getSignature() {
        return this.signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getArea() {
        return this.area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getOnLine() {
        return this.onLine;
    }
    public void setOnLine(String onLine) {
        this.onLine = onLine;
    }
}
