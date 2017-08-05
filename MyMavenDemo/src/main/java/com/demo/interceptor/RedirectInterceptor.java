package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RedirectInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// String path = request.getContextPath();
		// if (request.getSession().getAttribute("user") == null) {
		// request.getSession().setAttribute("user", "gan");
		// response.sendRedirect(path + "/stu/error");
		// return false;
		// }
		// Gson gson=new Gson();
		// System.out.println("=============拦截器=============");
		// System.out.println(request.getRequestURL());
		// System.out.println(arg2.getClass().getName());
		// if (arg2 instanceof DemoController) {
		// DemoController dc=(DemoController)arg2;
		// System.out.println("==========yes============");
		// }
		// System.out.println("=============拦截器=============");
		return true;
	}

}
