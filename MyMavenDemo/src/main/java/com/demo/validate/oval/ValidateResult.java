package com.demo.validate.oval;

import java.io.Serializable;

public class ValidateResult implements Serializable {
	private static final long serialVersionUID = 1L;

	private String field;
	private String errorCode;
	private String errorMessage;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
