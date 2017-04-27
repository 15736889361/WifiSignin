package com.chj.wifisignin.login.model;

import android.content.Context;

import com.chj.wifisignin.beans.User;

/**
 * author: WEI
 * date: 2017/4/26
 */

public interface ILoginModel
{
    String getRouterMac(Context context);

    String getPhoneMac(Context context);

    boolean verifyUser(Context context, User user);
}
