package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.cache.Cacheable;
import com.demo.cache.Cacheable.KeyMode;
import com.demo.dao.RecordsDao;
import com.demo.pojo.Student;
import com.demo.service.RecordsService;

@Service("recordsService")
public class RecordsServiceImpl implements RecordsService {
	@Autowired
	private RecordsDao recordsDao;

	@Override
	@Cacheable(expire = 6)
	public List getAll() {
		return recordsDao.getAll();
	}

	@Override
	@Cacheable(expire = 6, keyMode = KeyMode.ALL)
	public Student getById(int id) {
		return recordsDao.getById(id);
	}

}
