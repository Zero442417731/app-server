package com.example.wzs.myapplication.activity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.nonecity.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.base.BaseFragment;
import com.example.wzs.myapplication.config.Variable;
import com.example.wzs.myapplication.fragment.FriendFragment;
import com.example.wzs.myapplication.fragment.MessageFragment;
import com.example.wzs.myapplication.fragment.MineFragment;
import com.example.wzs.myapplication.utils.DoubleClickExitUtil;
import com.example.wzs.myapplication.utils.FragmentBuilder;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    public static final int LOCATION_PERMISSION = 10000;
    public static final int STORAGE_PERMISSION = 20000;
    public static final int CAMERA_PERMISSION = 30000;
    public static final int RECORD_AUDIO = 40000;
    public static final int READ_PHONE_STATE = 40000;

    public static final int REQUEST_VIDEO = 50000;
    public static BaseFragment currentFragment;
    public static final int HOME_TITLE = 1;
    public static final int OTHER_TITLE = 2;
    public static final int PERSON_OR_INTERTACT = 3;
    public static final int EDIT_TITLE = 4;
    public static final int RIGHT_TYPE = 5;
    @Bind(R.id.tab_title)
    TextView tabTitle;
    @Bind(R.id.person_sign)
    ImageView personSign;
    @Bind(R.id.base_action_bar)
    RelativeLayout baseActionBar;
    @Bind(R.id.mFram)
    FrameLayout mFram;
    @Bind(R.id.message)
    RadioButton message;
    @Bind(R.id.friend)
    RadioButton friend;
    @Bind(R.id.my)
    RadioButton my;
    @Bind(R.id.fragment_radio_group)
    RadioGroup fragmentRadioGroup;
    public FragmentManager supportFragmentManager;
    private DoubleClickExitUtil doubleClickExitUtil;

    private LocationClient locationClient;
    public void changeFragment(BaseFragment fragment, Bundle bundle, boolean isBack) {

        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        if (bundle != null)
            fragment.setParams(bundle);

        transaction.hide(currentFragment);
        if (isBack) {
            transaction.addToBackStack(null);
        } else {
            currentFragment = fragment;
        }
        if (!fragment.isAdded())

            transaction.add(R.id.mFram, fragment);

        transaction.show(fragment);

        transaction.commit();
    }

    @Override
    protected int setLayoutId() {
        HXApplication.context = this;
        return R.layout.activity_main;

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        checkPermissions();
         FragmentBuilder.changeFragment(MessageFragment.class,R.id.mFram,true,null,false);
        doubleClickExitUtil = new DoubleClickExitUtil();
        startLocation();
    }

    @OnClick({R.id.person_sign, R.id.message, R.id.friend, R.id.my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.person_sign:
                break;
            case R.id.message:
                FragmentBuilder.changeFragment(MessageFragment.class,R.id.mFram,true,null,false);
                baseActionBar.setVisibility(View.VISIBLE);
                tabTitle.setText("消息");
                message.setTextColor(Color.parseColor("#ff00ff"));
                break;
            case R.id.friend:
                FragmentBuilder.changeFragment(FriendFragment.class,R.id.mFram,true,null,false);
                baseActionBar.setVisibility(View.VISIBLE);
                tabTitle.setText("好友列表");
                message.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.my:
                FragmentBuilder.changeFragment(MineFragment.class,R.id.mFram,true,null,true);
                baseActionBar.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onPermissionsGranted(List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(List<String> perms) {

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return doubleClickExitUtil.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
    private void checkPermissions() {
        requestLocationPermission();
        requestCameraPermission();
        requestRecodeAudioPermission();
        requestExternalStoragePermission();
        requestReadPhoneStatePermission();
    }

    @AfterPermissionGranted(LOCATION_PERMISSION)
    private void requestLocationPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) {

        } else {
            EasyPermissions.requestPermissions(this, "", LOCATION_PERMISSION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
    }

    @AfterPermissionGranted(CAMERA_PERMISSION)
    public void requestCameraPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {

        } else {
            EasyPermissions.requestPermissions(this, "", CAMERA_PERMISSION, Manifest.permission.CAMERA, Manifest.permission.CAMERA);
        }
    }

    @AfterPermissionGranted(RECORD_AUDIO)
    public void requestRecodeAudioPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.RECORD_AUDIO)) {

        } else {
            EasyPermissions.requestPermissions(this, "", CAMERA_PERMISSION, Manifest.permission.RECORD_AUDIO, Manifest.permission.RECORD_AUDIO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(STORAGE_PERMISSION)
    public void requestExternalStoragePermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

        } else {
            EasyPermissions.requestPermissions(this, "", STORAGE_PERMISSION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }
    public void startLocation() {
        if (locationClient == null) {
            locationClient = new LocationClient(this);
            LocationClientOption option = new LocationClientOption();
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            option.setIsNeedAddress(true);
            option.setOpenGps(true);
            option.setCoorType("gcj02");
            locationClient.setLocOption(option);
            locationClient.registerLocationListener(new BDLocationListener() {
                @Override
                public void onReceiveLocation(BDLocation location) {
                    if (location == null) {
                        return;
                    }
                    Variable.location = location;
                    //获取定位结果
                    StringBuffer sb = new StringBuffer(256);
                    sb.append("time : ");
                    sb.append(location.getTime());    //获取定位时间
                    sb.append("\nerror code : ");
                    sb.append(location.getLocType());    //获取类型类型
                    sb.append("\nlatitude : ");
                    sb.append(location.getLatitude());    //获取纬度信息

                    sb.append("\nlontitude : ");
                    sb.append(location.getLongitude());    //获取经度信息

                    sb.append("\nradius : ");
                    sb.append(location.getRadius());    //获取定位精准度

                    LogUtil.w("aaaaa",sb.toString());

                    ToastUtil.showLong(getApplicationContext(),"定位成功");

                    if (locationClient != null && locationClient.isStarted()) {
                        locationClient.stop();
                        locationClient = null;
                    }
                }

                @Override
                public void onConnectHotSpotMessage(String s, int i) {

                }
            });
        }

        if (!locationClient.isStarted()) {
            locationClient.start();
            locationClient.requestLocation();
        }
    }

    @AfterPermissionGranted(READ_PHONE_STATE)

    public void requestReadPhoneStatePermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_PHONE_STATE)) {

        } else {
            EasyPermissions.requestPermissions(this, "", READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE);
        }
    }
}
