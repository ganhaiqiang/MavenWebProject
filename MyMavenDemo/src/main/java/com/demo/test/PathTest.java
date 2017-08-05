package com.demo.test;

import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class PathTest {
	public static void main(String[] args) {
		URL url=PathTest.class.getClassLoader().getResource("jdbc.properties");
		String path=url.getPath();
		System.out.println(path);
		System.out.println(FilenameUtils.getBaseName(path));
		System.out.println(FilenameUtils.getExtension(path));
		System.out.println(FilenameUtils.getPathNoEndSeparator(path));
	}
}
