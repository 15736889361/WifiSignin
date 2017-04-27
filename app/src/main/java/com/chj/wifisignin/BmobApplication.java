package com.chj.wifisignin;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class BmobApplication extends Application {
    /**
     * SDK初始化也可以放到Application中
     */
    public static String APPID = "1f68562db46eb000a872442e89037e93";

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, APPID);
    }


}