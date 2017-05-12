package com.chj.wifisignin.signin.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.chj.wifisignin.R;
import com.chj.wifisignin.base.BaseActivity;
import com.chj.wifisignin.base.Global;
import com.chj.wifisignin.beans.Sign;
import com.chj.wifisignin.beans.User;
import com.chj.wifisignin.signin.SignAdapter;
import com.chj.wifisignin.signin.presenter.ISigninPresenter;
import com.chj.wifisignin.signin.presenter.SigninPresenterImpl;
import com.chj.wifisignin.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IMainView
{
    private Button signinBtn, signinHistoryBtn;
    private ListView mListView;
    private boolean isSignin = true;
    private ISigninPresenter mPresenter;
    private Sign sign;
    private boolean isShouldClosed = false;

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
        mListView = (ListView) findViewById(R.id.lv_content);
        if (Global.sUser.getType() == Global.TEACHER)
        {
            signinBtn.setVisibility(View.GONE);
            signinHistoryBtn.setText("查看签到情况");
        }
        else
        {
            signinBtn.setVisibility(View.VISIBLE);
            signinHistoryBtn.setText("签到历史");
        }
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
                    sign = new Sign();
                    User user = Global.sUser;
                    sign.setNum(user.getNum());
                    sign.setRouterMac(user.getRouterMac());
                    Global.signin_time = TimeUtil.getTime(System.currentTimeMillis());
                    sign.setSignin_time(Global.signin_time);
                    mPresenter.signin(sign, mHandler);
                }
                else
                {
                    // 签退
//                    sign = new Sign();
//                    User user = Global.sUser;
//                    sign.setNum(user.getNum());
//                    sign.setRouterMac(user.getRouterMac());
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
                    List<Sign> signs =  new ArrayList<>();
                    signs.add(sign);
                    showList(signs);
                    break;

                case SIGNOUT_SUCCEED:
                    signinBtn.setVisibility(View.GONE);
                    List<Sign> signs1 =  new ArrayList<>();
                    signs1.add(sign);
                    showList(signs1);
                    isShouldClosed = true;
                    break;

                case SIGNOUT_FAILURE:
                    break;
            }
        }
    };

    @Override
    public void showList(List<Sign> signs)
    {
        final StringBuffer buffer = new StringBuffer();
        for (Sign sign:signs)
        {
            Log.e(TAG, sign.toString());
            buffer.append(sign.toString());
            buffer.append("\n");
        }
        SignAdapter adapter = new SignAdapter(mContext, signs);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (isShouldClosed)
        {
            finish();
        }
        else
        {
            showMsg("您还未签退，不能退出系统！");
        }
    }
}
