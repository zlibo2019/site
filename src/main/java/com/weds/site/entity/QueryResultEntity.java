package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

public class QueryResultEntity extends BaseEntity {
	// 请求码（必填）
	@NotNullAndEmptyValid
	private String requestSerialCode;

	public String getRequestSerialCode() {
		return requestSerialCode;
	}

	public void setRequestSerialCode(String requestSerialCode) {
		this.requestSerialCode = requestSerialCode;
	}

}
