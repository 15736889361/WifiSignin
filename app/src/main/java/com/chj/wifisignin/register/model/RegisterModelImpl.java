package com.chj.wifisignin.register.model;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.os.Handler;
import android.text.TextUtils;

import com.chj.wifisignin.beans.User;
import com.chj.wifisignin.util.LogUtil;
import com.chj.wifisignin.util.ToastUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static com.chj.wifisignin.util.NetUtil.getWifiInfo;

/**
 * author: WEI
 * date: 2017/4/28
 */

public class RegisterModelImpl implements IRegisterModel
{
    private final String TAG = getClass().getSimpleName();

    @Override
    public boolean valid(Context context, String num, String password, String confirmPass)
    {
        if (TextUtils.isEmpty(num))
        {
            ToastUtil.showMsg(context, "请输入编号！", false);
            return false;
        }

        if (TextUtils.isEmpty(password))
        {
            ToastUtil.showMsg(context, "请输入密码！", false);
            return false;
        }
        else
        {
            if (password.length() < 6)
            {
                ToastUtil.showMsg(context, "密码长度不能少于6位字符！", false);
                return false;
            }
        }

        if (TextUtils.isEmpty(confirmPass))
        {
            ToastUtil.showMsg(context, "请输入确认密码！", false);
            return false;
        }
        else
        {
            if (!password.equals(confirmPass))
            {
                ToastUtil.showMsg(context, "两次输入的密码不一致，请重新输入！", false);
                return false;
            }
        }
        return true;
    }

    @Override
    public void saveUser(final Context context, User user, final Handler handler)
    {
        WifiInfo wifiInfo = getWifiInfo(context);
        String routerMac = "";
        if (null != wifiInfo)
        {
            routerMac = wifiInfo.getBSSID();
        }
        user.setRouterMac(routerMac);
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null)
                {
                    handler.obtainMessage(1).sendToTarget();
                }else{
                    LogUtil.e(TAG, e.getMessage());
                    handler.obtainMessage(0).sendToTarget();
                }
            }
        });
    }
}
