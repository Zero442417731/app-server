package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wzs.myapplication.R;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.change_title)
    TextView changeTitle;
    @Bind(R.id.base_action_bar)
    RelativeLayout baseActionBar;
    @Bind(R.id.ll_safe)
    LinearLayout llSafe;
    @Bind(R.id.tongzhi)
    LinearLayout tongzhi;
    @Bind(R.id.yinsi)
    LinearLayout yinsi;
    LinearLayout clear;
    @Bind(R.id.help)
    LinearLayout help;
    @Bind(R.id.about)
    LinearLayout about;
    @Bind(R.id.setting_linear)
    LinearLayout settingLinear;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
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

    @OnClick({R.id.title_back_img, R.id.ll_safe, R.id.tongzhi, R.id.yinsi, R.id.help, R.id.about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.ll_safe:
                ActivityLauncherUtil.launcher(this,SafeActivity.class);
                break;
            case R.id.tongzhi:

                break;
            case R.id.yinsi:
                break;
            case R.id.help:
                break;
            case R.id.about:
                break;
        }
    }
}
