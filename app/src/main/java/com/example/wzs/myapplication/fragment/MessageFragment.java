package com.example.wzs.myapplication.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.wzs.myapplication.swipeMenuListView.SwipeMenu;
import com.example.wzs.myapplication.swipeMenuListView.SwipeMenuCreator;
import com.example.wzs.myapplication.swipeMenuListView.SwipeMenuItem;
import com.example.wzs.myapplication.swipeMenuListView.SwipeMenuListView;
import com.example.wzs.myapplication.utils.DensityUtils;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.ToastUtil;
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
    SwipeMenuListView mListView;
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

        //1.item显示按钮构造器  (此处只设置两个按钮，当然可以设置多个)
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                createMenu(menu);
            }
        };
        //2.set 构造器
        mListView.setMenuCreator(creator);
        //3.set  按钮的监听事件
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        //置顶
                        User user = list.get(position);
                        list.remove(position);
                        list.add(0, user);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        ToastUtil.showToast("免扰");
                        break;
                    case 2:
                        //删除
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }

            }
        });

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

    private void createMenu(SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(HXApplication.mContext);
        item1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        item1.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        item1.setIcon(R.drawable.list_slide_top);
        menu.addMenuItem(item1);
        SwipeMenuItem item2 = new SwipeMenuItem(HXApplication.mContext);
        item2.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        item2.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        item2.setIcon(R.drawable.list_slide_disturb);
        menu.addMenuItem(item2);
        SwipeMenuItem item3 = new SwipeMenuItem(HXApplication.mContext);
        item3.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        item3.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        item3.setIcon(R.drawable.list_slide_del);
        menu.addMenuItem(item3);

    }
}
