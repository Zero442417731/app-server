package com.example.wzs.myapplication.model;

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
}

