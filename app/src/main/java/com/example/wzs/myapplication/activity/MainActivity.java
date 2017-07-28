package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wzs.myapplication.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    public static BaseFragment currentFragment;
    public static final int HOME_TITLE = 1;
    public static final int OTHER_TITLE = 2;
    public static final int PERSON_OR_INTERTACT = 3;
    public static final int EDIT_TITLE = 4;
    public static final int RIGHT_TYPE = 5;
    @Bind(R.id.tab_title)
    TextView tabTitle;
    @Bind(R.id.person_sign)
    ImageView personSign;
    @Bind(R.id.base_action_bar)
    RelativeLayout baseActionBar;
    @Bind(R.id.mFram)
    FrameLayout mFram;
    @Bind(R.id.message)
    RadioButton message;
    @Bind(R.id.friend)
    RadioButton friend;
    @Bind(R.id.my)
    RadioButton my;
    @Bind(R.id.fragment_radio_group)
    RadioGroup fragmentRadioGroup;
    public FragmentManager supportFragmentManager;

    public void changeFragment(BaseFragment fragment, Bundle bundle, boolean isBack) {

        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        if (bundle != null)
            fragment.setParams(bundle);

        transaction.hide(currentFragment);
        if (isBack) {
            transaction.addToBackStack(null);
        } else {
            currentFragment = fragment;
        }
        if (!fragment.isAdded())

            transaction.add(R.id.mFram, fragment);

        transaction.show(fragment);

        transaction.commit();
    }

    @Override
    protected int setLayoutId() {
        HXApplication.context = this;
        return R.layout.activity_main;
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

    @OnClick({R.id.person_sign, R.id.message, R.id.friend, R.id.my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_sign:
                break;
            case R.id.message:
                break;
            case R.id.friend:
                break;
            case R.id.my:
                break;
        }
    }
}
