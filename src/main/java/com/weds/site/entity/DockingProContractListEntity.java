package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

// 企业信息
public class DockingProContractListEntity {

	// 项目编号
	private Integer proCode;

	// 中建项目编号
	private String projectCode;

	private Integer regSerial;

	private String corpCode;

	public Integer getProCode() {
		return proCode;
	}

	public void setProCode(Integer proCode) {
		this.proCode = proCode;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Integer getRegSerial() {
		return regSerial;
	}

	public void setRegSerial(Integer regSerial) {
		this.regSerial = regSerial;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

}
