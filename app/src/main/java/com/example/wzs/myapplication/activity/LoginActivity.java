package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nonecity.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.network.ClientUtil;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.example.wzs.myapplication.utils.DoubleClickExitUtil;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.example.wzs.myapplication.weight.PasswordEditText;
import com.zhy.android.percent.support.PercentLinearLayout;

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
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
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
                ClientUtil.connected();
                ClientUtil.sendMessage(setLogin());
                SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "phone",name );
                SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "password", pwd);
                HXApplication.isLogin = true;
                ActivityLauncherUtil.launcher(HXApplication.mContext,MainActivity.class);
                finish();
                break;
            case R.id.register_login:
                ActivityLauncherUtil.launcher(this, RegisterActivity.class);
                break;
            case R.id.forgot_password:
                ActivityLauncherUtil.launcher(this, ForgotPwdActivity.class);
                break;
        }
    }

    private String setLogin() {

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject.put("code", "HXCS-JC-YHDL");
            jsonObject1.put("username","13720018370" );
            jsonObject1.put("password", "1234567");
            jsonObject1.put("deviceId", HXApplication.PHONE_ID);
            jsonObject1.put("ostype", "1");
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }
}
