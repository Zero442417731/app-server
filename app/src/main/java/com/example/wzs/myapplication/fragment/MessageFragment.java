package com.example.wzs.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.wzs.myapplication.adapter.SlideAdapter;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseFragment;
import com.example.wzs.myapplication.dbmanger.DbManager;
import com.example.wzs.myapplication.dbmanger.db_dao.UserInfoEntity;
import com.example.wzs.myapplication.event.EventId;
import com.example.wzs.myapplication.event.MessageEvent;
import com.example.wzs.myapplication.model.User;
import com.example.wzs.myapplication.model.XTJC;
import com.example.wzs.myapplication.utils.LogUtil;
import com.nonecity.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hxcs-02 on 2017/7/28.
 */

public class MessageFragment extends BaseFragment {


    @Bind(R.id.mListView)
    ListView mListView;
    private List<User> list;
    private SlideAdapter adapter;

    private DbManager dbManger;

    @Override
    protected int setLayoutId() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View contentView) {

    }

    @Override
    protected void initData() {
        dbManger = new DbManager();
        list = new ArrayList<>();
        List<UserInfoEntity> userInfoEntities = dbManger.selectFriendList();
        for (int i = 0; i < userInfoEntities.size(); i++) {
            UserInfoEntity resultDataBean = userInfoEntities.get(i);
            User user = new User();
            user.setNickName(resultDataBean.getNickName());
            list.add(user);
        }

        adapter = new SlideAdapter(HXApplication.mContext, list);
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void msg(MessageEvent messageEvent) {
        switch (messageEvent.getFriendUserId()) {
            case EventId.TEST:
                XTJC messageContent = (XTJC) messageEvent.getMessageContent();

                LogUtil.e("test----------", messageContent.getHeartState());
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

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
}
