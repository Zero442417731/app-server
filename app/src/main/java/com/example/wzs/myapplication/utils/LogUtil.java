package com.example.wzs.myapplication.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by hxcs-02 on 2017/7/25.
 */

public class LogUtil {

    // 所有自定义log输出开关
    private static boolean islog = true;
    private static String defaultTag = "LogUtil";
    public static boolean IS_DEBUG = false;

    /**
     * debug级别信息log
     *
     * @param context
     * @param log_content
     */
    public static void w(String tag, String log_content) {
        if (islog) {
            if (null != tag) {
                Log.w(tag, log_content);
            } else {
                Log.w(defaultTag, log_content);
            }
        }
    }

    /**
     * 错误级别log
     *
     * @param context
     * @param log_content
     */
    public static void e(Context context, String log_content) {
        if (islog) {
            if (null != context) {
                Log.e(context.getClass().getSimpleName(), log_content);
            } else {
                Log.e(defaultTag, log_content);
            }
        }
    }

    public static void e(String tag, String log_content) {
        if (islog) {
            if (null != tag) {
                Log.e(tag, log_content);
            } else {
                Log.e(defaultTag, log_content);
            }
        }
    }

    /**
     * 全局级别log
     *
     * @param context
     * @param log_content
     */
    public static void v(Context context, String log_content) {
        if (islog) {
            if (null != context) {
                Log.v(context.getClass().getSimpleName(), log_content);
            } else {
                Log.v(defaultTag, log_content);
            }
        }
    }

    public static void v(String tag, String log_content) {
        if (islog) {
            if (null != tag) {
                Log.v(tag, log_content);
            } else {
                Log.v(defaultTag, log_content);
            }
        }
    }

    /**
     * 自定义输出
     *
     * @param tag
     * @param log_content
     */
    public static void f(String tag, String log_content) {
        if (islog) {
            Log.e(tag, log_content);
        }
    }

    public static void netTimeLog(String method, long time) {
        StringBuffer sb = new StringBuffer();
        sb.append(method).append(":").append(time).append("**************");
        System.out.println(sb.toString());
    }
}
