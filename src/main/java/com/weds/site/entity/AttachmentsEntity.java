package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;

public class AttachmentsEntity {

	private Integer xh;

	// 附件名称（必填）
	@NotNullAndEmptyValid
	private String name;

	// 附件Base64字符串，不超过1M（必填）
	@NotNullAndEmptyValid
	private String data;

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
