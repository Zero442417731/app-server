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


import com.example.wzs.myapplication.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.network.ApiWrapper;
import com.example.wzs.myapplication.utils.ActivityManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;


public abstract class BaseActivity extends FragmentActivity {
    public static String TAG = "BaseActivity";

    protected static Handler handler = new Handler();

    protected Context mContext;

    protected Intent intent;

    protected SystemBarTintManager tintManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

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
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        ActivityManagerUtil.create().finishActivity(this);
        System.gc();

        ButterKnife.unbind(this);
        RefWatcher refWatcher = HXApplication.getRefWatcher(this);
        refWatcher.watch(this);

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
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.app_basic));
        }
    }

    public HXApplication getApp() {
        return (HXApplication) getApplication();
    }

    private WeakReference<Dialog> netDialog;

    public void showNetDialog() {
        if (netDialog == null || netDialog.get() == null) {
            Dialog dialog = new Dialog(mContext, R.style.NormalDialog);
            RelativeLayout layout = (RelativeLayout) View.inflate(mContext, R.layout.item_progress_dialog, null);
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

    public ApiWrapper getApiWrapper(boolean show) {
        if (show) {
            showNetDialog();
        }
        return ApiWrapper.getInstance();
    }
}
