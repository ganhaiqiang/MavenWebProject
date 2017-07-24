package com.project.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/param")
public class ParamTestController {

	@RequestMapping(value = { "/jsonParam" }, method = RequestMethod.POST, headers = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object jsonParam() {
		return null;
	}

	@RequestMapping(value = { "/stringParam" }, method = RequestMethod.POST)
	@ResponseBody
	public Object stringParam() {
		return null;
	}

	@RequestMapping(value = { "/jsonObjectParam" }, method = RequestMethod.POST)
	@ResponseBody
	public Object jsonObjectParam() {
		return null;
	}

	@RequestMapping(value = { "/mapParam" }, method = RequestMethod.POST)
	@ResponseBody
	public Object mapParam() {
		return null;
	}

	@RequestMapping(value = { "/pojoParam" }, method = RequestMethod.POST)
	@ResponseBody
	public Object pojoParam() {
		return null;
	}
}
