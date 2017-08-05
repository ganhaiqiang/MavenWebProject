/*
 * BonusMapper.java
 * Copyright(C) 20xx-2015 xxxxxx��˾
 * All rights reserved.
 * -----------------------------------------------
 * 2017-08-06 Created
 */
package com.demo.dao;

import com.demo.entity.Bonus;

public interface BonusMapper {
    int insert(Bonus record);

    int insertSelective(Bonus record);
}