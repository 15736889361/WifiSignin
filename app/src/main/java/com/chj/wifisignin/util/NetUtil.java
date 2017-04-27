package com.chj.wifisignin.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * author: WEI
 * date: 2017/4/27
 */

public class NetUtil
{

    public static WifiInfo getWifiInfo(Context context)
    {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled())
        {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            return wifiInfo;
        }
        return null;
    }
}
