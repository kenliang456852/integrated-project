package com.integrated.utils.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: CookieUtils
 * Description:
 * Author: liangchao
 * Date: 2018/6/27 17:32
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class CookieUtils {

    public static String get(String key, HttpServletRequest request) {
        if(null == request || StringUtils.isBlank(key)) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(key.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void set(String key, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        //默认生存时间
        cookie.setMaxAge(30 * 60);
        response.addCookie(cookie);
    }

    public static void del(String key, HttpServletRequest request) {
        if(null != request && StringUtils.isNotBlank(key)) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie: cookies) {
                if(key.equals(cookie.getName())) {
                    cookie.setPath("/");
                    cookie.setValue("");
                    cookie.setMaxAge(0); // 立即销毁cookie
                }
            }
        }

    }

}
