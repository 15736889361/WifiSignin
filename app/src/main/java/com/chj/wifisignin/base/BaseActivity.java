package com.chj.wifisignin.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * author: WEI
 * date: 2017/4/26
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener
{
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = this;
        initView();
        initListener();
    }

    protected abstract void initView();

    protected abstract void initListener();

    public void showProgress(String msg) {
        showMsg(msg);
    }

    public void hideProgress() {

    }

    protected void showMsg(String msg)
    {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}
