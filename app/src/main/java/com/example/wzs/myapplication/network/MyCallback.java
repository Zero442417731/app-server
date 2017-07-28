package com.example.wzs.myapplication.network;


public interface MyCallback<T> {

    void onSuccess(T t);

    void onError(String msg);
}
