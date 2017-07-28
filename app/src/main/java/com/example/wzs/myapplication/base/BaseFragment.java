package com.example.wzs.myapplication.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.example.wzs.myapplication.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * @AUTHOR HACKER
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    protected static Handler handler = new Handler();

    protected View contentView;

    protected Intent intent;

    protected Bundle bundle;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(setLayoutId(), container, false);
        ButterKnife.bind(this, contentView);
        initView(contentView);
        initData();
        return contentView;
    }

    protected abstract int setLayoutId();

    protected abstract void initView(View contentView);

    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = HXApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    public void setParams(Bundle bundle) {
        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public HXApplication getApp() {
        return (HXApplication) HXApplication.getInstance();
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


}

