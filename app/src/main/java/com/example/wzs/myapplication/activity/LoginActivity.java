package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.event.EventId;
import com.example.wzs.myapplication.event.MessageEvent;
import com.example.wzs.myapplication.model.UserLoginInfo;
import com.example.wzs.myapplication.utils.MD5Util;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.example.wzs.myapplication.utils.ToastUtil;
import com.nonecity.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.network.ClientUtil;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.example.wzs.myapplication.utils.DoubleClickExitUtil;
import com.example.wzs.myapplication.weight.PasswordEditText;
import com.zhy.android.percent.support.PercentLinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.login_head)
    CircleImageView loginHead;
    @Bind(R.id.login_name)
    PasswordEditText loginName;
    @Bind(R.id.phone_layout)
    PercentLinearLayout phoneLayout;
    @Bind(R.id.login_pwd)
    PasswordEditText loginPwd;
    @Bind(R.id.sms_layout)
    PercentLinearLayout smsLayout;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.register_login)
    TextView registerLogin;
    @Bind(R.id.forgot_password)
    TextView forgotPassword;
    private DoubleClickExitUtil doubleClickExitUtil;
    private String name;
    private String pwd;

    @Override
    protected int setLayoutId() {
        EventBus.getDefault().register(this);
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        ClientUtil.connected();
        doubleClickExitUtil = new DoubleClickExitUtil();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return doubleClickExitUtil.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.register_login, R.id.forgot_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                name = loginName.getText().toString().trim();
                pwd = loginPwd.getText().toString().trim();
                ClientUtil.sendMessage(setLogin());
                //  UserLoginInfo userLoginInfo;

              /*  if (userLoginInfo.getBody().isSuccessful()) {
                    SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "phone", userLoginInfo.getUsername());
                    SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token", userLoginInfo.getToken());
                    SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "userid", userLoginInfo.getUserid());
                    LogUtil.e("username------", userLoginInfo.getUsername());
                    LogUtil.e("userid------", userLoginInfo.getUserid());
                    LogUtil.e("usertoken------", userLoginInfo.getToken());
                    HXApplication.isLogin = true;
                    ActivityLauncherUtil.launcher(HXApplication.mContext, MainActivity.class);
                    finish();


                } else {
                    ToastUtil.showToast(userLoginInfo.getBody().getErrorMsg());
                }*/
                break;
            case R.id.register_login:
                ActivityLauncherUtil.launcher(this, RegisterActivity.class);
                break;
            case R.id.forgot_password:
                ActivityLauncherUtil.launcher(this, ForgotPwdActivity.class);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void userLogin(MessageEvent messageEvent) {
        switch (messageEvent.getFriendUserId()) {
            case EventId.USERLOGIN_SUSSES:
                boolean successful = (boolean) messageEvent.getMessageContent();
                Log.i(TAG, "userLogin: " + successful);
                if (successful) {
                    SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER_PWD,"name",name);
                    SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER_PWD,"pwd",pwd);
                    ActivityLauncherUtil.launcher(this, MainActivity.class);
                }
                break;
            case EventId.USERLOGIN_ERROR:
                String errorMsg = (String) messageEvent.getMessageContent();
                Log.i(TAG, "userLogin: " + errorMsg);
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        //执行耗时操作
                        Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                    }
                };
                new Thread() {
                    public void run() {
                        new Handler(Looper.getMainLooper()).post(runnable);//在子线程中直接去new 一个handler
                        //这种情况下，Runnable对象是运行在主线程中的，不可以进行联网操作，但是可以更新UI
                    }
                }.start();

                break;
        }

    }

    private String setLogin() {

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject.put("code", "HXCS-JC-YHDL");
            jsonObject1.put("username", name);
            jsonObject1.put("password", MD5Util.getMD5Str(pwd));
            jsonObject1.put("deviceId", HXApplication.PHONE_ID);
            jsonObject1.put("ostype", "1");
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解除订阅
    }
}
