/*
 * EmpMapper.java
 * Copyright(C) 20xx-2015 xxxxxx��˾
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-24 Created
 */
package com.project.dao;

import com.project.entity.Emp;

public interface EmpMapper {
	int deleteByPrimaryKey(Integer empno);

	int insert(Emp record);

	int insertSelective(Emp record);

	Emp selectByPrimaryKey(Integer empno);

	int updateByPrimaryKeySelective(Emp record);

	int updateByPrimaryKey(Emp record);
}