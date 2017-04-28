package com.chj.wifisignin.signin.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.chj.wifisignin.R;
import com.chj.wifisignin.base.BaseActivity;
import com.chj.wifisignin.base.Global;
import com.chj.wifisignin.beans.Sign;
import com.chj.wifisignin.beans.User;
import com.chj.wifisignin.signin.presenter.ISigninPresenter;
import com.chj.wifisignin.signin.presenter.SigninPresenterImpl;
import com.chj.wifisignin.util.TimeUtil;

public class MainActivity extends BaseActivity implements IMainView
{
    private Button signinBtn, signinHistoryBtn;
    private boolean isSignin = true;
    private ISigninPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new SigninPresenterImpl(this);
    }

    @Override
    protected void initView() {
        signinBtn = (Button) findViewById(R.id.btn_signin);
        signinHistoryBtn = (Button) findViewById(R.id.btn_signin_history);
    }

    @Override
    protected void initListener() {
        signinBtn.setOnClickListener(this);
        signinHistoryBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_signin:
                // 签到/签退
                if (isSignin)
                {
                    isSignin = !isSignin;
                    signinBtn.setText("签退");
                    // 签到
                    Sign sign = new Sign();
                    User user = Global.sUser;
                    sign.setNum(user.getNum());
                    sign.setRouterMac(user.getRouterMac());
                    sign.setSignin_time(TimeUtil.getTime(System.currentTimeMillis()));
                    mPresenter.signin(sign, mHandler);
                }
                else
                {
                    // 签退，成功后
                    Sign sign = new Sign();
                    User user = Global.sUser;
                    sign.setNum(user.getNum());
                    sign.setRouterMac(user.getRouterMac());
                    sign.setSignout_time(TimeUtil.getTime(System.currentTimeMillis()));
                    mPresenter.signin(sign, mHandler);
                }
                break;

            case R.id.btn_signin_history:
                // 查看签到历史
                mPresenter.getSigns();
                break;
        }
    }

    public final static int SIGNIN_SUCCEED = 1;
    public final static int SIGNIN_FAILURE = 0;

    public final static int SIGNOUT_SUCCEED = 2;
    public final static int SIGNOUT_FAILURE = 3;

    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            hideProgress();
            super.handleMessage(msg);
            switch (msg.what)
            {
                case SIGNIN_FAILURE:
                    break;

                case SIGNIN_SUCCEED:
                    break;

                case SIGNOUT_SUCCEED:
                    signinBtn.setVisibility(View.GONE);
                    break;

                case SIGNOUT_FAILURE:
                    break;
            }
        }
    };
}
