package com.roroldo.ishare.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期字符串工具
 * @author 落霞不孤
 */
public class DateUtil {
    public static String getCurrentTimeDateStr() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        return sdf.format(date);
    }
}
