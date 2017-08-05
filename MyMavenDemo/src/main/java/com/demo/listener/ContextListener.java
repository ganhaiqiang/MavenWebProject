package com.demo.listener;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

import com.demo.utils.SpringUtils;
import com.demo.utils.UrlMappingUtils;

public class ContextListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		System.out.println("ContextLoaderListener  contextInitialized方法执行");
//		DefaultAnnotationHandlerMapping mapping=SpringUtils.getBean(DefaultAnnotationHandlerMapping.class);
//		Map<String, Object> map=mapping.getHandlerMap();
//		for (Entry<String, Object> en : map.entrySet()) {
//			System.out.println(en.getKey()+"<===>"+en.getValue().getClass());
//			UrlMappingUtils.initKeyValue(en.getKey(), en.getValue());
//		}
//		StudentService studentService = SpringUtils.getBean(StudentService.class);
//		System.out.println(JSON.toJSONString(studentService.getAll(), true));
	}

}
