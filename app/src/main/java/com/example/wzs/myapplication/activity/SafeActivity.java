package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wzs.myapplication.R;
import com.example.wzs.myapplication.base.BaseActivity;
import com.zhy.android.percent.support.PercentRelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SafeActivity extends BaseActivity {


    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.change_title)
    TextView changeTitle;
    @Bind(R.id.base_action_bar)
    RelativeLayout baseActionBar;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.change_id)
    TextView changeId;
    @Bind(R.id.safe_id)
    PercentRelativeLayout safeId;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    @Bind(R.id.change_phone)
    TextView changePhone;
    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.change_ZM)
    TextView changeZM;
    @Bind(R.id.PWD)
    PercentRelativeLayout PWD;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_safe;
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


    @OnClick({R.id.title_back_img, R.id.PWD})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.PWD:
                break;
        }
    }
}
