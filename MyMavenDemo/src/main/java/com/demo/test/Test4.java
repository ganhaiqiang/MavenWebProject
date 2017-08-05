package com.demo.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Test4 {
	public static void main(String[] args) throws Exception {
		Map<String, String> map=System.getenv();
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey()+"****"+entry.getValue());
		}
		System.out.println("=======================================");
		Properties properties=System.getProperties();
		for (Entry<Object, Object> entry : properties.entrySet()) {
			System.out.println(entry.getKey()+"****"+entry.getValue());
		}
//		SystemEnvironment environment=SystemEnvironment.getSystemEnvironment();
//		System.out.println(environment.getCpuManufacturer());
//		System.out.println(environment.getHostId());
//		System.out.println(environment.getHostname());
//		System.out.println(environment.getOsArchitecture());
//		System.out.println(environment.getOsName());
//		System.out.println(environment.getOsVersion());
//		System.out.println(environment.getSerialNumber());
//		System.out.println(environment.getSystemManufacturer());
//		System.out.println(environment.getSystemModel());
//		
//		System.out.println("操作系统的名称:" + System.getProperty("os.name"));
//		System.out.println("操作系统的版本:" + System.getProperty("os.version"));
//		System.out.println("Java运行时环境版本:" + System.getProperty("java.version"));
//		System.out.println("Java 运行时环境供应商:" + System.getProperty("java.vendor"));
//		System.out.println("Java 供应商的URL:" + System.getProperty("java.vendor.url"));
//		System.out.println("Java 安装目录:" + System.getProperty("java.home"));
//		System.out.println("Java 虚拟机规范版本:" + System.getProperty("java.vm.specification.version"));
//		System.out.println("Java 类格式版本号:" + System.getProperty("java.class.version"));
//		System.out.println("Java 类路径：" + System.getProperty("java.class.path"));
//		System.out.println("加载库时搜索的路径列表:" + System.getProperty("java.library.path"));
//		System.out.println("默认的临时文件路径:" + System.getProperty("java.io.tmpdir"));
//		System.out.println("要使用的 JIT 编译器的名称:" + System.getProperty("java.compiler"));
//		System.out.println("一个或多个扩展目录的路径:" + System.getProperty("java.ext.dirs"));
//		System.out.println("操作系统的名称:" + System.getProperty("os.name"));
//		System.out.println("操作系统的架构:" + System.getProperty("os.arch"));
//		System.out.println("操作系统的版本:" + System.getProperty("os.version"));
//		System.out.println("文件分隔符（在 UNIX 系统中是“/”）:" + System.getProperty("file.separator"));
//		System.out.println("路径分隔符（在 UNIX 系统中是“:”）:" + System.getProperty("path.separator"));
//		System.out.println("行分隔符（在 UNIX 系统中是“/n”）:" + System.getProperty("line.separator"));
//		System.out.println("用户的账户名称:" + System.getProperty("user.name"));
//		System.out.println("用户的主目录:" + System.getProperty("user.home"));
//		System.out.println("用户的当前工作目录: " + System.getProperty("user.dir"));
//
		InetAddress netAddress = getInetAddress();
		System.out.println("host ip:" + getHostIp(netAddress));
		System.out.println("host name:" + getHostName(netAddress));
	}

	/**
	 * 获取本地主机
	 * 
	 * @return
	 */
	public static InetAddress getInetAddress() {
		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("unknown host!");
		}
		return null;
	}

	/**
	 * 通过InetAddress获取本地Ip
	 * 
	 * @param netAddress
	 * @return
	 */
	public static String getHostIp(InetAddress netAddress) {
		if (null == netAddress) {
			return null;
		}
		String ip = netAddress.getHostAddress();
		return ip;
	}

	/**
	 * 通过InetAddress获取本地主机的名字
	 * 
	 * @param netAddress
	 * @return
	 */
	public static String getHostName(InetAddress netAddress) {
		if (null == netAddress) {
			return null;
		}
		String name = netAddress.getHostName();
		return name;
	}
}
