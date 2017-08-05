package com.demo.utils;

import java.io.IOException;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * FreeMarker帮助类
 * 
 * @author 80001267
 * 
 */
public class FreeMarkerUtils {
	private static Configuration configuration;

	static {
		WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		FreeMarkerConfig freeMarkerConfig = (FreeMarkerConfig) applicationContext.getBean("freemarkerConfig");
		configuration = freeMarkerConfig.getConfiguration();
	}

	/**
	 * 获取模板对象
	 * 
	 * @param ftlName
	 *            ftl文件名
	 * @return 模板对象
	 */
	private static Template getTemplate(String ftlName) {
		try {
			Template template = configuration.getTemplate(ftlName);
			template.setEncoding("UTF-8");
			return template;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * FTL模板生成字符串
	 * 
	 * @param ftlName
	 *            ftl文件名
	 * @param model
	 *            填充数据
	 * @return
	 */
	public static String getHTML(String ftlName, Object model) {
		String html = "";
		try {
			Template template = getTemplate(ftlName);
			html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return html;
	}
}
