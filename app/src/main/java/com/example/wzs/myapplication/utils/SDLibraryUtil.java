package com.example.wzs.myapplication.utils;

import android.app.Application;

import com.example.wzs.myapplication.application.HXApplication;

public class SDLibraryUtil {

    private static SDLibraryUtil instance;
    private Application application;

    public SDLibraryUtil() {
        application = HXApplication.getInstance();
    }

    public static SDLibraryUtil getInstance() {
        if (instance == null) {
            instance = new SDLibraryUtil();
        }
        return instance;
    }

    public Application getApplication() {
        return application;
    }


}
