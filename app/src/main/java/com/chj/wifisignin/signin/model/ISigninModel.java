package com.chj.wifisignin.signin.model;

import android.content.Context;
import android.os.Handler;

import com.chj.wifisignin.beans.Sign;

import java.util.List;

/**
 * author: WEI
 * date: 2017/4/28
 */

public interface ISigninModel
{
    void signin(Context context, Sign sign, Handler handler);

    List<Sign> getSigns(Context context);
}
