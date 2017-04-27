package com.chj.wifisignin.login.presenter;

import android.app.Application;
import android.content.Context;

import com.chj.wifisignin.login.model.ILoginModel;
import com.chj.wifisignin.login.model.LoginModelImpl;
import com.chj.wifisignin.login.view.ILoginView;
import com.chj.wifisignin.login.view.LoginActivity;

/**
 * author: WEI
 * date: 2017/4/26
 */

public class LoginPresenterImpl implements ILoginPresenter
{
    private ILoginView mILoginView;
    private ILoginModel mILoginModel;
    private Context mContext;

    public LoginPresenterImpl(Context context, ILoginView iLoginView)
    {
        mContext = context;
        mILoginView = iLoginView;
        mILoginModel = new LoginModelImpl();
    }

    @Override
    public boolean verifyUser()
    {
        mILoginView.showProgress();
        return false;
    }
}
