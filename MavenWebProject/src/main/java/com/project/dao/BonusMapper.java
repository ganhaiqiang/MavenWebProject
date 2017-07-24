/*
 * BonusMapper.java
 * Copyright(C) 20xx-2015 xxxxxx��˾
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-24 Created
 */
package com.project.dao;

import com.project.entity.Bonus;

public interface BonusMapper {
	int insert(Bonus record);

	int insertSelective(Bonus record);
}