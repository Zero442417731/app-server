package com.example.wzs.myapplication.dbmanger.db_dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hxcs-02 on 2017/8/22.
 */
@Entity
public class FriendID {

    @Id(autoincrement = true)
    private Long _Id;

    private String friendUserId;

    @Generated(hash = 129588793)
    public FriendID(Long _Id, String friendUserId) {
        this._Id = _Id;
        this.friendUserId = friendUserId;
    }

    @Generated(hash = 1543100971)
    public FriendID() {
    }

    public Long get_Id() {
        return this._Id;
    }

    public void set_Id(Long _Id) {
        this._Id = _Id;
    }

    public String getFriendUserId() {
        return this.friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

}
