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
import com.example.wzs.myapplication.model.RequestList;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.LogUtil;
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
    @Bind(R.id.text_remake_info)
    TextView textRemarkInfo;
    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.change_title)
    TextView changeTitle;
    private RequestList.BodyBean resultDataBean;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_friends_request_message;
    }

    @Override
    protected void initView() {
        changeTitle.setText("好友申请");
    }

    @Override
    protected void initData() {
        mbundle = this.getIntent().getBundleExtra("userInfo");
        friendid = mbundle.getString("friendid");
        getMessgae();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_add_friends_no, R.id.btn_add_friends_yes, R.id.title_back_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_friends_no:
                Toast.makeText(getApplicationContext(), "1234", Toast.LENGTH_LONG).show();
                sendMessage(2);
                break;
            case R.id.btn_add_friends_yes:
                sendMessage(1);
                break;
            case R.id.title_back_img:
                finish();
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
            jsonObject1.put("friendId", friendid);
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
                LogUtil.e(TAG, addFriendsResponce.getBody().getErrorMsg());
                if (addFriendsResponce.getBody().isSuccessful()) {
                    switch (addFriendsResponce.getBody().getResultData()) {
                        case 1:
                            textFriendsStatus.setVisibility(View.VISIBLE);
                            btnAddFriendsNo.setVisibility(View.GONE);
                            btnAddFriendsYes.setVisibility(View.GONE);
                            switch (state) {
                                case 1://添加好友成功同意
                                    textFriendsStatus.setText("已同意");
                                    break;
                                case 2:
                                    textFriendsStatus.setText("已拒绝添加好友");
                                    break;
                            }
                            // Toast.makeText(getApplicationContext(),"已添加成功",Toast.LENGTH_LONG).show();
                            break;
                        case 0:
                            textFriendsStatus.setVisibility(View.VISIBLE);
                            btnAddFriendsNo.setVisibility(View.GONE);
                            btnAddFriendsYes.setVisibility(View.GONE);
                            switch (state) {
                                case 1:
                                    textFriendsStatus.setText("添加好友失败");
                                    break;
                                case 2:
                                    textFriendsStatus.setText("拒绝添加好友失败");
                                    break;
                                default:
                                    break;
                            }

                            //     Toast.makeText(getApplicationContext(),"已添加成功",Toast.LENGTH_LONG).show();
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    private void getMessgae() {
        //  resultDataBean = new RequestList.BodyBean();
        // LogUtil.e(TAG,""+resultDataBean.getResultData().size());
        Glide.with(getApplicationContext()).load(mbundle.getString("headImage")).into(imgHead);
        textArea.setText(mbundle.getString("area"));
        textFrom.setText("" + "通过ID查找");
        textNikeName.setText(mbundle.getString("nikename"));
        textRemarkInfo.setText(mbundle.getString("remark"));
        switch (mbundle.getInt("state")) {
            case 0:
                textFriendsStatus.setVisibility(View.GONE);
                btnAddFriendsYes.setVisibility(View.VISIBLE);
                btnAddFriendsNo.setVisibility(View.VISIBLE);
                break;
            case 1:
                //验证已通过
                btnAddFriendsNo.setVisibility(View.GONE);
                btnAddFriendsYes.setVisibility(View.GONE);

                textFriendsStatus.setVisibility(View.VISIBLE);
                textFriendsStatus.setText("已通过");
                break;

        }
    }
}
