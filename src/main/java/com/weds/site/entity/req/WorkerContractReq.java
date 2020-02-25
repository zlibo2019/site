package com.weds.site.entity.req;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;
import com.weds.site.base.CustomPageSearch;

public class WorkerContractReq extends CustomPageSearch {
    // 项目编码（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 工人所属企业统一社会信用编码
    private String corpCode;

    // 工人所属企业名称
    private String corpName;

    // 证件类型。参考人员证件类型字典表
    private String idCardType;

    // 证件号码。AES
    @ColumnProperties(aes = true)
    private String IdCardNumber;

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

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return IdCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        IdCardNumber = idCardNumber;
    }
}
