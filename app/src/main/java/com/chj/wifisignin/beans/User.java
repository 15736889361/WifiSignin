package com.chj.wifisignin.beans;

/**
 * author: WEI
 * date: 2017/4/26
 */

public class User
{
    /**
     *  学生/老师编号
     */
    private long no;
    private String phone;
    private String name;
    private String password;
    private String signin_time;
    private String signout_time;
    /**
     * type = 1，身份为老师
     * type = 0，身份为学生
     */
    protected int type;

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
