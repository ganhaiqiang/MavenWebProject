package com.demo.vo;

import java.io.Serializable;
import java.util.Map;

import com.demo.pojo.Student;

public class StudentMap implements Serializable {
	private static final long serialVersionUID = 1L;

	private Map<String, Student> stuMap = new LazyMap<String, Student>(Student.class);

	public StudentMap() {
		super();
	}

	public Map<String, Student> getStuMap() {
		return stuMap;
	}

	public void setStuMap(Map<String, Student> stuMap) {
		this.stuMap = stuMap;
	}

}
