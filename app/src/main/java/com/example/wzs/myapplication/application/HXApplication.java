package com.example.wzs.myapplication.application;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.base.BaseFragment;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.SignInfo;
import com.example.wzs.myapplication.model.UserInfo;
import com.example.wzs.myapplication.model.UserLoginInfo;
import com.example.wzs.myapplication.network.RetrofitUtils;
import com.example.wzs.myapplication.utils.GlideImageLoader;
import com.example.wzs.myapplication.utils.SDPackageUtil;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.nonecity.BuildConfig;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zxy.tiny.Tiny;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by hxcs-02 on 2017/7/25.
 */

public class HXApplication extends Application {
    private static HXApplication instance;
    private UserLoginInfo userLoginInfo;
    private UserInfo userInfo;
    private SignInfo signInfo;
    public static BaseActivity context;
    public static Context mContext;
    public static BaseFragment lastFragment;
    public static String phone;
    public static RetrofitUtils retrofitUtils;
    public static String PHONE_ID;


    public synchronized static HXApplication getInstance() {
        return instance;
    }


    private static RefWatcher refWatcher;
    public static  boolean isLogin;


    public HXApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this;

        retrofitUtils = RetrofitUtils.getInstance();
        refWatcher = LeakCanary.install(this);
        initGalleryFinal();
        //    PHONE_ID = SDPackageUtil.getDeviceId();
        PHONE_ID = "12311231";
        Tiny.getInstance().init(this);
        autoLogin();
    }

    public static Context getAppContext() {

        return instance == null ? null : instance.getApplicationContext();

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

    }


    private void initGalleryFinal() {
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(0x5D, 0x87, 0xF1))
                .setTitleBarTextColor(Color.WHITE)
                .setTitleBarIconColor(Color.WHITE)
                .setFabNornalColor(Color.rgb(0x5D, 0x87, 0xF1))
                .setFabPressedColor(Color.rgb(0x5D, 0x69, 0xF1))
                .setCheckNornalColor(Color.WHITE)
                .setCheckSelectedColor(Color.BLACK)
                .build();
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnablePreview(true)
                .build();
        GlideImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme)
                .setDebug(BuildConfig.DEBUG)
                .setNoAnimcation(true)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
    }

    public static RefWatcher getRefWatcher() {

        return refWatcher;
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
