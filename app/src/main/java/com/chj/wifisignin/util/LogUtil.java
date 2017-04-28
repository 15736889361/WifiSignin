package com.chj.wifisignin.util;

import android.util.Log;

/**
 * author: WEI
 * date: 2017/4/28
 */

public class LogUtil
{
    private static boolean ISDEBUG = true;
    
    public static void i(String tag, String msg) {
        //if (logLevel <= android.util.Log.INFO)
        if (ISDEBUG)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        //if (logLevel <= android.util.Log.ERROR)
        if (ISDEBUG)
            android.util.Log.e(tag, msg);
    }

    public static void d(String tag, String msg) {
        //if (logLevel <= android.util.Log.DEBUG)
        if (ISDEBUG)
            android.util.Log.d(tag, msg);
    }

    public static void v(String tag, String msg) {
        //if (logLevel <= android.util.Log.VERBOSE)
        if (ISDEBUG)
            android.util.Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        //if (logLevel <= android.util.Log.WARN)
        if (ISDEBUG)
            android.util.Log.w(tag, msg);
    }

    ///////////////////////
    public static void i(String tag, String msg, Throwable cause) {
        //if (logLevel <= android.util.Log.INFO)
        if (ISDEBUG)
            android.util.Log.i(tag, msg, cause);
    }

    public static void e(String tag, String msg, Throwable cause) {
        //if (logLevel <= android.util.Log.ERROR)
        if (ISDEBUG)
            android.util.Log.e(tag, msg, cause);
    }

    public static void d(String tag, String msg, Throwable cause) {
        //if (logLevel <= android.util.Log.DEBUG)
        if (ISDEBUG)
            android.util.Log.d(tag, msg, cause);
    }

    public static void v(String tag, String msg, Throwable cause) {
        //if (logLevel <= android.util.Log.VERBOSE)
        if (ISDEBUG)
            android.util.Log.v(tag, msg, cause);
    }

    public static void w(String tag, String msg, Throwable cause) {
        //if (logLevel <= android.util.Log.WARN)
        if (ISDEBUG)
            android.util.Log.w(tag, msg, cause);
    }
}
