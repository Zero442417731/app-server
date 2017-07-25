package com.example.wzs.myapplication.application;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.SignInfo;
import com.example.wzs.myapplication.model.UserInfo;
import com.example.wzs.myapplication.model.UserLoginInfo;
import com.example.wzs.myapplication.network.ApiWrapper;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.example.wzs.myapplication.utils.ToastUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import rx.Subscriber;

/**
 * Created by hxcs-02 on 2017/7/25.
 */

public class HXApplication extends Application{
    private static HXApplication instance;
    private UserLoginInfo userLoginInfo;
    private UserInfo userInfo;
    private SignInfo signInfo;
    public synchronized static HXApplication getInstance() {
        return instance;
    }


    private RefWatcher refWatcher;
    private boolean isLogin;


    public HXApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


        //leakcanary install
        refWatcher = LeakCanary.install(this);
    }
    private void autoLogin() {
        if (isLogin) {
            return;
        }
        String phone = SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "phone");
        String password = SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "password");
        if (TextUtils.isEmpty(phone)) {
            return;
        }
        if (TextUtils.isEmpty(password)) {
            return;
        }
        ApiWrapper.getInstance().login(phone, password).subscribe(new Subscriber<UserLoginInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserLoginInfo userLoginInfo) {
                if (userLoginInfo != null) {
                    setUserLoginInfo(userLoginInfo);
                    setLogin(true);
                    ToastUtil.showToast("自动登录成功");
                }
            }
        });
    }
    public static RefWatcher getRefWatcher(Context context) {
        HXApplication hxApplication = (HXApplication) context.getApplicationContext();
        return hxApplication.refWatcher;
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public UserLoginInfo getUserLoginInfo() {
        return userLoginInfo;
    }

    public void setUserLoginInfo(UserLoginInfo userLoginInfo) {
        this.userLoginInfo = userLoginInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public SignInfo getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(SignInfo signInfo) {
        this.signInfo = signInfo;
    }
}
