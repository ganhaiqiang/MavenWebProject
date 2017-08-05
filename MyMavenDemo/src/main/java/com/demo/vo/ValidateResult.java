package com.demo.vo;

import java.io.Serializable;

/**
 * 属性校验结果类
 * 
 * @author 80001267
 *
 */
public class ValidateResult implements Serializable {
	private static final long serialVersionUID = 1L;

	private String field;
	private String message;

	public ValidateResult(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
