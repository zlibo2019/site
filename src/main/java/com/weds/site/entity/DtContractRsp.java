package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

public class DtContractRsp {
   
	//序号
    private Integer xh;

	//类型
    private Integer lx;
    
	//人员序号
    private Integer userSerial;
  
	//企业序号
    private Integer regSerial;
    
    //图片名称
    private String contractName;
    
    //图片路径
    private String contractPath;

	//是否显示
    private Integer isShow;
     
    //时间
    private String sj;
    
	//图片编号
    private Integer contractBh;

	//图片编号
    private Integer sendStatus;

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}

	public Integer getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(Integer userSerial) {
		this.userSerial = userSerial;
	}

	public Integer getRegSerial() {
		return regSerial;
	}

	public void setRegSerial(Integer regSerial) {
		this.regSerial = regSerial;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractPath() {
		return contractPath;
	}

	public void setContractPath(String contractPath) {
		this.contractPath = contractPath;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public Integer getContractBh() {
		return contractBh;
	}

	public void setContractBh(Integer contractBh) {
		this.contractBh = contractBh;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

    
    
    
}
