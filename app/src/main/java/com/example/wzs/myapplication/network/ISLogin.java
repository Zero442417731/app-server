package com.example.wzs.myapplication.network;

import com.example.wzs.myapplication.model.UserLoginInfo;

/**
 * Created by hxcs-02 on 2017/8/10.
 */

public interface ISLogin {

    void onSuccess(UserLoginInfo userLoginInfo);

    void onError(String msg);

}
