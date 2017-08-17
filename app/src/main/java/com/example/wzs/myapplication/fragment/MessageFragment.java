package com.example.wzs.myapplication.fragment;

import android.support.annotation.MainThread;
import android.view.View;
import android.widget.TextView;

import com.example.wzs.myapplication.event.EventId;
import com.example.wzs.myapplication.event.MessageEvent;
import com.example.wzs.myapplication.model.XTJC;
import com.example.wzs.myapplication.utils.LogUtil;
import com.nonecity.R;
import com.example.wzs.myapplication.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by hxcs-02 on 2017/7/28.
 */

public class MessageFragment extends BaseFragment {

    private TextView textView;

    @Override
    protected int setLayoutId() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View contentView) {
        textView = contentView.findViewById(R.id.mtest);
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void msg(MessageEvent messageEvent) {
        switch (messageEvent.getFriendUserId()) {
            case EventId.TEST:
                XTJC messageContent = (XTJC) messageEvent.getMessageContent();
                textView.setVisibility(View.GONE);
                LogUtil.e("test----------", messageContent.getHeartState());
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
