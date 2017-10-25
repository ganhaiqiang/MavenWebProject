package com.demo.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import org.joda.time.DateTime;

public class Test8 {
	public static void main(String[] args) {
		BigDecimal bd = new BigDecimal("555.55");
		System.out.println(bd.longValue());
	}
}
