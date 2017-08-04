package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wzs.myapplication.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.GetSMS;
import com.example.wzs.myapplication.model.IsSMS;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.ToastUtil;
import com.example.wzs.myapplication.weight.PasswordEditText;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPwdActivity extends BaseActivity {


    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.base_action_bar)
    RelativeLayout baseActionBar;
    @Bind(R.id.set_password)
    PasswordEditText registerPhone;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.set_password_again)
    PasswordEditText etSms;
    @Bind(R.id.get_sms)
    Button getSms;
    @Bind(R.id.btn_login)
    Button btnLogin;
    private TimeCounter counter = new TimeCounter(60 * 1000, 1000);
    private String resultData;
    private String phone;
    private boolean successful;
    private String phone1;



    @Override
    protected int setLayoutId() {
        return R.layout.activity_forgot_pwd;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_back_img, R.id.get_sms, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.get_sms:
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
            case R.id.btn_login:
                register();
                break;
        }
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
            ToastUtil.showLong(this, "发送成功");
        } else if (resultData.equals("1")) {

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

            if (successful) {
                ActivityLauncherUtil.launcher(this, SetPasswordActivity.class);
            }
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
    } private void smsCode() {
        counter.start();
        getSms.setClickable(false);
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
}
