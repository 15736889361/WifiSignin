package com.chj.wifisignin.beans;

import static android.R.attr.type;

/**
 * 签到及签退实体类
 * author: WEI
 * date: 2017/4/27
 */

public class Sign
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
    private String rounterMac;

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

    public String getRounterMac() {
        return rounterMac;
    }

    public void setRounterMac(String rounterMac) {
        this.rounterMac = rounterMac;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
