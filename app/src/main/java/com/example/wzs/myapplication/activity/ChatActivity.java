package com.example.wzs.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.dbmanger.DbManager;
import com.example.wzs.myapplication.dbmanger.db_dao.FriendID;
import com.example.wzs.myapplication.event.MessageEvent;
import com.example.wzs.myapplication.model.friendMsg.DrawingDataBean;
import com.example.wzs.myapplication.model.friendMsg.HYXX;
import com.example.wzs.myapplication.utils.JsonBinder;
import com.example.wzs.myapplication.utils.List2Json;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.SDPackageUtil;
import com.example.wzs.myapplication.utils.ZipUtil;
import com.example.wzs.myapplication.weight.DrawView;
import com.nonecity.R;

import org.codehaus.jackson.map.ObjectMapper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity {


    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.change_title)
    TextView changeTitle;
    @Bind(R.id.textview)
    TextView textview;
    @Bind(R.id.more)
    ImageView more;
    @Bind(R.id.broad)
    TextView broad;
    @Bind(R.id.base_action_bar)
    RelativeLayout baseActionBar;
    @Bind(R.id.mFrame)
    FrameLayout mFrame;
    @Bind(R.id.news_key)
    ImageView newsKey;
    @Bind(R.id.mtext)
    EditText mtext;
    @Bind(R.id.brush)
    ImageView brush;
    @Bind(R.id.personMore)
    ImageView personMore;
    private String friendId;
    private String id;
    public String userID;
    private DrawView drawView;
    private DrawingDataBean drawingDataBean;


    protected static final String ID = null;

    @Override
    protected int setLayoutId() {
        EventBus.getDefault().register(this);
        return R.layout.activity_chat;
    }

    @Override
    protected void initView() {
        more.setVisibility(View.VISIBLE);
        broad.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initData() {
        Bundle userInfo = getIntent().getBundleExtra("userInfo");
        id = userInfo.getString("id");
        userID = id;
        friendId = userInfo.getString("friendId");
        String userCode = userInfo.getString("userCode");
        String mobilePhone = userInfo.getString("mobilePhone");
        String headImgPath = userInfo.getString("headImgPath");
        String nickName = userInfo.getString("nickName");
        String signature = userInfo.getString("signature");
        String sex = userInfo.getString("sex");
        changeTitle.setText(nickName);
        LogUtil.e("user", id + nickName + sex + signature);
        broadView();


    }


    private void broadView() {

        drawView = new DrawView(this, "11", id, SDPackageUtil.getScreenWidth(this), SDPackageUtil.getScreenHeight(this));
        drawView.setIsb(true);
        mFrame.addView(drawView);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void init() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Subscribe
    public void msg(MessageEvent<HYXX> messageEvent) {
        switch (messageEvent.getFriendUserId()) {




            case "c6212e4205fd4d23993d228dc75bc2d4":


                HYXX body = messageEvent.getMessageContent();

                String drawingData = body.getDrawingData();


                List<DrawingDataBean> list = List2Json.fromDrawStringZip(drawingData);


                drawView.setCanvasDate(list);
                LogUtil.e("接收到的消息-----大师兄------", messageEvent.getMessageContent().toString());


                break;
            case "30cb481d37c487a81fc73ec13b1beee":

                HYXX body1 = messageEvent.getMessageContent();

                String drawingData1 = body1.getDrawingData();


                List<DrawingDataBean> list2 = List2Json.fromDrawStringZip(drawingData1);

                drawView.setCanvasDate(list2);
                LogUtil.e("接收到的消息------二师兄-----", messageEvent.getMessageContent().toString());

                break;


        }
    }


    @OnClick({R.id.title_back_img, R.id.more, R.id.broad, R.id.news_key, R.id.brush, R.id.personMore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_img:
                finish();
                break;
            case R.id.more:
                break;
            case R.id.broad:
                break;
            case R.id.news_key:
                break;
            case R.id.brush:
                break;
            case R.id.personMore:
                break;
        }
    }
}
