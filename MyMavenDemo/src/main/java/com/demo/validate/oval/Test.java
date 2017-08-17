package com.demo.validate.oval;

import java.util.List;
import java.util.Locale;

import com.alibaba.fastjson.JSON;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.configuration.annotation.JPAAnnotationsConfigurer;
import net.sf.oval.localization.locale.LocaleProvider;

public class Test {
	public static void main(String[] args) {
		Validator.setLocaleProvider(new LocaleProvider() {
			
			@Override
			public Locale getLocale() {
				return Locale.KOREA;
			}
		});
		// configure OVal to interprete OVal constraint annotations as well as EJB3 JPA annotations
		Validator validator = new Validator();
		ValidBean validBean = new ValidBean();
//		validBean.setId(10000L);
//		 validBean.setBirthday(new Date());
//		 validBean.setName("");
//		 validBean.setType("a");

//		List<ValidateResult> result = OvalUtils.validate(validBean);
//		System.out.println(JSON.toJSONString(result, true));
		
		List<ConstraintViolation> list=validator.validate(validBean);
		for (ConstraintViolation constraintViolation : list) {
			System.out.println(JSON.toJSONString(constraintViolation.getMessage(), true));
		}
	}
}
