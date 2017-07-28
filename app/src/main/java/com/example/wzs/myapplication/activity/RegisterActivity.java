package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wzs.myapplication.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.GetSMS;
import com.example.wzs.myapplication.model.SendSMS;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.network.RetrofitUtils;
import com.example.wzs.myapplication.utils.JsonBinder;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.MD5Util;
import com.example.wzs.myapplication.utils.ToastUtil;
import com.zhy.android.percent.support.PercentLinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;

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
    @Bind(R.id.delete_phone)
    ImageView deletePhone;

    RetrofitUtils retrofitUtils;

    private TimeCounter counter = new TimeCounter(60 * 1000, 1000);

    private String phone;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        retrofitUtils = RetrofitUtils.getInstance();
    }

    @Override
    protected void initData() {

    }



    @OnClick({R.id.get_sms, R.id.btn_register, R.id.register_login, R.id.forgot_password,R.id.delete_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_sms:
                smsCode();
              retrofitUtils.postData(ReplyParams1(), new MyCallback<GetSMS>() {
                    @Override
                    public void onSuccess(GetSMS getSMS) {
                        LogUtil.w("qqqqq",getSMS.getBody().getResultData());
                    }

                    @Override
                    public void onError(String msg) {
                            LogUtil.e("aaaa",msg);
                    }
                });
                break;
            case R.id.btn_register:
                register();

                break;
            case R.id.register_login:
                break;
            case R.id.forgot_password:
                break;
            case R.id.delete_phone:
                break;
        }
    }
    private void smsCode() {
        counter.start();
        getSms.setClickable(false);
    }

    private String ReplyParams1(){
        phone = registerPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("请输入手机号");
        }
        // smsCode();
        SendSMS.BodyBean bodyBean = new SendSMS.BodyBean();
        bodyBean.setMobile(phone);
        bodyBean.setType("1");
        SendSMS.HeaderBean headerBean = new SendSMS.HeaderBean();
        headerBean.setCode("HXCS-JC-FSDX");
        SendSMS sendSMS = new SendSMS();
        sendSMS.setHeader(headerBean);
        sendSMS.setBody(bodyBean);
        JsonBinder jsonBinder = JsonBinder.buildNormalBinder();
        String toJson = jsonBinder.toJson(sendSMS);
        return toJson;
    }

    private void register() {
        String phone = registerPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("请输入手机号");
            return;
        }

        String sms = getSms.getText().toString().trim();
        if (TextUtils.isEmpty(sms)) {
            ToastUtil.showToast("请输入验证码");
            return;
        }

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
