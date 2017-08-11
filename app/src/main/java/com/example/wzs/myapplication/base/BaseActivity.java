package com.example.wzs.myapplication.base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;


import com.nonecity.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.utils.ActivityManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;


public abstract class BaseActivity extends FragmentActivity {
    public static String TAG = "BaseActivity";

    protected static Handler handler = new Handler();



    protected Intent intent;

    protected SystemBarTintManager tintManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HXApplication.context = this;
        HXApplication.mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        TAG = this.getClass().getSimpleName();
        ActivityManagerUtil.create().addActivity(this);
        this.setContentView(this.setLayoutId());
        if (isInitWindow()) {
            initWindow();
        }
         ButterKnife.bind(this);
        initTitleBar();
        initView();
        initData();
    }

    public Boolean isInitWindow() {
        return true;
    }

    protected abstract int setLayoutId();

    protected void initTitleBar() {
    }

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
        HXApplication.context = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        ActivityManagerUtil.create().finishActivity(this);
        System.gc();
        HXApplication.getRefWatcher().watch(this);
        ButterKnife.unbind(this);

        super.onDestroy();
    }

    @Override
    public void finish() {
        System.gc();
        super.finish();
    }

    @TargetApi(19)
    protected void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorPrimaryDark));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    public HXApplication getApp() {
        return (HXApplication) getApplication();
    }

    private WeakReference<Dialog> netDialog;

    public void showNetDialog() {
        if (netDialog == null || netDialog.get() == null) {
            Dialog dialog = new Dialog(HXApplication.context, R.style.NormalDialog);
            RelativeLayout layout = (RelativeLayout) View.inflate(HXApplication.context, R.layout.item_progress_dialog, null);
            dialog.setContentView(layout);
            dialog.show();
            netDialog = new WeakReference<Dialog>(dialog);
        } else {
            if (!netDialog.get().isShowing()) {
                netDialog.get().show();
            }
        }
    }

    public void closeNetDialog() {
        if (netDialog != null && netDialog.get() != null && netDialog.get().isShowing()) {
            netDialog.get().dismiss();
        }
    }



}
