package com.demo.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XSSHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(encodeXSS(name));
		if (value != null) {
			value = encodeXSS(value);
		}
		System.out.println("name:"+name+"，value:"+value);
		return value;
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(encodeXSS(name));
		if (value != null) {
			value = encodeXSS(value);
		}
		System.out.println("name:"+name+"，value:"+value);
		return value;
	}

	private String encodeXSS(String s) {
		if (s == null || "".equals(s)) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('＞');
				break;
			case '<':
				sb.append('＜');
				break;
			case '\'':
				sb.append('‘');
				break;
			case '\"':
				sb.append('“');
				break;
			case '&':
				sb.append('＆');
				break;
			case '\\':
				sb.append('＼');
				break;
			case '#':
				sb.append('＃');
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}
}
