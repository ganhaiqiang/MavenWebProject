package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		// super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		// super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
//			String uri=request.getRequestURI();
//			String path=request.getContextPath();
//			String header=request.getHeader("X-Requested-With");
//			System.out.println("========================================");
//			System.out.println(uri);
//			System.out.println(header);
//			if (uri.contains("/getAll")) {
//				System.out.println("跳转");
//				response.sendRedirect("http://www.baidu.com");
//				return false;
//			}else{
//				System.out.println("放行");
//				return true;
//			}
		return true;
	}
}
