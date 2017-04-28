package com.chj.wifisignin.login.model;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.os.Handler;
import android.util.Log;

import com.chj.wifisignin.beans.User;
import com.chj.wifisignin.util.LogUtil;
import com.chj.wifisignin.util.ToastUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.chj.wifisignin.util.NetUtil.getWifiInfo;


/**
 * author: WEI
 * date: 2017/4/26
 */

public class LoginModelImpl implements ILoginModel
{
    private final String TAG = getClass().getSimpleName();

    @Override
    public String getRouterMac(Context context)
    {
        WifiInfo wifiInfo = getWifiInfo(context);
        String routerMac = "";
        if (null != wifiInfo)
        {
            routerMac = wifiInfo.getBSSID();
        }
        return routerMac;
    }

    @Override
    public String getPhoneMac(Context context)
    {
        WifiInfo wifiInfo = getWifiInfo(context);
        String phoneMac = "";
        if (null != wifiInfo)
        {
            phoneMac = wifiInfo.getMacAddress();
        }
        return phoneMac;
    }

    @Override
    public void verifyUser(final Context context, final User user, final Handler handler)
    {
        String routerMac = getRouterMac(context);
        user.setRouterMac(routerMac);

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
                        // 路由mac及编号都存在，继续判断账号和密码是否正确
                        User user1 = users.get(0);
                        if (user1.getPassword().equals(user.getPassword()))
                        { // 账号及密码都正确，则继续判断身份是否选择正确
                            if (user1.getType() == user.getType())
                            {
                                handler.obtainMessage(1).sendToTarget();
                            }
                            else
                            {
                                ToastUtil.showMsg(context, "身份类型不匹配，请确认是否选中正确的身份！", true);
                                handler.obtainMessage(0).sendToTarget();
                            }
                        }
                        else
                        {
                            ToastUtil.showMsg(context, "密码错误，请确认后重输！", true);
                            handler.obtainMessage(0).sendToTarget();
                        }
                    }
                    else
                    {
                        ToastUtil.showMsg(context, "账号不存在，请确认编号是否正确及是否在连接该wifi的情况下注册过！", true);
                        handler.obtainMessage(0).sendToTarget();
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
