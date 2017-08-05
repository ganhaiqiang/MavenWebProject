package com.demo.dao;

import java.io.Serializable;
import java.util.List;

import com.demo.cache.CacheKey;

public interface RecordsDao {
	List getAll();

	<T> T getById(@CacheKey Serializable id);
}
