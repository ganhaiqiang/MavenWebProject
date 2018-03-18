package com.demo.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;

public class Test8 {
	public static void main(String[] args) {
		List<List<String>> fList=new LinkedList<>();
		List<String> cList =new ArrayList<>();
		cList.add("aaaaaaaaaaa");
		fList.add(cList);
		cList.add("bbbbbbbbbb");
		System.out.println(JSON.toJSONString(fList, true));
	}
}
