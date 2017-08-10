package com.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.http.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.demo.pojo.CreateOrderParamVo;
import com.demo.pojo.QueryOrderParamVo;
import com.demo.pojo.Student;
import com.demo.utils.HttpUtil;
import com.demo.validate.hibernate.ValidatorUtils;
import com.demo.vo.ArrayVo;
import com.demo.vo.ListParam;
import com.demo.vo.StudentList;
import com.demo.vo.StudentMap;

@Controller
@RequestMapping(value = "/param")
public class ParamController {

	@RequestMapping(value = "/demo1", method = RequestMethod.POST)
	@ResponseBody
	public Object getListParam(@Valid StudentList studentList, BindingResult result) {
		if (result.hasErrors()) {
			return ValidatorUtils.getResultList(result);
		}
		return studentList;
	}

	@RequestMapping(value = "/demo2", method = RequestMethod.POST)
	@ResponseBody
	public Object getMapParam(StudentMap stuMap) {
		Map<String, Student> map = stuMap.getStuMap();
		for (Entry<String, Student> entry : map.entrySet()) {
			System.out.println(JSON.toJSONString(entry.getValue()));
		}
		return stuMap;
	}

	@RequestMapping(value = "/demo3", method = RequestMethod.POST)
	@ResponseBody
	public Object getArrayParam(ArrayVo arrayVo) {
		System.out.println(JSON.toJSONString(arrayVo, true));
		return arrayVo;
	}

	@RequestMapping(value = "/demo4", method = RequestMethod.POST)
	@ResponseBody
	public Object getListParam(ListParam listParam,String name,String other,HttpServletRequest request) throws UnsupportedEncodingException {
//	public Object getListParam(@RequestBody JSONObject object,HttpServletRequest request) {
//		List<ValidateResult> list=ValidatorUtils.validate(listParam);
//		if (list!=null) {
//			System.out.println(JSON.toJSONString(list, true));
//		}
		System.out.println(JSON.toJSONString(listParam, true));
//		System.out.println(URLDecoder.decode(name,"utf-8"));
//		System.out.println(URLDecoder.decode(other,"utf-8"));
		return null;
	}

	@RequestMapping(value = "/demo5", method = RequestMethod.GET)
	@ResponseBody
	public Object getFile() {
//		System.out.println(RSAUtils.signRsa("甘海强"));
		return null;
	}

	@RequestMapping(value = "/demo6", method = RequestMethod.POST)
	@ResponseBody
	public Object getPost(String name, String url) {
		CreateOrderParamVo paramVo = JSON.parseObject(name, CreateOrderParamVo.class);
		paramVo.setRequestTime(new Date());
		paramVo.setSign(null);
		System.out.println(JSON.toJSONString(paramVo, true));
		String jsons = JSON.toJSONStringWithDateFormat(paramVo, "yyyyMMddhhmmssSSS");
		Map<String, Object> map = JSON.parseObject(jsons, Map.class);
		try {
			map.remove("sign");
			// String spString = HttpUtil.splice(map);
			String spString = HttpUtil.map2AsciiStr(map, "sign");
			System.out.println(spString);
			map.put("sign", spString);
			String param = HttpUtil.splice(map);
			System.out.println(param);
			String result = HttpUtil.sendByPost(url, param);
			System.out.println(result);
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/demo7", method = RequestMethod.POST)
	@ResponseBody
	public Object getPayResult(String name, String url) {
		QueryOrderParamVo paramVo = JSON.parseObject(name, QueryOrderParamVo.class);
		paramVo.setRequestTime(new Date());
		paramVo.setSign(null);
		System.out.println(JSON.toJSONString(paramVo, true));
		String jsons = JSON.toJSONStringWithDateFormat(paramVo, "yyyyMMddhhmmssSSS");
		Map<String, Object> map = JSON.parseObject(jsons, Map.class);
		try {
			// map.remove("sign");
			// String spString = HttpUtil.splice(map);
			String spString = HttpUtil.map2AsciiStr(map, "sign");
			System.out.println(spString);
			map.put("sign", spString);
			String param = HttpUtil.splice(map);
			System.out.println(param);
			// String
			// url="https://cpay-sit.sf-pay.com/sys-exp/exp/queryExpressOrder";
			String result = HttpUtil.sendByPost(url, param);
			System.out.println(result);
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
