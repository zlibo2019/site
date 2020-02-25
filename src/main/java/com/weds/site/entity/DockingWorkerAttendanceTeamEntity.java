package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;

public class DockingWorkerAttendanceTeamEntity {
	// 平台为项目分配的接入编号（必填）
	@NotNullAndEmptyValid
	private String projectCode;

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
