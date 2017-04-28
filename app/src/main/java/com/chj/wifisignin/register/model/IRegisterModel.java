package com.chj.wifisignin.register.model;

import android.content.Context;
import android.os.Handler;

import com.chj.wifisignin.beans.User;

/**
 * author: WEI
 * date: 2017/4/28
 */

public interface IRegisterModel
{
    boolean valid(Context context, String num, String password, String confirmPass);

    void saveUser(Context context, User user, Handler handler);
}
