/*
 * DeptMapper.java
 * Copyright(C) 20xx-2015 xxxxxx��˾
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-06 Created
 */
package com.demo.dao;

import com.demo.entity.Dept;

public interface DeptMapper {
	int deleteByPrimaryKey(Long deptno);

	int insert(Dept record);

	int insertSelective(Dept record);

	Dept selectByPrimaryKey(Long deptno);

	int updateByPrimaryKeySelective(Dept record);

	int updateByPrimaryKey(Dept record);
}