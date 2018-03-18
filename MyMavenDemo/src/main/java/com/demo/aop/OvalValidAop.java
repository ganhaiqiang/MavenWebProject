package com.demo.aop;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.demo.validate.oval.OvalUtils;
import com.demo.validate.oval.OvalValid;
import com.demo.validate.oval.ValidateResult;
import com.demo.vo.LogAppender;
import com.demo.vo.ResultUtil;

@Aspect
@Component
public class OvalValidAop {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAppender.AOP);

	@Around("@annotation(ovalValid)")
	public Object cached(final ProceedingJoinPoint pjp, OvalValid ovalValid) throws Throwable {
		Object[] args = pjp.getArgs();
		for (Object object : args) {
			List<ValidateResult> list = OvalUtils.validate(object);
			if (CollectionUtils.isNotEmpty(list)) {
				LOGGER.info(JSON.toJSONString(list, true));
				return ResultUtil.error(list.get(0).getErrorMessage());
			}
		}
		return pjp.proceed();
	}
}
