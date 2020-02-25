package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;

public class HaiyangRecordListEntity {
	

	// token（必填）
	@NotNullAndEmptyValid
	private String token;
	
	// 项目编号（必填）
	@NotNullAndEmptyValid
	private String projcode;
	
	// 主键唯一值（必填）
	@NotNullAndEmptyValid
	private String id;
	
	// 企业名称（必填）
	@NotNullAndEmptyValid
	private String organname; 

	// 身份证号码（必填）
	@NotNullAndEmptyValid
	private String idcard; 

	// 姓名（必填）
	@NotNullAndEmptyValid
	private String name; 
	
	// 实名ID卡号（必填）
	@NotNullAndEmptyValid
	private String safecard; 


	// 刷卡时间（必填）
	@NotNullAndEmptyValid
	private String time;
	
	// 方向 入，出（必填）
	@NotNullAndEmptyValid
	private String ioflag;

	public String getProjcode() {
		return projcode;
	}

	public void setProjcode(String projcode) {
		this.projcode = projcode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrganname() {
		return organname;
	}

	public void setOrganname(String organname) {
		this.organname = organname;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSafecard() {
		return safecard;
	}

	public void setSafecard(String safecard) {
		this.safecard = safecard;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIoflag() {
		return ioflag;
	}

	public void setIoflag(String ioflag) {
		this.ioflag = ioflag;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
