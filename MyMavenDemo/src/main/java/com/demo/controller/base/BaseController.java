package com.demo.controller.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice
public class BaseController {

	@ModelAttribute
	public Map<String, Object> newUser() {
		System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model");
		Map<String, Object> map = new HashMap<>();
		map.put("name", "张三");
		return map;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器");
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String processUnauthenticatedException(NativeWebRequest request, Exception e) {
		System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出UnauthenticatedException异常时执行");
		return "viewName";
	}
}
