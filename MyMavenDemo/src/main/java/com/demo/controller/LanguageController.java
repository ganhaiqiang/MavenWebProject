package com.demo.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/lang")
public class LanguageController {
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public Object getLang(HttpServletRequest request, String key) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(key, null, locale);
	}

	@RequestMapping(value = "/getLocale", method = RequestMethod.GET)
	@ResponseBody
	public Object getLocale(HttpServletRequest request, String key) {
		return LocaleContextHolder.getLocale();
	}
}
