package com.chj.wifisignin.signin.presenter;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.chj.wifisignin.beans.Sign;
import com.chj.wifisignin.signin.model.ISigninModel;
import com.chj.wifisignin.signin.model.SigninModelImpl;
import com.chj.wifisignin.signin.view.IMainView;
import com.chj.wifisignin.signin.view.MainActivity;

import java.util.List;

/**
 * author: WEI
 * date: 2017/4/28
 */

public class SigninPresenterImpl implements ISigninPresenter
{
    private ISigninModel mSigninModel;
    private IMainView mMainView;
    private Context mContext;

    public SigninPresenterImpl(Context context)
    {
        mContext = context;
        mSigninModel = new SigninModelImpl();
        mMainView = (MainActivity) context;
    }

    @Override
    public void signin(Sign sign, Handler handler) {
        if (TextUtils.isEmpty(sign.getSignout_time()))
        {
            mMainView.showProgress("正在签到，请稍候......");
        }
        else
        {
            mMainView.showProgress("正在签退，请稍候......");
        }
        mSigninModel.signin(mContext, sign, handler);
    }

    @Override
    public List<Sign> getSigns()
    {
        return mSigninModel.getSigns(mContext);
    }
}
