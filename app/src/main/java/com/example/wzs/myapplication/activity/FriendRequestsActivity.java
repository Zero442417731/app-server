package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.AddFriendsResponce;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.example.wzs.myapplication.weight.RoundImageView;
import com.nonecity.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FriendRequestsActivity extends BaseActivity {
    @Bind(R.id.img_head)
    RoundImageView imgHead;
    @Bind(R.id.text_nike_name)
    TextView textNikeName;
    @Bind(R.id.text_area)
    TextView textArea;
    @Bind(R.id.img_no)
    ImageView imgNo;
    @Bind(R.id.item)
    LinearLayout item;
    @Bind(R.id.btn_add_friends_yes)
    Button btnAddFriendsYes;
    @Bind(R.id.btn_add_friends_no)
    Button btnAddFriendsNo;
    Bundle mbundle;
    @Bind(R.id.text_from)
    TextView textFrom;
    String friendid = null;
    String resultData;
    @Bind(R.id.line_yes_or_no)
    LinearLayout lineYesOrNo;
    @Bind(R.id.text_friends_status)
    TextView textFriendsStatus;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_friends_request_message;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mbundle = this.getIntent().getBundleExtra("userInfo");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        textArea.setText(mbundle.getString("area"));
        textNikeName.setText(mbundle.getString("nickname"));
        Glide.with(getApplicationContext()).load(mbundle.getString("headImgPath")).into(imgHead);
        friendid = mbundle.getString("friendid");
//textFrom.setText(mbundle.getString(""));
    }

    @OnClick({R.id.btn_add_friends_no, R.id.btn_add_friends_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_friends_no:
                sendMessage(2);
                break;
            case R.id.btn_add_friends_yes:
                sendMessage(1);
                break;
            default:
                break;
        }

    }

    private String userSearch(int state) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject.put("code", Constant.HXCS_JC_YZTG);
            jsonObject1.put("token", SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token"));
            jsonObject.put("friendid", friendid);
            jsonObject1.put("state", state);
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }

    public void sendMessage(int state) {
        HXApplication.retrofitUtils.postData(userSearch(state), new MyCallback<AddFriendsResponce>() {
            @Override
            public void onSuccess(AddFriendsResponce addFriendsResponce) {
                if (addFriendsResponce.getBody().isSuccessful()) {
                    resultData = addFriendsResponce.getBody().getResultData();
                    if (resultData.equals(1)) {
                        Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_LONG).show();
                        lineYesOrNo.setVisibility(View.GONE);
                        textFriendsStatus.setText(View.VISIBLE);
                        if (state == 1) {
                            textFriendsStatus.setText("已同意");
                        } else {
                            textFriendsStatus.setText("已拒绝");
                        }


                    } else {
                        Toast.makeText(getApplicationContext(), "" + addFriendsResponce.getBody().getErrorMsg(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
