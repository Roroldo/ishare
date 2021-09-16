package com.roroldo.ishare.util;

import java.util.UUID;

/**
 * 产生UUID随机字符串工具类
 * @author 落霞不孤
 */
public final class UuidUtil {
	private UuidUtil(){}

	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-","");
	}
}
