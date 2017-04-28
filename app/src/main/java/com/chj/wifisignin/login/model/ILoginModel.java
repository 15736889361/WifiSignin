package com.chj.wifisignin.login.model;

import android.content.Context;
import android.os.Handler;

import com.chj.wifisignin.beans.User;

/**
 * author: WEI
 * date: 2017/4/26
 */

public interface ILoginModel
{
    String getRouterMac(Context context);

    String getPhoneMac(Context context);

    void verifyUser(Context context, User user, Handler handler);
}
