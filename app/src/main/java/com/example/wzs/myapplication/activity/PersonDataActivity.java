package com.example.wzs.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nonecity.R;
import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.base.BaseActivity;
import com.example.wzs.myapplication.model.UploadHead;
import com.example.wzs.myapplication.network.IUpLoad;
import com.example.wzs.myapplication.utils.ActionSheet;
import com.example.wzs.myapplication.utils.ActivityLauncherUtil;
import com.example.wzs.myapplication.utils.DensityUtils;
import com.example.wzs.myapplication.utils.GlideImageLoaderUtil;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.ToastUtil;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.zhy.android.percent.support.PercentLinearLayout;
import com.zhy.android.percent.support.PercentRelativeLayout;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Response;

public class PersonDataActivity extends BaseActivity implements ActionSheet.MenuItemClickListener, EasyPermissions.PermissionCallbacks {
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private static final int STORAGE_PERMISSION = 10003;
    public static final int CAMERA_PERMISSION = 10002;
    private static final int READ_PHONE_STATE = 10000;
    @Bind(R.id.rl_person_data)
    PercentLinearLayout rlPersonData;

    private PhotoInfo photoInfo;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.change_head)
    CircleImageView changeHead;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    @Bind(R.id.change_name)
    TextView changeName;
    @Bind(R.id.person_data_name)
    PercentRelativeLayout personDataName;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.change_id)
    TextView changeId;
    @Bind(R.id.person_data_id)
    PercentRelativeLayout personDataId;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.imageView4)
    ImageView imageView4;
    @Bind(R.id.change_sex)
    TextView changeSex;
    @Bind(R.id.person_data_sex)
    PercentRelativeLayout personDataSex;
    @Bind(R.id.imageView5)
    ImageView imageView5;
    @Bind(R.id.change_address)
    TextView changeAddress;
    @Bind(R.id.person_data_address)
    PercentRelativeLayout personDataAddress;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.person_data_setting)
    PercentRelativeLayout personDataSetting;
    @Bind(R.id.title_back_img)
    ImageView titleBackImg;
    @Bind(R.id.change_title)
    TextView changeTitle;
    @Bind(R.id.base_action_bar)
    RelativeLayout baseActionBar;


    private File file;
    private Uri uri;
    private PopupWindow popupWindow;
    String token = "ZKNgHfqI/p5HCfwcYfhmzNicgurE64geDLjbgja2XcrGQqNkHfLnoC1hrUSX5mrRNL1WCNT5N2k=";


    @Override
    protected int setLayoutId() {
        return R.layout.activity_person_data;
    }

    @Override
    protected void initView() {
        changeTitle.setText("个人资料");
    }

    @Override
    protected void initData() {
    requestReadPhoneStatePermission();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.change_head, R.id.person_data_name, R.id.person_data_id, R.id.person_data_sex, R.id.person_data_address, R.id.person_data_setting, R.id.title_back_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_head:
                checkPermissions();
                break;
            case R.id.person_data_name:
                setChangeName();
                break;
            case R.id.person_data_id:
                break;
            case R.id.person_data_sex:
                setTheme(R.style.ActionSheetStyleIOS7);
                setChangeSex();
                break;
            case R.id.person_data_address:
                setChangeAddress();
                break;
            case R.id.person_data_setting:
                ActivityLauncherUtil.launcher(this, SettingActivity.class);
                break;
            case R.id.title_back_img:
                finish();
                break;

        }
    }

    private void checkPermissions() {
        requestExternalStorage();
        requestCameraStorage();
    }

    @AfterPermissionGranted(STORAGE_PERMISSION)
    public void requestExternalStorage() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

        } else {
            EasyPermissions.requestPermissions(this, "", STORAGE_PERMISSION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @AfterPermissionGranted(CAMERA_PERMISSION)
    public void requestCameraStorage() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            setTheme(R.style.ActionSheetStyleIOS7);
            getPhoto();
        } else {
            EasyPermissions.requestPermissions(this, "", CAMERA_PERMISSION, Manifest.permission.CAMERA, Manifest.permission.CAMERA);
        }
    }
    @AfterPermissionGranted(READ_PHONE_STATE)

    public void requestReadPhoneStatePermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_PHONE_STATE)) {

        } else {
            EasyPermissions.requestPermissions(this, "", READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE);
        }
    }

    private void setChangeAddress() {
        CityPicker cityPicker = new CityPicker.Builder(this)
                .textSize(20)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#ffffff")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("北京市")
                .city("北京市")
                .district("昌平区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                if (province.equals(city)) {
                    changeAddress.setText(city + district+code);
                } else {
                    changeAddress.setText(province + city + district+code);
                }
            }

            @Override
            public void onCancel() {
                ToastUtil.showLong(PersonDataActivity.this, "已取消");
            }
        });
    }

    public void setChangeSex() {
        ActionSheet menuView = new ActionSheet(this);
        menuView.setCancelButtonTitle("取消");// before add items
        menuView.addItems("男", "女");
        menuView.setItemClickListener(this);
        menuView.setCancelableOnTouchMenuOutside(true);
        menuView.showMenu();

    }

    @Override
    public void onItemClick(int itemPosition) {
        switch (itemPosition) {
            case 0:
                changeSex.setText("男");
                break;
            case 1:
                changeSex.setText("女");
                break;
        }
    }

    public void setChangeName() {
        popupWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.popup_change_name, null);
        popupWindow.setContentView(view);
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
        popupWindow.setOutsideTouchable(true);// 设置外部区域可触摸
        popupWindow.setTouchable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setHeight(DensityUtils.dip2px(this, 150));
        popupWindow.setWidth(DensityUtils.dip2px(this, 200));
        EditText editText = view.findViewById(R.id.et_change_name);
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeName.setText(editText.getText().toString().trim());
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(rlPersonData, Gravity.CENTER, 0, 0);
    }


    private void getPhoto() {
        ActionSheet menuView = new ActionSheet(this);
        menuView.setCancelButtonTitle("取消");// before add items
        menuView.addItems("相册", "拍照");
        menuView.setItemClickListener(new ActionSheet.MenuItemClickListener() {
            @Override
            public void onItemClick(int itemPosition) {
                switch (itemPosition) {
                    case 0:
                        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, mOnHanlderResultCallback);

                        break;
                    case 1:
                        //  GalleryFinal.openCamera(REQUEST_CODE_CAMERA, mOnHanlderResultCallback);
                        useCamera();
                        GlideImageLoaderUtil.displayImageInActivity(PersonDataActivity.this, uri + "", changeHead);


                        break;
                    default:
                        break;
                }
            }
        });
        menuView.setCancelableOnTouchMenuOutside(true);
        menuView.showMenu();
    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                photoInfo = resultList.get(0);
                GlideImageLoaderUtil.displayImageInActivity(PersonDataActivity.this, "file://" + photoInfo.getPhotoPath(), changeHead);

                HXApplication.retrofitUtils.upload(photoInfo.getPhotoPath(), token, "1111111", new IUpLoad<UploadHead>() {
                    @Override
                    public void onSuccess(Response<ResponseBody> responseBody) {
                        if (responseBody.isSuccessful()) {
                            LogUtil.e("上传文件--------", "成功");
                        } else {
                            LogUtil.e("上传文件--------", "message" + responseBody.message());
                            LogUtil.e("上传文件--------", "body" + responseBody.body());
                            LogUtil.e("上传文件--------", "toString" + responseBody.toString());
                            LogUtil.e("上传文件--------", "headers" + responseBody.headers());
                            LogUtil.e("上传文件--------", "raw" + responseBody.raw());
                            LogUtil.e("上传文件--------", "errorBody" + responseBody.errorBody());
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        LogUtil.e("上传文件--------", "失败2");
                    }
                });
            }


        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            ToastUtil.showShort(PersonDataActivity.this, errorMsg);
        }
    };


    @Override
    public void onPermissionsGranted(List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(List<String> perms) {

    }

    private void useCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/Camera/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();

        //改变Uri  注意和xml中的一致
        uri = FileProvider.getUriForFile(this, "com.example.wzs.myapplication.fileProvider", file);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            options.config = new BitmapFactory.Options().inPreferredConfig;

            Tiny.getInstance().source(file).asFile().withOptions(options).compress(new FileCallback() {
                @Override
                public void callback(boolean isSuccess, String outfile) {
                    if (!isSuccess) {
                        return;
                    } else {
                       //在手机相册中显示刚拍摄的图片
                        LogUtil.e("压缩地址----",outfile);
                        File file = new File(outfile);
                        LogUtil.e("file----",file.getPath());
                        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        Uri contentUri = Uri.fromFile(file);
                        mediaScanIntent.setData(contentUri);
                        sendBroadcast(mediaScanIntent);
                        HXApplication.retrofitUtils.upload(outfile, token, "1111111", new IUpLoad<UploadHead>() {
                            @Override
                            public void onSuccess(Response<ResponseBody> responseBody) {
                                if (responseBody.isSuccessful()) {
                                    LogUtil.e("上传文件--------", "成功");
                                } else {
                                    LogUtil.e("上传文件--------", "message" + responseBody.message());
                                    LogUtil.e("上传文件--------", "body" + responseBody.body());
                                    LogUtil.e("上传文件--------", "toString" + responseBody.toString());
                                    LogUtil.e("上传文件--------", "headers" + responseBody.headers());
                                    LogUtil.e("上传文件--------", "raw" + responseBody.raw());
                                    LogUtil.e("上传文件--------", "errorBody" + responseBody.errorBody());
                                }
                            }

                            @Override
                            public void onError(String msg) {
                                LogUtil.e("上传文件--------", "失败2");
                            }
                        });
                    }

                }

            });
        }
    }


}
