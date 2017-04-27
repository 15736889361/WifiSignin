package com.chj.wifisignin.login.model;

import com.chj.wifisignin.beans.User;

/**
 * author: WEI
 * date: 2017/4/26
 */

public interface ILoginModel
{
    boolean verifyUser(User user);
}
