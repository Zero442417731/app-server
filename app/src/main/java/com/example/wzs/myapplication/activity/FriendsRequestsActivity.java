package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wzs.myapplication.adapter.FriendsRequestAdapter;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.model.FriendsRequestsPush;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.example.wzs.myapplication.weight.LinearDividerItemDecoration;
import com.nonecity.R;

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
    FriendsRequestsPush pushben;


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
        adapter=new FriendsRequestAdapter(this);
        adapter.setYesorot(this);
        recyclviewFriends.setLayoutManager(new LinearLayoutManager(this));
        recyclviewFriends.setAdapter(adapter);
        recyclviewFriends.addItemDecoration(new LinearDividerItemDecoration(this,1));
        recyclviewFriends.setHasFixedSize(true);
      adapter.setOnItemClickListener(new FriendsRequestAdapter.OnItemClickListener() {
          @Override
          public void onItemClick(View view, int position) {
              Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_LONG).show();
              Bundle bundle = new Bundle();
              bundle.putString("remark",pushben.getBody().getRemark() );
              bundle.putString("headImgPath",pushben.getBody().getHeadImgPath());
              bundle.putString("signature",pushben.getBody().getSignature());
              bundle.putString("area",pushben.getBody().getArea());
              bundle.putString("nickname",pushben.getBody().getNickName());
              bundle.putString("friendid",pushben.getBody().getFriendId());
              ActivityLauncherUtil.launcher(getApplicationContext(),FriendRequestsActivity.class,bundle,"userInfo");
          }
      });
    }

    @Override
    public void yes(int pos) {
        Toast.makeText(getApplicationContext(),"点击了 yes",Toast.LENGTH_LONG).show();
    }

    @Override
    public void no(int pos) {
        Toast.makeText(getApplicationContext(),"点击了 no",Toast.LENGTH_LONG).show();
    }
}
