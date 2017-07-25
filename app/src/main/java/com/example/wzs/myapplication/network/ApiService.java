package com.example.wzs.myapplication.network;

import android.database.Observable;

import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.RemoteReturnData;
import com.example.wzs.myapplication.model.UserLoginInfo;

import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.example.wzs.myapplication.network.RetrofitHelper.getAPIService;

/**
 * Created by hxcs-02 on 2017/7/25.
 */

public interface   ApiService {
    /******************************************登录******************************************/
    @POST(Constant.URL_SUB_SMS)
    rx.Observable<RemoteReturnData<Object>> sms(@Query("mobile") String phone) ;

    @POST(Constant.URL_SERVICE)
    rx.Observable<RemoteReturnData<Object>> register(@Query("mobile") String phone,
                                                     @Query("verify_coder") String sms,
                                                     @Query("user_pass") String password,
                                                     @Query("user_pwd") String passwordRepeat);
    @POST(Constant.URL_SUB_LOGIN)
    rx.Observable<RemoteReturnData<UserLoginInfo>> login(@Query("mobile") String phone,
                                                         @Query("user_pwd") String passwordRepeat);
}
