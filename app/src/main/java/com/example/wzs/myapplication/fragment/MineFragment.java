package com.example.wzs.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nonecity.R;
import com.example.wzs.myapplication.activity.PersonDataActivity;
import com.example.wzs.myapplication.base.BaseFragment;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.lang.reflect.Field;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hxcs-02 on 2017/7/28.
 */

public class MineFragment extends BaseFragment {
    @Bind(R.id.mine_head)
    CircleImageView mineHead;
    @Bind(R.id.mine_name)
    TextView mineName;
    @Bind(R.id.mine_signature)
    TextView mineSignature;
    @Bind(R.id.mine_bg)
    PercentRelativeLayout mineBg;
    @Bind(R.id.person_data)
    PercentRelativeLayout personData;
    @Bind(R.id.person_board)
    PercentRelativeLayout personBoard;
    @Bind(R.id.person_collection)
    PercentRelativeLayout personCollection;
    @Bind(R.id.person_setting)
    PercentRelativeLayout personSetting;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View contentView) {

    }

    @Override
    protected void initData() {

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

    @OnClick({R.id.mine_head, R.id.mine_name, R.id.mine_signature, R.id.person_data, R.id.person_board, R.id.person_collection, R.id.person_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_head:
                break;
            case R.id.mine_name:
                break;
            case R.id.mine_signature:
                break;
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
    @Override
    public void onDetach(){
        super.onDetach();
        try {
            Field childFragmentManager =Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this,null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
