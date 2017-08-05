package com.demo.test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.demo.pojo.Student;

public class ValidatorTest {
	public static void main(String[] args) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Student Student = new Student();
		Set<ConstraintViolation<Student>> constraintViolations = validator.validate(Student);
		for (ConstraintViolation<Student> constraintViolation : constraintViolations) {
			System.out.println(constraintViolation.getPropertyPath() + ": "+constraintViolation.getMessage());
		}
	}
}
