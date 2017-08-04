package com.example.wzs.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wzs.myapplication.application.HXApplication;



public class GlideImageLoaderUtil {
    public static void displayImageInActivity(Activity activity, String path, ImageView imageView) {
        Glide.with(activity)
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(imageView);
    }

    public static void displayImageInFragment(Fragment fragment, String path, ImageView imageView) {
        Glide.with(fragment)
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(imageView);
    }

    public static void displayImage(String path, ImageView imageView) {
        Glide.with(HXApplication.getInstance().getApplicationContext())
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(imageView);
    }

    public static void displayImage(Context context, String path, ImageView imageView, Drawable defaultDrawable, int width, int height) {
        Glide.with(context)
                .load(path)
                .placeholder(defaultDrawable)
                .error(defaultDrawable)
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(imageView);
    }
}