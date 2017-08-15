package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.network.ClientUtil;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.nonecity.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.model.Register;
import com.example.wzs.myapplication.model.SetPwd;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.MD5Util;
import com.example.wzs.myapplication.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.utils.Utils;

public class SetPasswordActivity extends BaseActivity {
    private static final String TAG = "SetPasswordActivity";

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.base_action_bar)
    RelativeLayout baseActionBar;
    @Bind(R.id.set_password)
    EditText setPassword;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.set_password_again)
    EditText setPasswordAgain;
    @Bind(R.id.btn_login)
    Button btnLogin;

    private String resultData;
    private String pwd;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.title_back_img, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login() {
        pwd = setPassword.getText().toString().trim();
        String pwd1 = setPasswordAgain.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showShort(this, "请输入密码~");
        } else if (TextUtils.isEmpty(pwd1)) {
            ToastUtil.showShort(this, "请确认密码~");
        } else if (pwd.length() < 6) {
            ToastUtil.showShort(this, "密码不能小于6位~");
        } else if (pwd1.length() < 6) {
            ToastUtil.showShort(this, "密码不能小于6位~");
        } else if (!pwd.equals(pwd1)) {
            ToastUtil.showShort(this, "两次密码输入不一致");
        } else {
            //   SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER_PWD, "password", MD5Util.getMD5Str(pwd));
            // 关闭软键盘
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(
                    setPassword.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            HXApplication.retrofitUtils.postData(getZC(), new MyCallback<Register>() {
                @Override
                public void onSuccess(Register register) {
                    resultData = register.getBody().getResultData();
                    LogUtil.e(TAG, "成功" + resultData);
                }

                @Override
                public void onError(String msg) {
                    LogUtil.e(TAG, "失败" + msg);
                }
            });
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    if ("1".equals(resultData)) {
                        ActivityLauncherUtil.launcher(SetPasswordActivity.this, LoginActivity.class);
                    } else {
                        ToastUtil.showLong(SetPasswordActivity.this, "注册失败");
                    }
                }
            }, 1000);

        }

    }

    private String getZC() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        try {
            jsonObject.put("code", "HXCS-JC-YHZC");
            jsonObject1.put("mobile", HXApplication.phone);
            jsonObject1.put("ostype", "1");
            jsonObject1.put("password", MD5Util.getMD5Str(pwd));
            jsonObject1.put("gps", SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "latitude")
                    + "," + SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "longitude"));
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }
}
