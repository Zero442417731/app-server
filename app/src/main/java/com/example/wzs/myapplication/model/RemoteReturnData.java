package com.example.wzs.myapplication.model;

import java.io.Serializable;

/**
 * Created by hxcs-02 on 2017/7/25.
 */


public class RemoteReturnData<DataType> implements Serializable {

    private int status;
    private String message;
    private DataType data;

    public RemoteReturnData(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public RemoteReturnData(int status, String message, DataType data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataType getData() {
        return data;
    }

    public void setData(DataType data) {
        this.data = data;
    }

}
