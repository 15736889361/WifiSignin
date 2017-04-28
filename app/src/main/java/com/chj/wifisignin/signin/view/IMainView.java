package com.chj.wifisignin.signin.view;

import com.chj.wifisignin.beans.Sign;

import java.util.List;

/**
 * author: WEI
 * date: 2017/4/28
 */

public interface IMainView
{
    void showProgress(String msg);

    void hideProgress();

    void showList(List<Sign> signs);
}
