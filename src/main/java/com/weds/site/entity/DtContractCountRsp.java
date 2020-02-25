package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

public class DtContractCountRsp {
   

	//人员序号
    private Integer userSerial;
  
	//企业下人员合同图片数量
    private Integer userContractCount;

	public Integer getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(Integer userSerial) {
		this.userSerial = userSerial;
	}

	public Integer getUserContractCount() {
		return userContractCount;
	}

	public void setUserContractCount(Integer userContractCount) {
		this.userContractCount = userContractCount;
	}
    
   
    
}
