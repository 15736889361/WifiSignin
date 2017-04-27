package com.chj.wifisignin.login.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.chj.wifisignin.base.BaseActivity;
import com.chj.wifisignin.login.presenter.ILoginPresenter;
import com.chj.wifisignin.login.presenter.LoginPresenterImpl;

/**
 * author: WEI
 * date: 2017/4/26
 */

public class LoginActivity extends BaseActivity implements ILoginView
{
    private ILoginPresenter mILoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mILoginPresenter = new LoginPresenterImpl(getApplication(), this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
