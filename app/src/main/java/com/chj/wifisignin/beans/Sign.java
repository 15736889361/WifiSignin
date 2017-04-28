package com.chj.wifisignin.beans;

import cn.bmob.v3.BmobObject;

import static android.R.attr.type;

/**
 * 签到及签退实体类
 * author: WEI
 * date: 2017/4/27
 */

public class Sign extends BmobObject
{
    /**
     * 学生编号
     */
    private String num;
    private String username;
    /**
     * 签到时间
     */
    private String signin_time;
    /**
     * 签退时间
     */
    private String signout_time;
    private String routerMac;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSignin_time() {
        return signin_time;
    }

    public void setSignin_time(String signin_time) {
        this.signin_time = signin_time;
    }

    public String getSignout_time() {
        return signout_time;
    }

    public void setSignout_time(String signout_time) {
        this.signout_time = signout_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRouterMac() {
        return routerMac;
    }

    public void setRouterMac(String routerMac) {
        this.routerMac = routerMac;
    }

    @Override
    public String toString() {
        return "Sign{" +
                "num='" + num + '\'' +
                ", username='" + username + '\'' +
                ", signin_time='" + signin_time + '\'' +
                ", signout_time='" + signout_time + '\'' +
                ", routerMac='" + routerMac + '\'' +
                '}';
    }
}
