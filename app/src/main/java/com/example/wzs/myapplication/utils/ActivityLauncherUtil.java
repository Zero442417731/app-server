package com.example.wzs.myapplication.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by hxcs-02 on 2017/7/25.
 */

public class ActivityLauncherUtil {

    /**
     * @param context
     * @param next
     */
    public static void launcher(Context context, Class<?> next, int flag) {

        launcher(context, next, null, null, flag);
    }

    /**
     * @param context
     * @param next
     */
    public static void launcher(Context context, Class<?> next) {

        launcher(context, next, Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    /**
     * @param context
     * @param next
     * @param bundle
     * @param bundleName
     */
    public static void launcher(Context context, Class<?> next, Bundle bundle, String bundleName) {

        launcher(context, next, bundle, bundleName,
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    /**
     * @param context
     * @param next
     * @param bundle
     * @param bundleName
     * @param flag
     */
    public static void launcher(Context context, Class<?> next, Bundle bundle,
                                String bundleName, int flag) {

        Intent intent = new Intent(context, next);
        if (bundle != null) {
            intent.putExtra(bundleName, bundle);
        }
        intent.setFlags(flag);
        context.startActivity(intent);
    }
}
