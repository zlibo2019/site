package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;

public class DockingWorkerProTeamEntity {

	// 平台为项目分配的接入编号（必填）
	@NotNullAndEmptyValid
	private String projectCode;

	// 所在企业统一社会信用代码（必填）
	@NotNullAndEmptyValid
	private String corpCode;

	// 所在企业名称（必填）
	@NotNullAndEmptyValid
	private String corpName;

	// 班组编号（必填）
	private Integer teamSysNo;

	// 班组名称（必填）
	@NotNullAndEmptyValid
	private String teamName;

	// 企业号
	private String regSerial;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public Integer getTeamSysNo() {
		return teamSysNo;
	}

	public void setTeamSysNo(Integer teamSysNo) {
		this.teamSysNo = teamSysNo;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getRegSerial() {
		return regSerial;
	}

	public void setRegSerial(String regSerial) {
		this.regSerial = regSerial;
	}

}
