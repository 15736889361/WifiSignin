package com.chj.wifisignin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: WEI
 * date: 2017/4/28
 */

public class TimeUtil
{
    public static String getTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }
}
