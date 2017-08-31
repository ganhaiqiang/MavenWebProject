package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("★★★★★★★★★");
		String uri = request.getRequestURI();
		System.out.println(uri);
		HandlerMethod hm = (HandlerMethod) handler;
		System.out.println(hm.getBean().getClass().getTypeName()+ "\t" + hm.getMethod().getName());
		return true;
	}
}
