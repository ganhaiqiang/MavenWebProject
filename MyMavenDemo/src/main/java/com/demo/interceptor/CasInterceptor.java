package com.demo.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CasInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("afterCompletion方法执行后。。。");
		System.out.println("====================");
		System.out.println(request.getRemoteUser());
		Enumeration<String> es = request.getSession().getAttributeNames();
		while (es.hasMoreElements()) {
			String string = es.nextElement();
			System.out.println("**********" + string + "**********" + request.getSession().getAttribute(string));
		}
		System.out.println("----------------------------");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("preHandle====================");
		String type = request.getHeader("X-Requested-With");// XMLHttpRequest
		System.out.println(type);
		// 重定向
		// response.sendRedirect(basePath+"index.jsp");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

}
