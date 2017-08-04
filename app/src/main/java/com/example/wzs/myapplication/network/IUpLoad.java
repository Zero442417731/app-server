package com.example.wzs.myapplication.network;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by hxcs-02 on 2017/8/3.
 */

public interface IUpLoad<T> {
    void onSuccess(Response<ResponseBody> responseBody);

    void onError(String msg);
}
