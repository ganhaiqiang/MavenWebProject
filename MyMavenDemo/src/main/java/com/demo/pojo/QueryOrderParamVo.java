package com.demo.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 支付结果接口输入参数
 * 
 * @author 80001267
 *
 */
public class QueryOrderParamVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**************************** 系统参数 ****************************/
	private String serviceName = "QUERY_EXP_ORDER"; // 接口名称
	private String serviceVersion = "V1.0.0"; // 接口版本
	private String charset = "UTF-8"; // 字符编码
	private String signType = "RSA"; // 签名方式
	private Date requestTime = new Date(); // 请求时间，格式：yyyyMMddhhmmssSSS
	private String sign; // RSA签名

	/**************************** 业务参数 ****************************/
	private String mchNo=""; // 商户号
	private String sourceCode = "SST"; // 来源渠道
	private String channelType=""; // 渠道类型：顺手付：SFPAY 微信：WXPAY 支付宝：ALIPAY
	private String businessSn=""; // 业务流水号

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@NotNull(message = "商户号不能为空！")
	public String getMchNo() {
		return mchNo;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	@NotNull(message = "业务流水号不能为空！")
	public String getBusinessSn() {
		return businessSn;
	}

	public void setBusinessSn(String businessSn) {
		this.businessSn = businessSn;
	}
}
