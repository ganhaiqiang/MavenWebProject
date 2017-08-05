package com.demo.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class DemoFilter
 */
public class DemoFilter implements Filter {

    /**
     * Default constructor. 
     */
    public DemoFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest=(HttpServletRequest) request;
		HttpServletResponse servletResponse=(HttpServletResponse) response;
		System.out.println("============过滤器start=============");
		System.out.println(servletRequest.getRemoteUser());
		System.out.println(servletRequest.getRequestURI());
		System.out.println(servletRequest.getRequestURL());
		System.out.println(servletRequest.getQueryString());
//		Collection<String> names=servletResponse.getHeaderNames();
//		for (String s : names) {
//			System.out.println("响应头："+s+"===>"+servletResponse.getHeader(s));
//		}
//		System.out.println("："+servletResponse.getHeaderNames());
		Map<String, String[]> map=servletRequest.getParameterMap();
		for (Entry<String, String[]> en : map.entrySet()) {
			System.out.println("请求参数============"+en.getKey());
			System.out.println(en.getKey()+"<===>"+en.getValue()[0]);
		}
		System.out.println("============过滤器end=============");
		if (map!=null && map.containsKey("logoutRequest")) {
			System.out.println("logoutRequest："+map.get("logoutRequest"));
			servletResponse.sendRedirect("https://localhost:8443/MyDemo/index.jsp");
		}else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
