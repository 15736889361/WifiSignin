package com.chj.wifisignin.register.presenter;

import android.content.Context;
import android.os.Handler;

import com.chj.wifisignin.beans.User;

/**
 * author: WEI
 * date: 2017/4/28
 */

public interface IRegisterPresenter {
    boolean valid(String num, String password, String confirmPass);

    void saveUser(User user, Handler handler);
}
