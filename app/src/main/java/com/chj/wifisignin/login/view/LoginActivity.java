package com.chj.wifisignin.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.chj.wifisignin.signin.view.MainActivity;
import com.chj.wifisignin.R;
import com.chj.wifisignin.base.BaseActivity;
import com.chj.wifisignin.beans.User;
import com.chj.wifisignin.login.presenter.ILoginPresenter;
import com.chj.wifisignin.login.presenter.LoginPresenterImpl;
import com.chj.wifisignin.register.view.RegisterActivity;

/**
 * author: WEI
 * date: 2017/4/26
 */

public class LoginActivity extends BaseActivity implements ILoginView {
    private ILoginPresenter mILoginPresenter;
    private EditText numTxt, passTxt;
    private RadioButton teacherRdoBtn, studentRdoBtn;
    private Button loginBtn, registerBtn;
    private String num, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mILoginPresenter = new LoginPresenterImpl(getApplication(), this);
    }

    @Override
    protected void initView() {
        numTxt = (EditText) findViewById(R.id.edtTxt_no);
        passTxt = (EditText) findViewById(R.id.edtTxt_pass);
        teacherRdoBtn = (RadioButton) findViewById(R.id.rdoBtn_teacher);
        studentRdoBtn = (RadioButton) findViewById(R.id.rdoBtn_student);
        loginBtn = (Button) findViewById(R.id.btn_login);
        registerBtn = (Button) findViewById(R.id.btn_register);
    }

    @Override
    protected void initListener() {
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_login:
                // 登录操作
                if (valid())
                {
                    User user = new User();
                    user.setNum(num);
                    user.setPassword(pass);
                    user.setType(studentRdoBtn.isChecked() ? 0 : 1);

                    mILoginPresenter.verifyUser(user, mHandler);
                }
                break;

            case R.id.btn_register:
                // 注册操作
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
        }
    }

    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            hideProgress();
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 0:
                    // 登录失败
                    break;

                case 1:
                    // 登录成功
                    startActivity(new Intent(mContext, MainActivity.class));
                    break;
            }
        }
    };

    private boolean valid()
    {
        num = numTxt.getText() + "";
        pass = passTxt.getText() + "";
        if (TextUtils.isEmpty(num))
        {
            showMsg("编号不能为空！");
            return false;
        }
        else if (TextUtils.isEmpty(pass))
        {
            showMsg("密码不能为空！");
            return false;
        }
        return true;
    }
}
