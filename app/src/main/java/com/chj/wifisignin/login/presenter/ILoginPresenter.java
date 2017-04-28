package com.chj.wifisignin.login.presenter;

import android.os.Handler;

import com.chj.wifisignin.beans.User;

/**
 * author: WEI
 * date: 2017/4/26
 */

public interface ILoginPresenter
{
    void verifyUser(User user, Handler handler);
}
