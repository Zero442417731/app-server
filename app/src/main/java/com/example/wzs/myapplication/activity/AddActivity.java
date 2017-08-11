package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.UserSearch;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.nonecity.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddActivity extends BaseActivity implements SearchView.OnQueryTextListener {


    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.change_title)
    TextView changeTitle;
    @Bind(R.id.base_action_bar)
    RelativeLayout baseActionBar;
    @Bind(R.id.search_friend)
    SearchView searchFriend;
    @Bind(R.id.contact_add_voice)
    ImageView contactAddVoice;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.new_group)
    LinearLayout newGroup;
    @Bind(R.id.contact_friends)
    LinearLayout contactFriends;
    private String userCode;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        changeTitle.setText("添加好友");
        searchFriend.setOnQueryTextListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_back_img, R.id.contact_add_voice, R.id.new_group, R.id.contact_friends})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.contact_add_voice:
                break;
            case R.id.new_group:
                break;
            case R.id.contact_friends:
                break;
        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        HXApplication.retrofitUtils.postData(userSearch(query), new MyCallback<UserSearch>() {
            @Override
            public void onSuccess(UserSearch userSearch) {

                if (userSearch.getBody().isSuccessful()){

                    UserSearch.BodyBean.ResultDataBean userInfo = userSearch.getBody().getResultData();
                    Bundle bundle = new Bundle();
                    bundle.putString("userName",userInfo.getNickName());
                    bundle.putString("userId",userInfo.getUserCode());
                    bundle.putString("userSex",userInfo.getSex());
                    bundle.putString("userAddress",userInfo.getArea());
                    bundle.putString("userSignature",userInfo.getSignature());
                    ActivityLauncherUtil.launcher(AddActivity.this,FriendDetailsActivity.class,bundle,"userInfo");
                }

                userCode = userSearch.getBody().getResultData().getUserCode();

            }

            @Override
            public void onError(String msg) {

            }
        });
        Toast.makeText(this, userCode, Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private String userSearch(String friendId) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        try {
            jsonObject.put("code", "HXCS-JC-YHCZ");
            jsonObject1.put("token", SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token"));
            jsonObject1.put("code", friendId);
            jsonObject2.put("header",jsonObject);
            jsonObject2.put("body",jsonObject1);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonObject2.toString();
    }
}
