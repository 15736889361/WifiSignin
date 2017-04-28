package com.chj.wifisignin.util;

import android.content.Context;
import android.widget.Toast;

/**
 * author: WEI
 * date: 2017/4/28
 */

public class ToastUtil
{
    public static void showMsg(Context context, String msg, boolean isLongShow)
    {
        Toast.makeText(context, msg, isLongShow ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}
