package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wzs.myapplication.adapter.FriendsRequestAdapter;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.config.Constant;
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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hxcs-04 on 2017/8/16.
 * 好友申请
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
        // LogUtil.e("resultDataBeen-----",resultDataBeen.size()+"---"+resultDataBeen.get(1).getFriendId());
        recyclviewFriends.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new FriendsRequestAdapter(HXApplication.mContext, resultDataBeen);
        recyclviewFriends.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }


    @Override
    public void yes(int pos) {
        Toast.makeText(getApplicationContext(), "点击了 yes", Toast.LENGTH_LONG).show();
    }

    @Override
    public void no(int pos) {
        Toast.makeText(getApplicationContext(), "点击了 no", Toast.LENGTH_LONG).show();
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
}
