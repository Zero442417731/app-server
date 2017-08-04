package com.example.wzs.myapplication.network;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface MyNetRequest {
    @GET
    Call<ResponseBody> getResponseBody(@Url String url, @QueryMap Map<String, String> params);

    @GET
    Call<ResponseBody> getResponseBody(@Url String url);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postResponseBody(@Url String url, @FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postResponseBody(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @GET
    Call<ResponseBody> getResponseBody(@Url String url, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("api")
    Call<ResponseBody> postResponseBody(@Body RequestBody route);

    @Multipart
    @POST("userUpload")
    Call<ResponseBody> upload(@Query("token") String token,
                              @Query("fileName") String fileName,
                              @Part("description") RequestBody description,
                              @Part MultipartBody.Part file);
}
