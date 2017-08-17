package com.demo.validate.oval;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Past;

public class ValidBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "id不能为空")
	private Long id;

	@NotBlank(message = "name内容不能为空")
	private String name;

	@NotNull
	@NotBlank
	@MatchPattern(pattern = { "0|1" }, message = "type类型错误")
	private String type;

	@Digits(maxInteger = 3, minInteger = 2, maxFraction = 2, minFraction = 0, message = "price错误")
	private Double price;

	@Past(message = "birthday必须是过去的时间")
	private Date birthday;

	@Max(20)
	private String len;

	private String[] color;

	private List<Integer> subject;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	public String[] getColor() {
		return color;
	}

	public void setColor(String[] color) {
		this.color = color;
	}

	public List<Integer> getSubject() {
		return subject;
	}

	public void setSubject(List<Integer> subject) {
		this.subject = subject;
	}

}
