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
import com.example.wzs.myapplication.model.FriendsRequestsPush;
import com.example.wzs.myapplication.model.RequestList;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.example.wzs.myapplication.weight.LinearDividerItemDecoration;
import com.nonecity.R;

import org.json.JSONException;
import org.json.JSONObject;

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
    private RequestList listBean;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_requests;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        changeTitle.setText("好友申请");
        listBean = new RequestList();
        sendMessage();
        try {
            Thread.sleep(1000);
            adapter = new FriendsRequestAdapter(getApplicationContext(), listBean);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        recyclviewFriends.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclviewFriends.setAdapter(adapter);
        // listBean = new RequestList();

        //  adapter.setYesorot(this);

//        recyclviewFriends.addItemDecoration(new LinearDividerItemDecoration(this, 1));
//        recyclviewFriends.setHasFixedSize(true);
//        adapter.setOnItemClickListener(new FriendsRequestAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
////              Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_LONG).show();
//                Bundle bundle = new Bundle();
////              bundle.putString("remark",pushben.getBody().getRemark() );
////              bundle.putString("headImgPath",pushben.getBody().getHeadImgPath());
////              bundle.putString("signature",pushben.getBody().getSignature());
////              bundle.putString("area",pushben.getBody().getArea());
////              bundle.putString("nickname",pushben.getBody().getNickName());
////              bundle.putString("friendid",pushben.getBody().getFriendId());
//                ActivityLauncherUtil.launcher(getApplicationContext(), FriendRequestsActivity.class, bundle, "userInfo");
//            }
//        });
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
                    requestList.getBody().getResultData();
                } else {

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
            jsonObject.put("code", Constant.HXCS_JC_QQLB);
            jsonObject1.put("token", SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token"));
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }
}
