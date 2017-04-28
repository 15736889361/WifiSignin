package com.chj.wifisignin.register.presenter;

import android.content.Context;
import android.os.Handler;

import com.chj.wifisignin.beans.User;
import com.chj.wifisignin.register.model.IRegisterModel;
import com.chj.wifisignin.register.model.RegisterModelImpl;
import com.chj.wifisignin.register.view.IRegisterView;
import com.chj.wifisignin.register.view.RegisterActivity;

/**
 * author: WEI
 * date: 2017/4/28
 */

public class RegisterPresenterImpl implements IRegisterPresenter
{
    private IRegisterModel mRegisterModel;
    private IRegisterView mRegisterView;
    private Context mContext;

    public RegisterPresenterImpl(Context context)
    {
        mContext = context;
        mRegisterView = (RegisterActivity) context;
        mRegisterModel = new RegisterModelImpl();
    }

    @Override
    public boolean valid(String num, String password, String confirmPass) {
        return mRegisterModel.valid(mContext, num, password, confirmPass);
    }

    @Override
    public void saveUser(User user, Handler handler)
    {
        mRegisterView.showProgress("用户信息正在提交，请稍候");
        mRegisterModel.saveUser(mContext, user, handler);
    }
}
