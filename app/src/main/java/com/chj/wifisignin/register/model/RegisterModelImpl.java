package com.chj.wifisignin.register.model;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.os.Handler;
import android.text.TextUtils;

import com.chj.wifisignin.beans.User;
import com.chj.wifisignin.util.LogUtil;
import com.chj.wifisignin.util.ToastUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
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
    public void saveUser(final Context context, final User user, final Handler handler)
    {
        WifiInfo wifiInfo = getWifiInfo(context);
        String routerMac = "";
        if (null != wifiInfo)
        {
            routerMac = wifiInfo.getBSSID();
        }
        if (TextUtils.isEmpty(routerMac))
        {
            ToastUtil.showMsg(context, "设备未连接wifi，不能进行注册！", true);
            handler.obtainMessage(0).sendToTarget();
            return;
        }
        user.setRouterMac(routerMac);
        user.setWifi(wifiInfo.getSSID());

        String sql = "select * from User where routerMac = '" + routerMac + "' and num = '" + user.getNum() + "'";
        LogUtil.e(TAG, "sql = " + sql);
        BmobQuery<User> query = new BmobQuery<>();
        query.setSQL(sql);
        query.doSQLQuery(sql, new SQLQueryListener<User>() {
            @Override
            public void done(BmobQueryResult<User> bmobQueryResult, BmobException e) {
                if (e == null)
                {
                    List<User> users = bmobQueryResult.getResults();
                    if (null != users && users.size() > 0)
                    {
                       // 表示该账户已经在该路由器成功注册过，不能再注册
                        ToastUtil.showMsg(context, "该账户已经在该路由器成功注册过，可直接登录！", true);
                        handler.obtainMessage(2).sendToTarget();
                    }
                    else
                    {
                        // 该账户未注册，可以进行注册操作
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
                else
                {
                    ToastUtil.showMsg(context, "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage(), true);
                    handler.obtainMessage(0).sendToTarget();
                }
            }
        });
    }
}
