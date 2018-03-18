package com.demo.aop;

import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.demo.cache.CacheKey;
import com.demo.cache.Cacheable;
import com.demo.cache.Cacheable.KeyMode;
import com.demo.vo.LogAppender;

/**
 * AOP环绕增强，拦截带有@cacheable注解的方法
 * 
 * @author 80001267
 * 
 */
@Aspect
@Component
public class CacheableAop {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAppender.AOP);

	@Resource(name = "redisTemplate")
	private ValueOperations<String, Object> valueOper;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Around("@annotation(cache)")
	public Object cached(final ProceedingJoinPoint pjp, Cacheable cache) throws Throwable {
		// HashOperations<String, Object, Object> hashOperations =
		// redisTemplate.opsForHash();
		// HyperLogLogOperations<String, Object> hyperLogLogOperations =
		// redisTemplate.opsForHyperLogLog();
		// ListOperations<String, Object> listOperations =
		// redisTemplate.opsForList();
		// SetOperations<String, Object> setOperations =
		// redisTemplate.opsForSet();
		// ValueOperations<String, Object> valueOperations =
		// redisTemplate.opsForValue();
		// ZSetOperations<String, Object> zSetOperations =
		// redisTemplate.opsForZSet();

		String key = getCacheKey(pjp, cache);
		LOGGER.info("key===>" + key);
		Object value = null;
		try {
			value = valueOper.get(key);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		if (value == null) {
			LOGGER.info("缓存为空，重新获取数据");
			value = pjp.proceed(); // 跳过缓存,到后端查询数据
			try {
				if (cache.expire() <= 0) { // 如果没有设置过期时间,则无限期缓存
					valueOper.set(key, value);
				} else { // 否则设置缓存时间
					valueOper.set(key, value, cache.expire(), TimeUnit.SECONDS);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return value;
	}

	/**
	 * 获取缓存的key值
	 * 
	 * @param pjp
	 * @param cache
	 * @return
	 */
	private String getCacheKey(ProceedingJoinPoint pjp, Cacheable cache) {
		StringBuilder buf = new StringBuilder();
		buf.append(pjp.getSignature().getDeclaringTypeName()).append('.').append(pjp.getSignature().getName());
		if (cache.key().length() > 0) {
			buf.append('.').append(cache.key());
		}
		Object[] args = pjp.getArgs();
		if (cache.keyMode() == KeyMode.DEFAULT) {
			Annotation[][] pas = ((MethodSignature) pjp.getSignature()).getMethod().getParameterAnnotations();
			for (int i = 0; i < pas.length; i++) {
				for (Annotation an : pas[i]) {
					if (an instanceof CacheKey) {
						buf.append('.').append(args[i].toString());
						break;
					}
				}
			}
		} else if (cache.keyMode() == KeyMode.BASIC) {
			for (Object arg : args) {
				if (arg instanceof String) {
					buf.append('.').append(arg);
				} else if (arg instanceof Integer || arg instanceof Long || arg instanceof Short) {
					buf.append('.').append(arg.toString());
				} else if (arg instanceof Boolean) {
					buf.append('.').append(arg.toString());
				}
			}
		} else if (cache.keyMode() == KeyMode.ALL) {
			for (Object arg : args) {
				buf.append('.').append(arg.toString());
			}
		}
		return buf.toString();
	}
}
