package com.example.wzs.myapplication.network;

import android.util.Log;

import com.example.wzs.myapplication.config.Constant;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtils {

    private static RetrofitUtils retrofitUtils;
    private final MyNetRequest myNetRequest;

    private RetrofitUtils() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.URL_SERVICE_CENTER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myNetRequest = retrofit.create(MyNetRequest.class);
    }
    public static RetrofitUtils getInstance(){

        if (retrofitUtils==null){
            retrofitUtils=new RetrofitUtils();
        }
        return  retrofitUtils;
    }
    public <T> void getData(String url, Map<String,String> params, final MyCallback<T> callback){
        if (params==null || params.size()==0){
            Call<ResponseBody> call = myNetRequest.getResponseBody(url);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String json=response.body().string();
                        Type generice = getGenerice(callback);
                        Gson gson=new Gson();
                        T  t= gson.fromJson(json, generice);
                        callback.onSuccess(t);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.onError(t.getMessage());
                }
            });
        }else{
            Call<ResponseBody> call = myNetRequest.getResponseBody(url,params);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String json=response.body().string();

                        Type generice = getGenerice(callback);

                        Gson gson=new Gson();

                        T  t= gson.fromJson(json, generice);

                        callback.onSuccess(t);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.onError(t.getMessage());
                }
            });
        }
    }
    public <T> void getDatawithHeader(String url, Map<String,String> params, final MyCallback<T> callback,Map<String,String> headers){

            Call<ResponseBody> call = myNetRequest.getResponseBody(url,params,headers);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    try {
                        String json=response.body().string();

                        Type generice = getGenerice(callback);

                        Gson gson=new Gson();

                        T  t= gson.fromJson(json, generice);

                        callback.onSuccess(t);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.onError(t.getMessage());
                }
            });

    }
    public <T> void postData(String url, Map<String,String> params, final MyCallback<T> callback){
            Call<ResponseBody> call = myNetRequest.postResponseBody(url,params);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String json=response.body().string();
                        Type generice = getGenerice(callback);
                        Gson gson=new Gson();
                        T  t= gson.fromJson(json, generice);
                        callback.onSuccess(t);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.onError(t.getMessage());
                }
            });
    }
    public <T> void postData(String url, Map<String,String> params, final MyCallback<T> callback,Map<String,String> headers){

        Call<ResponseBody> call = myNetRequest.postResponseBody(url,params,headers);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json=response.body().string();
                    Type generice = getGenerice(callback);
                    Gson gson=new Gson();
                    T  t= gson.fromJson(json, generice);
                    callback.onSuccess(t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public <T> void postData(String jsonObject,final MyCallback<T> callback){

        RequestBody responseBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonObject);
        Call<ResponseBody> responseBodyCall = myNetRequest.postResponseBody(responseBody);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String json= null;
                try {
                    json = response.body().string();
                    Type generice = getGenerice(callback);
                    Gson gson=new Gson();
                      T  t= gson.fromJson(json, generice);

                callback.onSuccess(t);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }



    private <T>Type getGenerice(MyCallback<T> callback){

        Type[] genericInterfaces = callback.getClass().getGenericInterfaces();

        Type[] types = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();

        return types[0];
    }

    private void upload(){

    }
}
