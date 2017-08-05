package com.demo.test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.springframework.web.bind.annotation.RequestMapping;

public class Test {
	public static void main(String[] args) {
		// 获取项目根目录
		File baseDir = SystemUtils.getUserDir();
		// 文件后缀
		String[] extensions = new String[] { "java" };
		// 到根目录下所有.java后缀的文件，第三个参数为true表示包括子目录
		Iterator<File> fs = FileUtils.iterateFiles(baseDir, extensions, true);
		while (fs.hasNext()) {
			File file = fs.next();
			// 转换为没有后缀名的路径
			String fPath = ClassUtils.getPackageCanonicalName(file.getPath());
			// 以src\分割路径
			String[] pArray = StringUtils.splitByWholeSeparator(fPath, "src" + SystemUtils.FILE_SEPARATOR);
			if (pArray.length > 1) {
				// 将路径分隔符替换成"."，得到类的类名className
				String className = StringUtils.replaceChars(pArray[1], SystemUtils.FILE_SEPARATOR, ".");
				try {
					// 将类名转换为类
					Class clazz = ClassUtils.getClass(className, false);
					// 获取类中所有方法
					Method[] methods = clazz.getDeclaredMethods();
					for (Method md : methods) {
						// 得到方法的@RequestMapping注解
						RequestMapping requestMapping = md.getAnnotation(RequestMapping.class);
						if (requestMapping != null) {
							// 得到方法配置的所有URL路径
							String[] urls = requestMapping.value();
							for (String url : urls) {
								System.out.println(url);
							}
						}
					}
				} catch (ClassNotFoundException e) {
					continue;
				}
			}
		}
	}
}
