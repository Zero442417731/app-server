package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.UserChangeModel;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.example.wzs.myapplication.utils.ToastUtil;
import com.example.wzs.myapplication.weight.PasswordEditText;
import com.nonecity.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChangeSignatureActivity extends BaseActivity {


    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.change_title)
    TextView changeTitle;
    @Bind(R.id.base_action_bar)
    RelativeLayout baseActionBar;
    @Bind(R.id.ed_change_signature)
    PasswordEditText edChangeSignature;
    @Bind(R.id.confirm_change)
    Button confirmChange;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_signature;
    }

    @Override
    protected void initView() {
        changeTitle.setText("编辑签名");
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

    @OnClick(R.id.confirm_change)
    public void onViewClicked() {

        HXApplication.retrofitUtils.postData(setNameJson(), new MyCallback<UserChangeModel>() {
            @Override
            public void onSuccess(UserChangeModel userChangeModel) {
                SharedPreferencesUtil.setStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "userSignature", edChangeSignature.getText().toString().trim());
                ToastUtil.showToast("修改成功");
            }

            @Override
            public void onError(String msg) {

            }
        });

    }

    public String setNameJson() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject.put("code", "HXCS-JC-YHXG");
            jsonObject1.put("signature", edChangeSignature.getText().toString().trim());
            jsonObject1.put("token", SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token"));
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }
}
