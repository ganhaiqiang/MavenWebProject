package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.StudentDao;
import com.project.entity.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	public List<Student> getAllStudent() {
		return studentDao.getAll();
	}
}
