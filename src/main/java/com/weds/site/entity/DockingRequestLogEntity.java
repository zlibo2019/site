package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

// 企业信息
public class DockingRequestLogEntity extends BaseEntity {

	private Integer lx;

	private String requestSerial;

	private String requestMessage;

	private Integer requestJg;

	private Integer parentId;

	private Integer returnStatus;

	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}

	public String getRequestSerial() {
		return requestSerial;
	}

	public void setRequestSerial(String requestSerial) {
		this.requestSerial = requestSerial;
	}

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public Integer getRequestJg() {
		return requestJg;
	}

	public void setRequestJg(Integer requestJg) {
		this.requestJg = requestJg;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(Integer returnStatus) {
		this.returnStatus = returnStatus;
	}

}
