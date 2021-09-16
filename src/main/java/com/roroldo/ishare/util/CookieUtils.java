package com.roroldo.ishare.util;

import javax.servlet.http.Cookie;

/**
 * cookie工具类
 * @author 落霞不孤
 */
public class CookieUtils {
    /**
     * 获取指定的 cookie
     * @param allCookie 所有的cookie信息
     * @param cookieName 指定的cookie名字
     * @return 指定的 cookie
     */
    public static Cookie getCookie(Cookie[] allCookie, String cookieName) {
        if (cookieName == null || allCookie == null) {
            return null;
        }
        if (allCookie != null) {
            for (Cookie cookie : allCookie) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
