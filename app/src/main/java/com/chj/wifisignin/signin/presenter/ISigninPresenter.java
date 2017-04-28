package com.chj.wifisignin.signin.presenter;

import android.content.Context;
import android.os.Handler;

import com.chj.wifisignin.beans.Sign;
import com.chj.wifisignin.signin.model.SigninModelImpl;

import java.util.List;

/**
 * author: WEI
 * date: 2017/4/28
 */

public interface ISigninPresenter
{
    void signin(Sign sign, Handler handler);

    void getSigns();
}
