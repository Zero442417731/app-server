package com.example.wzs.myapplication.model;

import java.io.Serializable;

/**
 * Created by hxcs-02 on 2017/8/17.
 */

public class XTJC implements Serializable {


    public String getHeartState() {
        return heartState;
    }

    public void setHeartState(String heartState) {
        this.heartState = heartState;
    }

    /**
     *  heartState : 1
     */

    private String heartState;
}
