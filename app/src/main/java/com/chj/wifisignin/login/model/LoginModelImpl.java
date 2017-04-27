package com.chj.wifisignin.login.model;

import android.content.Context;
import android.net.wifi.WifiInfo;

import com.chj.wifisignin.beans.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.chj.wifisignin.util.NetUtil.getWifiInfo;


/**
 * author: WEI
 * date: 2017/4/26
 */

public class LoginModelImpl implements ILoginModel
{
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

    boolean isExist;
    @Override
    public boolean verifyUser(Context context, User user)
    {
        String routerMac = getRouterMac(context);
        user.setRounterMac(routerMac);

        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("num", user.getNum());
        query.addWhereEqualTo("password", user.getPassword());
        query.addWhereEqualTo("rounterMac", routerMac);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null)
                {
                    isExist = true;
                }
                else
                {
                    isExist = false;
                }
            }
        });
        return isExist;
    }
}
