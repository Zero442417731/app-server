package com.example.wzs.myapplication.network;

import com.example.wzs.myapplication.model.UserLoginInfo;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by hxcs-02 on 2017/7/25.
 */
public class ApiWrapper extends RetrofitHelper {
    private static ApiWrapper apiWrapper;

    public ApiWrapper() {
    }

    public static ApiWrapper getInstance() {
        if (apiWrapper == null) {
            apiWrapper = new ApiWrapper();
        }
        return apiWrapper;
    }

    /******************************************登录******************************************/
    public Observable<Object> sms(String phone) {
        return getAPIService().sms(phone).compose(this.<Object>applySchedulers());
    }
    public Observable<UserLoginInfo> login(String phone, String password) {
        return getAPIService().login(phone, password).compose(this.<UserLoginInfo>applySchedulers());
    }

}
