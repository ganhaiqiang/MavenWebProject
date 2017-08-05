/*
 * SalGradeMapper.java
 * Copyright(C) 20xx-2015 xxxxxx��˾
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-06 Created
 */
package com.demo.dao;

import com.demo.entity.SalGrade;

public interface SalGradeMapper {
	int insert(SalGrade record);

	int insertSelective(SalGrade record);
}