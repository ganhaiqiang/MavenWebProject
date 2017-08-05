package com.demo.test;

import java.io.File;
import java.util.HashMap;

import com.demo.utils.AutoCreatePDFUtils;

public class Test3 {
	public static void main(String[] args) {
		File file=new File("D:/contract_template_old.pdf");
		System.out.println(file.getName());
		AutoCreatePDFUtils.createPDFWithTemplate(new HashMap<String, String>(), "D:/contract_template_old.pdf", "D:/abc.pdf");
	}
}
