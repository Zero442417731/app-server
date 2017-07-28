package com.example.wzs.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.wzs.myapplication.application.HXApplication;

/**
 * Created by hxcs-02 on 2017/7/25.
 */

public class SharedPreferencesUtil {
    private final static int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private final static int INT_PREFERENCES_DEFAULT_VALUE = 0;
    private final static long LONG_PREFERENCES_DEFAULT_VALUE = 0l;
    private final static float FLOAT_PREFERENCES_DEFAULT_VALUE = 0f;
    private final static boolean BOOLEAN_PREFERENCES_DEFAULT_VALUE = false;
    private final static String STRING_PREFERENCES_DEFAULT_VALUE = "";

    /**
     * @param preName
     * @param mode
     * @param key
     * @param value
     */
    public static boolean setIntPreferences(String preName, int mode, String key,
                                            int value) {
        SharedPreferences preferences = HXApplication.getInstance()
                .getSharedPreferences(preName, mode);
        boolean b = preferences.edit().putInt(key, value).commit();
        return b;
    }

    /**
     * @param preName
     * @param key
     * @param value
     */
    public static boolean setIntPreferences(String preName, String key, int value) {
        return setIntPreferences(preName, PREFERENCES_MODE, key, value);
    }

    /**
     * @param preName
     * @param mode
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getIntPreferences(String preName, int mode, String key,
                                        int defaultValue) {
        SharedPreferences preferences = HXApplication.getInstance()
                .getSharedPreferences(preName, mode);
        return preferences.getInt(key, defaultValue);
    }

    /**
     * @param preName
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getIntPreferences(String preName, String key,
                                        int defaultValue) {
        return getIntPreferences(preName, PREFERENCES_MODE, key, defaultValue);
    }

    /**
     * @param preName
     * @param key
     * @return
     */
    public static int getIntPreferences(String preName, String key) {
        return getIntPreferences(preName, key, INT_PREFERENCES_DEFAULT_VALUE);
    }

    /**
     * @param preName
     * @param mode
     * @param key
     * @param value
     */
    public static void setLongPreferences(String preName, int mode, String key,
                                          long value) {
        SharedPreferences preferences = HXApplication.getInstance()
                .getSharedPreferences(preName, mode);
        preferences.edit().putLong(key, value).commit();
    }

    /**
     * @param preName
     * @param key
     * @param value
     */
    public static void setLongPreferences(String preName, String key, long value) {
        setLongPreferences(preName, PREFERENCES_MODE, key, value);
    }

    /**
     * @param preName
     * @param mode
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getLongPreferences(String preName, int mode, String key,
                                          long defaultValue) {
        SharedPreferences preferences = HXApplication.getInstance()
                .getSharedPreferences(preName, mode);
        return preferences.getLong(key, defaultValue);
    }

    /**
     * @param preName
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getLongPreferences(String preName, String key,
                                          long defaultValue) {
        return getLongPreferences(preName, PREFERENCES_MODE, key, defaultValue);
    }

    /**
     * @param preName
     * @param key
     * @return
     */
    public static long getLongPreferences(String preName, String key) {
        return getLongPreferences(preName, key, LONG_PREFERENCES_DEFAULT_VALUE);
    }

    /**
     * @param preName
     * @param mode
     * @param key
     * @param value
     */
    public static void setBooleanPreferences(String preName, int mode,
                                             String key, boolean value) {
        SharedPreferences preferences = HXApplication.getInstance()
                .getSharedPreferences(preName, mode);
        preferences.edit().putBoolean(key, value).commit();
    }

    /**
     * @param preName
     * @param key
     * @param value
     */
    public static void setBooleanPreferences(String preName, String key,
                                             boolean value) {

        setBooleanPreferences(preName, PREFERENCES_MODE, key, value);
    }

    /**
     * @param preName
     * @param mode
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBooleanPreferences(String preName, int mode,
                                                String key, boolean defaultValue) {
        SharedPreferences preferences = HXApplication.getInstance()
                .getSharedPreferences(preName, mode);
        return preferences.getBoolean(key, defaultValue);
    }

    /**
     * @param preName
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBooleanPreferences(String preName, String key,
                                                boolean defaultValue) {
        return getBooleanPreferences(preName, PREFERENCES_MODE, key, defaultValue);
    }

    /**
     * @param preName
     * @param key
     * @return
     */
    public static boolean getBooleanPreferences(String preName, String key) {
        return getBooleanPreferences(preName, key, BOOLEAN_PREFERENCES_DEFAULT_VALUE);
    }

