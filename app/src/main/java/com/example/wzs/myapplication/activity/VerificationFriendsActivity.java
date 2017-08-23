package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.AddFriendsStatus;
import com.example.wzs.myapplication.network.MyCallback;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;
import com.example.wzs.myapplication.utils.ToastUtil;
import com.example.wzs.myapplication.weight.PasswordEditText;
import com.nonecity.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hxcs-04 on 2017/8/15.
 */

public class VerificationFriendsActivity extends BaseActivity {
    @Bind(R.id.addfried_remake)
    PasswordEditText remake;
    @Bind(R.id.btn_send_message)
    Button send_message;
    String add_friend_remake;
    String friendUserId = null;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_verificationfriends;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        Bundle mbundle = this.getIntent().getBundleExtra("userInfo");
        friendUserId = mbundle.getString("userID");
    }

    @OnClick(R.id.btn_send_message)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send_message:
             checkRemake();
                break;
                default:break;
        }
    }

    public void checkRemake() {
        add_friend_remake = remake.getText().toString();
        if (add_friend_remake.length() > 1) {
            senMessage(friendUserId,add_friend_remake);
        } else {
            Toast.makeText(getApplicationContext(), "随便说点什么吧", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void senMessage(String friendUserId,String add_friend_remake) {
       // add_friend_remake=remake.getText().toString();
        HXApplication.retrofitUtils.postData(userSearch(friendUserId, add_friend_remake), new MyCallback<AddFriendsStatus>() {
            @Override
            public void onSuccess(AddFriendsStatus addFriendsStatus) {
                Log.i(TAG, "onSuccess: "+addFriendsStatus.getBody().getResultData());
                if (addFriendsStatus.getBody().isSuccessful()) {
                    Log.i(TAG, "onSuccess: "+addFriendsStatus.getBody().getResultData());

                    String  resultID = addFriendsStatus.getBody().getResultData();
                    int resultid=Integer.parseInt(resultID);
                    Log.e(TAG, "onSuccess: " + addFriendsStatus.getBody().getErrorMsg());
                 //   Toast.makeText(getApplicationContext(),""+toastmsg,Toast.LENGTH_LONG).show();
                 //   ToastUtil.showToast(toastmsg);
                    switch (resultid) {
//                         "resultData" : "结果反馈。 0：添加失败；1：添加成功；2：好友之前已被添加，
//                          3：好友信息不存在，4: 好友验证信息已发送"
                        case 0://
                            ToastUtil.showLong(getApplicationContext(), "添加失败");
                            break;
                        case 2:
                            ToastUtil.showLong(getApplicationContext(), "好友之前已被添加");
                            break;
                        case 1:
                            ToastUtil.showLong(getApplicationContext(), "添加成功！");
                            break;
                        case 3:
                            ToastUtil.showLong(getApplicationContext(), "好友信息不存在");
                            break;
                        case 4:
                            ToastUtil.showLong(getApplicationContext(), "好友验证信息已发送");
                            break;
                    }
                }
            }

            @Override
            public void onError(String msg) {
                Log.e(TAG, "onError: "+msg );
            }
        });


    }

    private String userSearch(String friendId, String remake) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        try {
            jsonObject.put("code", Constant.HYTJ);
            jsonObject1.put("token", SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token"));
            jsonObject1.put("remark", remake);
            jsonObject1.put("friendUserId", friendId);
            jsonObject2.put("header", jsonObject);
            jsonObject2.put("body", jsonObject1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject2.toString();
    }
}
