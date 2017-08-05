package com.demo.service;

import java.util.List;

import com.demo.pojo.Student;

public interface RecordsService {
	List getAll();

	Student getById(int id);
}
