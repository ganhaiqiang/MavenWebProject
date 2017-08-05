package com.demo.sign;

import java.security.SecureRandom;

public class Util {
	public static String rootCA = "RootCA";
	public static String certCA = "client";

	/**
	 * 生成三位随机数
	 * 
	 * @return
	 */
	public static String getRandomNum() {
		SecureRandom sr = new SecureRandom();
		return String.valueOf(sr.nextInt(10) + sr.nextInt(10) + sr.nextInt(10));
	}

	/**
	 * 验证ip是否合法
	 * 
	 * @param ip
	 *            ip地址
	 * @return ip合法返回true，否则返回false
	 */
	public static boolean ipCheck(String ip) {
		if (ip != null && !ip.isEmpty()) {
			String regex = "^(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|[1-9])\\."
					+ "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\." + "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\."
					+ "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|[1-9])$";
			return ip.matches(regex);
		}
		return false;
	}

	/**
	 * 得到ip的数字，不足三位的在后面补0，调用ipCheck方法之后再调用此方法
	 * 
	 * @param ip
	 * @return
	 */
	public static String getIpNum(String ip) {
		StringBuffer sb = new StringBuffer();
		String len[] = ip.split("\\.");
		for (int i = 0; i < len.length; i++) {
			String s = len[i];
			if (s.length() == 3) {
				sb.append(s);
			} else if (s.length() == 2) {
				sb.append(s).append('0');
			} else if (s.length() == 1) {
				sb.append(s).append("00");
			}
		}
		return sb.toString();
	}
}
