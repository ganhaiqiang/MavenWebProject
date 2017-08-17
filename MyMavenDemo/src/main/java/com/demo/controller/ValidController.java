package com.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.demo.validate.oval.OvalValid;
import com.demo.validate.oval.ValidBean;
import com.demo.vo.ResultUtil;

@RestController
@RequestMapping("/valid")
public class ValidController {

	@RequestMapping(value = "/oval/valid", method = RequestMethod.POST)
	@OvalValid
	public Object ovalValid(@RequestBody String bean) {
		return ResultUtil.success(bean);
	}

	@RequestMapping(value = "/oval/valid2", method = RequestMethod.POST)
	@OvalValid
	public Object ovalValid2(@RequestBody JSONObject bean) {
		return ResultUtil.success(bean);
	}
	
	@RequestMapping(value = "/oval/valid3", method = RequestMethod.POST)
	@OvalValid
	public Object ovalValid3(@RequestBody Map<String, Object> bean) {
		return ResultUtil.success(bean);
	}
	
	@RequestMapping(value = "/oval/valid4", method = RequestMethod.POST)
	@OvalValid
	public Object ovalValid4(@RequestBody List<ValidBean> bean) {
		return ResultUtil.success(bean);
	}
}
