package com.demo.listener;

import java.text.MessageFormat;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ContextAttributeListener
 * 
 */
public class ContextAttributeListener implements ServletContextAttributeListener {

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		String str = MessageFormat.format("ServletContext域对象中添加了属性:{0}，属性值是:{1}", arg0.getName(), arg0.getValue());
		System.out.println(str);
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		String str = MessageFormat.format("ServletContext域对象中移除了属性:{0}，属性值是:{1}", arg0.getName(), arg0.getValue());
		System.out.println(str);
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		String str = MessageFormat.format("ServletContext域对象中替换了属性:{0}，属性值是:{1}", arg0.getName(), arg0.getValue());
		System.out.println(str);
	}

}
