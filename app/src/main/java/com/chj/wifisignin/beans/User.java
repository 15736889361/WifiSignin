package com.chj.wifisignin.beans;

import cn.bmob.v3.BmobObject;

/**
 * author: WEI
 * date: 2017/4/26
 */

public class User extends BmobObject
{
    /**
     *  学生/老师编号(唯一)
     */
    private String num;
    private String phone;
    private String username;
    private String password;
    /**
     * 路由器的mac地址，也是唯一的
     */
    private String routerMac;
    /**
     * type = 1，身份为老师
     * type = 0，身份为学生
     */
    protected int type;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "num='" + num + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", routerMac='" + routerMac + '\'' +
                ", type=" + type +
                '}';
    }

    public void setRouterMac(String routerMac) {
        this.routerMac = routerMac;
    }

    public String getRouterMac()
    {
        return routerMac;
    }
}
