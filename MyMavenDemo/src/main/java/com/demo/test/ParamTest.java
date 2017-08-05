package com.demo.test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.Student;
import com.demo.vo.StudentMap;

public class ParamTest {
	public static void main(String[] args) {
		StudentMap studentMap = new StudentMap();
		Student student = new Student();
		student.setId(222222222);
		student.setName("=========");
		Student student2 = new Student();
		student2.setId(111111111);
		student2.setName("*************");
		Map<String, Student> map = new HashMap<String, Student>();
		studentMap.setStuMap(map);
		studentMap.getStuMap().put("a", student);
		studentMap.getStuMap().put("b", student2);
		System.out.println(JSON.toJSONString(studentMap));
		StudentMap sMap = JSONObject.parseObject(JSON.toJSONString(studentMap), StudentMap.class);
		System.out.println(JSON.toJSONString(sMap));
	}
}
