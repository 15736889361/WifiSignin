package com.chj.wifisignin.register.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.chj.wifisignin.R;
import com.chj.wifisignin.base.BaseActivity;
import com.chj.wifisignin.beans.User;
import com.chj.wifisignin.register.presenter.IRegisterPresenter;
import com.chj.wifisignin.register.presenter.RegisterPresenterImpl;

/**
 * author: WEI
 * date: 2017/4/28
 */

public class RegisterActivity extends BaseActivity implements IRegisterView
{
    private EditText numTxt, passTxt, confirmPassTxt;
    private RadioButton teacherRdoBtn, studentRdoBtn;
    private Button registerBtn;
    private String num, pass, confirmPass;
    private IRegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegisterPresenter = new RegisterPresenterImpl(this);
    }

    @Override
    protected void initView() {
        numTxt = (EditText) findViewById(R.id.edtTxt_no);
        passTxt = (EditText) findViewById(R.id.edtTxt_pass);
        confirmPassTxt = (EditText) findViewById(R.id.edtTxt_pass_confirm);
        teacherRdoBtn = (RadioButton) findViewById(R.id.rdoBtn_teacher);
        studentRdoBtn = (RadioButton) findViewById(R.id.rdoBtn_student);
        registerBtn = (Button) findViewById(R.id.btn_register);
    }

    @Override
    protected void initListener() {
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_register:
                // 注册操作
                num = numTxt.getText() + "";
                pass = passTxt.getText() + "";
                confirmPass = confirmPassTxt.getText() + "";
                if (mRegisterPresenter.valid(num, pass, confirmPass))
                {
                    User user = new User();
                    user.setNum(num);
                    user.setPassword(pass);
                    user.setType(teacherRdoBtn.isChecked() ? 1 : 0);

                    mRegisterPresenter.saveUser(user, mHandler);
                }
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
                case 1:
                    showMsg("注册成功！");
                    finish();
                    break;

                case 2:
                    finish();
                    break;
            }
        }
    };

}
