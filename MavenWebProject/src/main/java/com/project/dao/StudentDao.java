package com.project.dao;

import java.util.List;

import com.project.entity.Student;

public interface StudentDao {
	List<Student> getAll();

	Student getStudentById(Integer id);

	int addStudent(Student student);

	int updateStudent(Student student);

	int deleteStudentByIds(List<Integer> ids);
}
