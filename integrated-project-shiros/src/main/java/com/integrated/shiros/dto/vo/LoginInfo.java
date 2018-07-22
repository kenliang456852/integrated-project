package com.integrated.shiros.dto.vo;

import java.io.Serializable;

/**
 * ClassName: LoginInfo
 * Description:
 * Author: liangchao
 * Date: 2018/7/22 20:03
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class LoginInfo implements Serializable {

    private String userName;
    private String password;

    public LoginInfo() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginInfo{" + "userName='" + userName + '\'' + ", password='" + password + '\'' + '}';
    }
}