    /**
     * @param preName
     * @param mode
     * @param key
     * @param value
     */
    public static void setFloatPreferences(String preName, int mode,
                                           String key, float value) {
        SharedPreferences preferences = HXApplication.getInstance()
                .getSharedPreferences(preName, mode);
        preferences.edit().putFloat(key, value).commit();
    }

    /**
     * @param preName
     * @param key
     * @param value
     */
    public static void setFloatPreferences(String preName, String key,
                                           float value) {
        setFloatPreferences(preName, PREFERENCES_MODE, key, value);
    }

    /**
     * @param preName
     * @param mode
     * @param key
     * @param defaultValue
     * @return
     */
    public static float getFloatPreferences(String preName, int mode,
                                            String key, float defaultValue) {
        SharedPreferences preferences = HXApplication.getInstance()
                .getSharedPreferences(preName, mode);
        return preferences.getFloat(key, defaultValue);
    }

    /**
     * @param preName
     * @param key
     * @param defaultValue
     * @return
     */
    public static float getFloatPreferences(String preName, String key,
                                            float defaultValue) {
        return getFloatPreferences(preName, PREFERENCES_MODE, key, defaultValue);
    }

    /**
     * @param preName
     * @param key
     * @return
     */
    public static float getFloatPreferences(String preName, String key) {

        return getFloatPreferences(preName, key,
                FLOAT_PREFERENCES_DEFAULT_VALUE);
    }

    /**
     * @param preName
     * @param mode
     * @param key
     * @param value
     */
    public static void setStringPreferences(String preName, int mode,
                                            String key, String value) {
        SharedPreferences preferences = HXApplication.getInstance()
                .getSharedPreferences(preName, mode);
        preferences.edit().putString(key, value).commit();
    }

    /**
     * @param preName
     * @param key
     * @param value
     */
    public static void setStringPreferences(String preName, String key,
                                            String value) {
        setStringPreferences(preName, PREFERENCES_MODE, key, value);
    }

    /**
     * @param preName
     * @param mode
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getStringPreferences(String preName, int mode,
                                              String key, String defaultValue) {
        SharedPreferences preferences = HXApplication.getInstance()
                .getSharedPreferences(preName, mode);
        return preferences.getString(key, defaultValue);
    }

    /**
     * @param preName
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getStringPreferences(String preName, String key,
                                              String defaultValue) {
        return getStringPreferences(preName, PREFERENCES_MODE, key,
                defaultValue);
    }

    /**
     * @param preName
     * @param key
     * @return
     */
    public static String getStringPreferences(String preName, String key) {
        return getStringPreferences(preName, key, STRING_PREFERENCES_DEFAULT_VALUE);
    }

    /**
     * @param preName
     * @param mode
     */
    public static void clearPreferences(String preName, int mode) {
        SharedPreferences preferences = HXApplication.getInstance()
                .getSharedPreferences(preName, mode);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * @param preName
     */
    public static void clearPreferences(String preName) {
        clearPreferences(preName, PREFERENCES_MODE);
    }
    private static final String spFileName = "app";


    public static String getString(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        String result = setPreferences.getString(strKey, "");
        return result;
    }

    public static String getString(Context context, String strKey,
                                   String strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        String result = setPreferences.getString(strKey, strDefault);
        return result;
    }

    public static void putString(Context context, String strKey, String strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putString(strKey, strData);
        editor.commit();
    }

    public static Boolean getBoolean(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        Boolean result = setPreferences.getBoolean(strKey, false);
        return result;
    }

    public static Boolean getBoolean(Context context, String strKey,
                                     Boolean strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        Boolean result = setPreferences.getBoolean(strKey, strDefault);
        return result;
    }


    public static void putBoolean(Context context, String strKey,
                                  Boolean strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putBoolean(strKey, strData);
        editor.commit();
    }

    public static int getInt(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        int result = setPreferences.getInt(strKey, -1);
        return result;
    }

    public static int getInt(Context context, String strKey, int strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        int result = setPreferences.getInt(strKey, strDefault);
        return result;
    }

    public static void putInt(Context context, String strKey, int strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putInt(strKey, strData);
        editor.commit();
    }

    public static long getLong(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        long result = setPreferences.getLong(strKey, -1);
        return result;
    }

    public static long getLong(Context context, String strKey, long strDefault) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        long result = setPreferences.getLong(strKey, strDefault);
        return result;
    }

    public static void putLong(Context context, String strKey, long strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putLong(strKey, strData);
        editor.commit();
    }
}
