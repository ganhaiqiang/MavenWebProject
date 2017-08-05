package com.demo.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.demo.vo.ValidateResult;

/**
 * 属性校验工具
 * 
 * @author 80001267
 * 
 */
public class ValidatorUtils {
	private static final Validator VALIDATOR;
	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		VALIDATOR = factory.getValidator();
	}

	/**
	 * 校验对象属性是否合法
	 * 
	 * @param t
	 *            需校验的类实例
	 * @param groups
	 *            校验组
	 * @return
	 */
	public static <T> List<ValidateResult> validate(T t, Class<?>... groups) {
		Set<ConstraintViolation<T>> set = VALIDATOR.validate(t, groups);
		return getValidtionList(set);
	}

	/**
	 * 校验对象单个属性
	 * 
	 * @param t
	 *            被校验对象
	 * @param propertyName
	 *            属性名
	 * @param groups
	 *            校验组
	 * @return
	 */
	public static <T> List<ValidateResult> validateProperty(T t, String propertyName, Class<?>... groups) {
		List<ValidateResult> results = new ArrayList<ValidateResult>();
		Field field = ReflectionUtils.findField(t.getClass(), propertyName);
		if (field == null) {
			results.add(new ValidateResult(propertyName, "属性不存在"));
			return results;
		}
		Set<ConstraintViolation<T>> set = VALIDATOR.validateProperty(t, propertyName, groups);
		return getValidtionList(set);
	}

	/**
	 * controller参数校验结果
	 * 
	 * @param result
	 *            对象校验结果
	 * @return
	 */
	public static List<ValidateResult> getResultList(BindingResult result) {
		List<ValidateResult> results = null;
		List<FieldError> list = result.getFieldErrors();
		if (list != null && list.size() > 0) {
			results = new ArrayList<ValidateResult>();
			for (FieldError error : list) {
				results.add(new ValidateResult(error.getField(), error.getDefaultMessage()));
			}
			return results;
		}
		return null;
	}

	private static <T> List<ValidateResult> getValidtionList(Set<ConstraintViolation<T>> set) {
		if (set != null && set.size() > 0) {
			List<ValidateResult> results = new ArrayList<ValidateResult>();
			for (ConstraintViolation<T> constraintViolation : set) {
				String field = constraintViolation.getPropertyPath().toString();
				String message = constraintViolation.getMessage();
				results.add(new ValidateResult(field, message));
			}
			return results;
		}
		return null;
	}
}
