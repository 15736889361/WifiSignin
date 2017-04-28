package com.chj.wifisignin.login.presenter;

import android.content.Context;
import android.os.Handler;

import com.chj.wifisignin.beans.User;
import com.chj.wifisignin.login.model.ILoginModel;
import com.chj.wifisignin.login.model.LoginModelImpl;
import com.chj.wifisignin.login.view.ILoginView;

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
    public void verifyUser(User user, Handler handler)
    {
        mILoginView.showProgress("正在登录，请稍候......");
        mILoginModel.verifyUser(mContext, user, handler);
    }
}
