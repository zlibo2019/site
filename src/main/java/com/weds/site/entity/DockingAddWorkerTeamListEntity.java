package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.io.Serializable;

public class DockingAddWorkerTeamListEntity implements Serializable {

	private String regSerial;

	private String teamName;

	public String getRegSerial() {
		return regSerial;
	}

	public void setRegSerial(String regSerial) {
		this.regSerial = regSerial;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
