package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/param")
public class ParamTestController {

	@RequestMapping(value = { "/postJson" }, method = RequestMethod.POST)
	@ResponseBody
	public Object jsonParam() {
		return null;
	}
}
