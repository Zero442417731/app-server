package com.example.wzs.myapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.dbmanger.DbManager;
import com.example.wzs.myapplication.dbmanger.db_dao.FriendID;
import com.example.wzs.myapplication.dbmanger.db_dao.UserInfoEntity;
import com.example.wzs.myapplication.event.EventId;
import com.example.wzs.myapplication.event.MessageEvent;

import com.example.wzs.myapplication.model.FriendList;
import com.example.wzs.myapplication.model.FriendsRefresh;
import com.example.wzs.myapplication.model.UserInfo;
import com.example.wzs.myapplication.model.friendMsg.YZXX;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.nonecity.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseFragment;
import com.example.wzs.myapplication.model.User;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.weight.ChineseToEnglish;
import com.example.wzs.myapplication.weight.CompareSort;
import com.example.wzs.myapplication.weight.SideBarView;
import com.example.wzs.myapplication.adapter.UserAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private ArrayList<User> userArrayList;


    private DbManager dbManger;
    private List<UserInfoEntity> userInfoEntities;


    @Override
    protected int setLayoutId() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_friend;
    }

    @Override
    protected void initView(View contentView) {
    }

    @Override
    protected void initData() {
        //初始化联系人列表集合
        userArrayList = new ArrayList<>();
        //初始化数据库
        dbManger = new DbManager();
        getFriend();
        addData();
        init();
        mListview.setOnScrollListener(this);
    }

    private void addData() {

        userInfoEntities = dbManger.selectFriendList();

    }

    /**
     * eventBus 传递数据
     * @param messageEvent
     * @param <T>
     */
    @Subscribe
    public <T> void userAdd(MessageEvent<T> messageEvent) {
        switch (messageEvent.getFriendUserId()) {
            case EventId.USER_TS:
                YZXX messageContent = (YZXX) messageEvent.getMessageContent();
                String friendId = messageContent.getFriendId();
                LogUtil.e("eventbus", "userLogin: " + friendId);
                if (messageContent.getFriendId() != null) {
                    rel_new_friends.setVisibility(View.VISIBLE);
                    new QBadgeView(getContext()).bindTarget(not_read_number).setBadgeNumber(1).setBadgeBackgroundColor(0xffffeb3b).setBadgeTextColor(0xff000000).stroke(0xff000000, 1, true);
                }
                break;
            case EventId.FRIENDSREFRESH:
                //好友列表刷新通知
                FriendsRefresh friendsRefresh = (FriendsRefresh) messageEvent.getMessageContent();
                LogUtil.e("好友列表刷新通知-------",friendsRefresh.getState());
                getFriend();
                break;
        }

    }

    /**
     * 数据初始化
     */
    private void init() {
        //  rel_new_friends.setVisibility(View.GONE);
        //排序
        Collections.sort(userArrayList, new CompareSort());
        //设置数据
        mAdapter = new UserAdapter(HXApplication.mContext, userArrayList);

        mListview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

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
        if (userArrayList.size() > 0) {
            sideBarView.setTouchIndex(userArrayList.get(firstVisibleItem).getHeaderWord());
            LogUtil.w("--------", userArrayList.get(firstVisibleItem).getHeaderWord() + "--------");
        }
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

    /**
     * 获取好友列表
     */
    private void getFriend() {
        HXApplication.retrofitUtils.postData(getFriendList(), new MyCallback<FriendList>() {
            @Override
            public void onSuccess(FriendList friendList) {
                friendList.getBody().getResultData();
                List<FriendList.BodyBean.ResultDataBean> resultData = friendList.getBody().getResultData();
                refreshList(resultData);
                LogUtil.e("resultData----", resultData.size() + "");
            }
            @Override
            public void onError(String msg) {

            }
        });
    }

    /**
     * 好友列表参数怕拼接
     * @return
     */
    public String getFriendList() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject.put("code", "HXCS-JC-HYLB");
            jsonObject1.put("token", SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token"));
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }

    /**
     * 刷新好友列表
     * @param resultData 返回好有数目
     */
    private void refreshList(List<FriendList.BodyBean.ResultDataBean> resultData) {
        for (int i = 0; i < resultData.size(); i++) {
            FriendList.BodyBean.ResultDataBean resultDataBean = resultData.get(i);
            User user = new User();
            user.setName(resultDataBean.getNickName());
            user.setFriendId(resultDataBean.getFriendId());
            user.setSex(resultDataBean.getSex());
            user.setSignature(resultDataBean.getSignature());
            user.setHeadImgPath(resultDataBean.getHeadImgPath());
            user.setId(resultDataBean.getId());
            user.setMobilePhone(resultDataBean.getMobilePhone());
            user.setUserCode(resultDataBean.getUserCode());
            LogUtil.e("user_name-----", user.getName());
            user.setArea(resultDataBean.getArea());
            String firstSpell = ChineseToEnglish.getFirstSpell(resultDataBean.getNickName());
            if (firstSpell.length() > 0) {
                String substring = firstSpell.substring(0, 1).toUpperCase();
                user.setHeaderWord(substring);

                //数据库插数据
                UserInfoEntity userInfoEntity = new UserInfoEntity(null, resultDataBean.getId(),
                        resultDataBean.getFriendId(), resultDataBean.getUserCode(),
                        resultDataBean.getMobilePhone(), resultDataBean.getHeadImgPath(),
                        resultDataBean.getNickName(), resultDataBean.getSignature(),
                        resultDataBean.getSex(), resultDataBean.getArea(), resultDataBean.getOnLine());
                dbManger.deleteAll(UserInfoEntity.class);
                dbManger.insertData(userInfoEntity);
                if (substring.matches("[A-Z]")) {
                    user.setLetter(substring);
                } else {
                    user.setLetter("#");
                }
                //联系人列表
                userArrayList.add(user);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
