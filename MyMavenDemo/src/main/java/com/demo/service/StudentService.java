package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.pojo.Student;

public interface StudentService {
	List<Map<String, Object>> getByTable(String table);
	
	int getRows();

	List getAll();
	
	long getCount();
	
	List<Student> getStuPicList(Student student);
	
	List<Student> getStuPic();
	
	List getByPage(Map map);

	Student getById(int id);
	
	List<Map<String, Object>> getStuByCondition(String name,String phone);
	
	int insertStudent(Student student);
	
	int insertStudentByMap(List<Map<String, Object>> list);
	
	int delMemUsers();
}
