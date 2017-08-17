package com.demo.validate.oval;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import net.sf.oval.localization.locale.LocaleProvider;

public final class OvalUtils {
	private static final Validator VALIDATOR = new Validator();

	private OvalUtils() {
		Validator.setLocaleProvider(new LocaleProvider() {

			@Override
			public Locale getLocale() {
				return Locale.PRC;
			}
		});
	}

	public static <T> List<ValidateResult> validate(T t, String... profiles) {
		List<ConstraintViolation> list = null;
		if (profiles.length == 0) {
			list = VALIDATOR.validate(t);
		} else {
			list = VALIDATOR.validate(t, profiles);
		}
		return createResult(list);
	}

	public static <T> List<ValidateResult> validate(T t, Field validatedField, Object fieldValueToValidate) {
		List<ConstraintViolation> list = VALIDATOR.validateFieldValue(t, validatedField, fieldValueToValidate);
		return createResult(list);
	}

	private static List<ValidateResult> createResult(List<ConstraintViolation> list) {
		if (CollectionUtils.isNotEmpty(list)) {
			List<ValidateResult> results = new ArrayList<>();
			for (ConstraintViolation constraintViolation : list) {
				ValidateResult result = new ValidateResult();
				result.setErrorCode(constraintViolation.getErrorCode());
				result.setErrorMessage(constraintViolation.getMessage());
				OValContext ct = constraintViolation.getCheckDeclaringContext();
				if (ct instanceof FieldContext) {
					FieldContext fContext = (FieldContext) ct;
					result.setField(fContext.getField().getName());
				}
				results.add(result);
			}
			return results;
		}
		return Collections.emptyList();
	}
}
