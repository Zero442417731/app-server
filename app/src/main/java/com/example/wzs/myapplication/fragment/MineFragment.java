package com.example.wzs.myapplication.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.config.Constant;

import com.example.wzs.myapplication.model.UserDetails;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.GlideImageLoaderUtil;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.nonecity.R;
import com.example.wzs.myapplication.activity.PersonDataActivity;
import com.example.wzs.myapplication.base.BaseFragment;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.zhy.android.percent.support.PercentRelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.wzs.myapplication.utils.GlideImageLoaderUtil.*;


public class MineFragment extends BaseFragment {

    @Bind(R.id.mine_name)
    TextView mineName;
    @Bind(R.id.mine_signature)
    TextView mineSignature;
    @Bind(R.id.mine_bg)
    ImageView mineBg;
    @Bind(R.id.person_data)
    PercentRelativeLayout personData;
    @Bind(R.id.person_board)
    PercentRelativeLayout personBoard;
    @Bind(R.id.person_collection)
    PercentRelativeLayout personCollection;
    @Bind(R.id.person_setting)
    PercentRelativeLayout personSetting;
    private CircleImageView userHead;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View contentView) {
        userHead = (CircleImageView) contentView.findViewById(R.id.mine_head);
    }

    @Override
    protected void initData() {
        String userHead = SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "userHead");
        LogUtil.e("userhead", userHead);
        GlideImageLoaderUtil.displayImageInFragment(this, userHead, this.userHead);
        GlideImageLoaderUtil.displayImageInFragment(this, userHead, mineBg);
        mineName.setText(SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "nickName"));
        mineSignature.setText(SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "userSignature"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.person_data, R.id.person_board, R.id.person_collection, R.id.person_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.person_data:
                ActivityLauncherUtil.launcher(getContext(), PersonDataActivity.class);
                break;
            case R.id.person_board:
                break;
            case R.id.person_collection:
                break;
            case R.id.person_setting:
                break;
        }
    }
}
