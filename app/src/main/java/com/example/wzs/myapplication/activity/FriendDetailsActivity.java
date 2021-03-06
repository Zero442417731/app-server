package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.model.UserSearch;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.nonecity.R;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendDetailsActivity extends BaseActivity {


    @Bind(R.id.user_bg)
    ImageView userBg;
    @Bind(R.id.user_head)
    CircleImageView userHead;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.user_id)
    TextView userId;
    @Bind(R.id.relativeLayout)
    LinearLayout relativeLayout;
    @Bind(R.id.user_sex)
    TextView userSex;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    @Bind(R.id.user_address)
    TextView userAddress;
    @Bind(R.id.ll_sex)
    LinearLayout llSex;
    @Bind(R.id.user_signature)
    TextView userSignature;
    @Bind(R.id.ll_signature)
    LinearLayout llSignature;
    @Bind(R.id.user_source)
    TextView userSource;
    @Bind(R.id.add_friend)
    Button addFriend;
    String friendUserId = null;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_friend_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Bundle mbundle = this.getIntent().getBundleExtra("userInfo");
        this.userName.setText(mbundle.getString("userName"));
        userId.setText(mbundle.getString("userId"));
        userSex.setText(mbundle.getString("userSex"));
        userAddress.setText(mbundle.getString("userAddress"));
        userSignature.setText(mbundle.getString("userSignature"));
        friendUserId = mbundle.getString("userId");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_friend)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_friend:
                Bundle bundle = new Bundle();
                bundle.putString("userID", friendUserId);
                ActivityLauncherUtil.launcher(FriendDetailsActivity.this, VerificationFriendsActivity.class, bundle, "userInfo");
                break;
        }

    }


}
