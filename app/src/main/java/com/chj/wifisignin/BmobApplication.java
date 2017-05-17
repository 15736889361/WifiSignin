package com.chj.wifisignin;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import cn.bmob.v3.Bmob;

public class BmobApplication extends Application
{
    /**
     * SDK初始化也可以放到Application中
     */
    public static String APPID = "1f68562db46eb000a872442e89037e93";
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, APPID);
        Log.e(TAG, "--- onCreate ---");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.e(TAG, "--- attachBaseContext ---");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e(TAG, "--- onLowMemory ---");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e(TAG, "--- onTrimMemory ---");
    }
}