package com.example.wzs.myapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wzs.myapplication.activity.FriendsRequestsActivity;
import com.example.wzs.myapplication.event.EventId;
import com.example.wzs.myapplication.event.MessageEvent;
import com.example.wzs.myapplication.model.HYXX;
import com.example.wzs.myapplication.model.YZXX;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.nonecity.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseFragment;
import com.example.wzs.myapplication.model.User;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.weight.ChineseToEnglish;
import com.example.wzs.myapplication.weight.CompareSort;
import com.example.wzs.myapplication.weight.SideBarView;
import com.example.wzs.myapplication.adapter.UserAdapter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by hxcs-02 on 2017/7/28.
 */

public class FriendFragment extends BaseFragment implements SideBarView.LetterSelectListener, AbsListView.OnScrollListener {
    @Bind(R.id.listview)
    ListView mListview;
    @Bind(R.id.sidebarview)
    SideBarView sideBarView;
    @Bind(R.id.tip)
    TextView mTip;
    @Bind(R.id.new_friend)
    RelativeLayout rel_new_friends;
    @Bind(R.id.text_add_friends_not_read_number)
    TextView not_read_number;
    private UserAdapter mAdapter;
    private ArrayList<User> userArrayList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_friend;
    }

    @Override
    protected void initView(View contentView) {

    }

    @Override
    protected void initData() {
        addData();
        mListview.setOnScrollListener(this);
        init();
    }

    private void addData() {
        String[] contactsArray = getResources().getStringArray(R.array.data);
        // String[] headArray = getResources().getStringArray(R.array.head);

        //模拟添加数据到Arraylist
        int length = contactsArray.length;
        for (int i = 0; i < length; i++) {
            User user = new User();
            user.setName(contactsArray[i]);
            String firstSpell = ChineseToEnglish.getFirstSpell(contactsArray[i]);
            String substring = firstSpell.substring(0, 1).toUpperCase();
            user.setHeaderWord(substring);
            if (substring.matches("[A-Z]")) {
                user.setLetter(substring);
            } else {
                user.setLetter("#");
            }
            userArrayList.add(user);
        }
/*

        for (int i = 0; i < headArray.length; i++) {
            User user = new User();
            user.setName(headArray[i]);
            user.setLetter("@");
            userArrayList.add(user);
        }
*/

    }


    @Subscribe
    public void userLogin(MessageEvent messageEvent) {
        switch (messageEvent.getFriendUserId()) {
            case EventId.USER_TS:
                YZXX messageContent = (YZXX) messageEvent.getMessageContent();
                String friendId = messageContent.getFriendId();
                Log.i("eventbus", "userLogin: " + friendId);
                if (messageContent.getFriendId() != null) {
                    rel_new_friends.setVisibility(View.VISIBLE);
                    new QBadgeView(getContext()).bindTarget(not_read_number).setBadgeNumber(1).setBadgeBackgroundColor(0xffffeb3b).setBadgeTextColor(0xff000000).stroke(0xff000000, 1, true);
                }
                break;
        }


    }

    private void init() {
        //  rel_new_friends.setVisibility(View.GONE);
        //排序
        Collections.sort(userArrayList, new CompareSort());
        //设置数据
        mAdapter = new UserAdapter(HXApplication.mContext);
        mAdapter.setData(userArrayList);
        mListview.setAdapter(mAdapter);

        //设置回调
        sideBarView.setOnLetterSelectListen(this);
        rel_new_friends.setOnClickListener(RelOnClickListener);
    }


    @Override
    public void onLetterSelected(String letter) {
        setListviewPosition(letter);
        mTip.setText(letter);
        mTip.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLetterChanged(String letter) {
        setListviewPosition(letter);
        mTip.setText(letter);
    }

    @Override
    public void onLetterReleased(String letter) {
        mTip.setVisibility(View.GONE);
    }

    private void setListviewPosition(String letter) {
        int firstLetterPosition = mAdapter.getFirstLetterPosition(letter);
        if (firstLetterPosition != -1) {
            mListview.setSelection(firstLetterPosition);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        LogUtil.e("------------------", scrollState + "");
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //当滑动列表的时候，更新右侧字母列表的选中状态
        sideBarView.setTouchIndex(userArrayList.get(firstVisibleItem).getHeaderWord());
        LogUtil.w("--------", userArrayList.get(firstVisibleItem).getHeaderWord() + "--------");
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

    private View.OnClickListener RelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivityLauncherUtil.launcher(getContext(), FriendsRequestsActivity.class);
        }
    };
}
