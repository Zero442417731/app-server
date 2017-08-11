package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nonecity.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.model.GetSMS;
import com.example.wzs.myapplication.model.IsSMS;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.example.wzs.myapplication.utils.DoubleClickExitUtil;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.ToastUtil;
import com.zhy.android.percent.support.PercentLinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.login_head)
    CircleImageView loginHead;
    @Bind(R.id.register_phone)
    EditText registerPhone;
    @Bind(R.id.phone_layout)
    PercentLinearLayout phoneLayout;
    @Bind(R.id.get_sms)
    Button getSms;
    @Bind(R.id.sms_layout)
    PercentLinearLayout smsLayout;
    @Bind(R.id.btn_register)
    Button btnRegister;
    @Bind(R.id.register_login)
    TextView registerLogin;
    @Bind(R.id.forgot_password)
    TextView forgotPassword;

    @Bind(R.id.et_sms)
    EditText etSms;

    private TimeCounter counter = new TimeCounter(60 * 1000, 1000);

    private String phone;
    private DoubleClickExitUtil doubleClickExitUtil;
    private boolean successful;
    private String phone1;
    private String resultData;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        doubleClickExitUtil = new DoubleClickExitUtil();
        registerPhone.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        etSms.setInputType(EditorInfo.TYPE_CLASS_PHONE);
    }


    @OnClick({R.id.get_sms, R.id.btn_register, R.id.register_login, R.id.forgot_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_sms:
                if (!isMobile(registerPhone.getText().toString().trim())) {
                    ToastUtil.showToast("手机号不正确");
                    return;
                }

                HXApplication.retrofitUtils.postData(registerJson(), new MyCallback<GetSMS>() {
                    @Override
                    public void onSuccess(GetSMS getSMS) {
                        resultData = getSMS.getBody().getResultData();
                    }

                    @Override
                    public void onError(String msg) {
                        LogUtil.e("aaaa", msg);
                    }
                });
                break;
            case R.id.btn_register:
                register();
                break;
            case R.id.register_login:
                ActivityLauncherUtil.launcher(HXApplication.mContext, LoginActivity.class);
                break;
            case R.id.forgot_password:
                ActivityLauncherUtil.launcher(this, ForgotPwdActivity.class);
                break;

        }
    }

    private void smsCode() {
        counter.start();
        getSms.setClickable(false);
    }


    private String registerJson() {
        phone = registerPhone.getText().toString().trim();
        HXApplication.phone = phone;
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showLong(this, "请输入手机号");
        }
        smsCode();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject.put("code", "HXCS-JC-FSDX");
            jsonObject1.put("mobile", phone);
            jsonObject1.put("type", "1");
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }

    private void register() {
        phone1 = registerPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone1)) {
            ToastUtil.showLong(this, "请输入手机号");
            return;
        }

        String sms = etSms.getText().toString().trim();
        if (TextUtils.isEmpty(sms)) {
            ToastUtil.showLong(this, "请输入验证码");
            return;
        }
        if (resultData.equals("0")) {
            HXApplication.retrofitUtils.postData(isSMS(), new MyCallback<IsSMS>() {
                @Override
                public void onSuccess(IsSMS isSMS) {
                    successful = isSMS.getBody().isResultData();

                }

                @Override
                public void onError(String msg) {
                    Log.d("错误信息-----------", msg);
                }
            });
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    if (successful) {
                        ActivityLauncherUtil.launcher(RegisterActivity.this, SetPasswordActivity.class);
                    } else {
                        ToastUtil.showLong(RegisterActivity.this, "注册失败");
                    }

                }
            }, 1000);


        } else if (resultData.equals("1")) {


        } else if (resultData.equals("2")) {
            ToastUtil.showLong(this, "手机号已注册");
        } else {
            return;
        }


    }

    private String isSMS() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject.put("code", "HXCS-JC-DXYZ");
            jsonObject1.put("mobilePhone", phone1);
            jsonObject1.put("smsValidCode", etSms.getText().toString().trim());
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    private class TimeCounter extends CountDownTimer {
        public TimeCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            getSms.setText("再次获取" + millisUntilFinished / 1000 + "");
        }

        @Override
        public void onFinish() {
            getSms.setClickable(true);
            getSms.setText("再次获取");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return doubleClickExitUtil.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    public static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
