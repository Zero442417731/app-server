package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wzs.myapplication.adapter.FriendsRequestAdapter;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.AddFriendsResponce;
import com.example.wzs.myapplication.model.FriendsRequestsPush;
import com.example.wzs.myapplication.model.RequestList;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.example.wzs.myapplication.weight.LinearDividerItemDecoration;
import com.nonecity.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hxcs-04 on 2017/8/16.
 * 好友申请列表
 */

public class FriendsRequestsActivity extends BaseActivity implements FriendsRequestAdapter.YesOrNo {


    @Bind(R.id.recyclview_friends)
    RecyclerView recyclviewFriends;
    FriendsRequestAdapter adapter;
    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.change_title)
    TextView changeTitle;
    @Bind(R.id.base_action_bar)
    RelativeLayout baseActionBar;
    // FriendsRequestsPush pushben;
    private static String TAG = "FriendsRequestsActivity";
    private List<RequestList.BodyBean.ResultDataBean> resultDataBeen;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_requests;
    }

    @Override
    protected void initView() {
        changeTitle.setText("好友申请");
    }

    @Override
    protected void initData() {
        resultDataBeen = new ArrayList<RequestList.BodyBean.ResultDataBean>();
        sendMessage();
        recyclviewFriends.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclviewFriends.addItemDecoration(new LinearDividerItemDecoration(this, 1));
        recyclviewFriends.setHasFixedSize(true);
        adapter = new FriendsRequestAdapter(HXApplication.mContext, resultDataBeen);
        recyclviewFriends.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setYesorot(this);
        adapter.setOnItemClickListener(new FriendsRequestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("state", resultDataBeen.get(position).getState());
                bundle.putString("remark", resultDataBeen.get(position).getRemark());
                bundle.putString("area", resultDataBeen.get(position).getArea());
                bundle.putString("nikename", resultDataBeen.get(position).getNickName());
                bundle.putString("headImage", resultDataBeen.get(position).getHeadImgPath());
                bundle.putString("friendid", resultDataBeen.get(position).getFriendId());
                ActivityLauncherUtil.launcher(getApplicationContext(), FriendRequestsActivity.class, bundle, "userInfo");
            }
        });
    }

    @OnClick(R.id.title_back_img)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
        }
    }

    @Override
    public void yes(int pos) {
        /**
         * 同意添加为好友
         */
        HXApplication.retrofitUtils.postData(userAddFriendsOrRefuse(resultDataBeen.get(pos).getFriendId(), 1), new MyCallback<AddFriendsResponce>() {
            @Override
            public void onSuccess(AddFriendsResponce addFriendsResponce) {
                if (addFriendsResponce.getBody().isSuccessful()) {
                    switch (addFriendsResponce.getBody().getResultData()) {
                        /**
                         * 0 失败 1 成功
                         */
                        case 0:
                            Toast.makeText(getApplicationContext(), "添加好友成功！", Toast.LENGTH_LONG).show();
                            resultDataBeen.get(pos).setState(1);
                            adapter.notifyDataSetChanged();
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "添加好友失败！", Toast.LENGTH_LONG).show();
                            resultDataBeen.get(pos).setState(0);
                            adapter.notifyDataSetChanged();
                            break;

                        default:
                            break;
                    }
                }
            }

            @Override
            public void onError(String msg) {
                LogUtil.e(TAG, msg);
            }
        });
    }

    @Override
    public void no(int pos) {
        /**
         * 拒绝添加为好友
         */
        HXApplication.retrofitUtils.postData(userAddFriendsOrRefuse(resultDataBeen.get(pos).getFriendId(), 2), new MyCallback<AddFriendsResponce>() {
            @Override
            public void onSuccess(AddFriendsResponce addFriendsResponce) {
                if (addFriendsResponce.getBody().isSuccessful()) {
                    switch (addFriendsResponce.getBody().getResultData()) {
                        /**
                         * 0 失败 1 成功
                         */
                        case 0:
                            Toast.makeText(getApplicationContext(), "已拒绝！", Toast.LENGTH_LONG).show();
                            resultDataBeen.get(pos).setState(1);
                            adapter.notifyDataSetChanged();
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "拒绝好友失败！", Toast.LENGTH_LONG).show();
                            resultDataBeen.get(pos).setState(0);
                            adapter.notifyDataSetChanged();
                            break;

                        default:
                            break;
                    }
                }
            }

            @Override
            public void onError(String msg) {
                LogUtil.e(TAG, msg);
            }
        });
    }

    private void sendMessage() {
        HXApplication.retrofitUtils.postData(userSearch(), new MyCallback<RequestList>() {
            @Override
            public void onSuccess(RequestList requestList) {
                if (requestList.getBody().isSuccessful()) {
                    Log.d(TAG, "onSuccess: ");
                    List<RequestList.BodyBean.ResultDataBean> resultData = requestList.getBody().getResultData();
                    resultDataBeen.addAll(resultData);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String msg) {
                Log.d(TAG, "onError: ");
            }
        });
    }

    private String userSearch() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject.put("code", Constant.QQLB);
            jsonObject1.put("token", SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token"));
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }

    private String userAddFriendsOrRefuse(String friendid, int state) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject.put("code", Constant.HXCS_JC_YZTG);
            jsonObject1.put("token", SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token"));
            jsonObject1.put("friendid", friendid);
            jsonObject1.put("state", state);
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }
}
