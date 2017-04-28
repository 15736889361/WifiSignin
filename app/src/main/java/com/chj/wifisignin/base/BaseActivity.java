package com.chj.wifisignin.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chj.wifisignin.R;

/**
 * author: WEI
 * date: 2017/4/26
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener
{
    protected String TAG = "";
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = this;
        TAG = getClass().getSimpleName();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initListener();
    }

    protected abstract void initView();

    protected abstract void initListener();

    protected ProgressDialog progressDialog;
    public void showProgress(String msg)
    {
        hideProgress();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public void hideProgress() {
        if (null != progressDialog)
        {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    protected void showMsg(String msg)
    {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}
