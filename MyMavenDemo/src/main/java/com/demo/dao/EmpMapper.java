/*
 * EmpMapper.java
 * Copyright(C) 20xx-2015 xxxxxx��˾
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-06 Created
 */
package com.demo.dao;

import com.demo.entity.Emp;

public interface EmpMapper {
	int deleteByPrimaryKey(Integer empno);

	int insert(Emp record);

	int insertSelective(Emp record);

	Emp selectByPrimaryKey(Integer empno);

	int updateByPrimaryKeySelective(Emp record);

	int updateByPrimaryKey(Emp record);
}