package com.chj.wifisignin.beans;

/**
 * author: WEI
 * date: 2017/4/26
 */

public class User
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
    private String rounterMac;
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
