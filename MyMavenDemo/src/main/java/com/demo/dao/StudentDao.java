package com.demo.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.demo.pojo.Student;

public interface StudentDao {
	List<Map<String, Object>> getByTable(@Param("table") String table);

	int getRows();

	@MapKey("id")
	Map<Integer, Student> getAllMap();

	List getAll();

	long getCount();

	List<Student> getStuPicList(Student student);

	List<Student> getStuPic();

	List getByPage(Map map);

	<T> T getById(Serializable id);

	List<Map<String, Object>> getStuByCondition(@Param("name") String name, @Param("phone") String phone);

	int insertStudent(Student student);

	int insertStudentByMap(List<Map<String, Object>> list);

	int delMemUsers();
}
