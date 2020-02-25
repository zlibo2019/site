package com.weds.site.entity;

public class DtProjectRsp {

	//项目编号
    private Integer bh;

	//企业序号
    private Integer regSerial;
    
    //proName名称
    private String proName;

    //token名称
    private String token;
    
    //projcode名称
    private String projcode;
    
    
	public Integer getBh() {
		return bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	public Integer getRegSerial() {
		return regSerial;
	}

	public void setRegSerial(Integer regSerial) {
		this.regSerial = regSerial;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getProjcode() {
		return projcode;
	}

	public void setProjcode(String projcode) {
		this.projcode = projcode;
	}
    
  
    
    
}
