package com.demo.test;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class Test5 {
	public static void main(String[] args) {
		try {
			ResourcePatternResolver  resolver = new PathMatchingResourcePatternResolver();
			Resource[] resources = resolver.getResources("i18n/**/*.properties");
			for (Resource resource : resources) {
				System.out.println(resource.getFilename());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
