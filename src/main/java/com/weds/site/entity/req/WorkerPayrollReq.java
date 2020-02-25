package com.weds.site.entity.req;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;
import com.weds.site.base.CustomPageSearch;

public class WorkerPayrollReq extends CustomPageSearch  {
    // 平台为项目分配的接入编码（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 发放工资的月份。仅取年月。格式yyyy-MM-dd（必填）
    @NotNullAndEmptyValid
    private String payMonth;

    // 工人所属企业统一社会信用代码，如果无统一社会信用代码，则填写组织机构代码（必填）
    @NotNullAndEmptyValid
    private String corpCode;

    // 工人所属企业名称（必填）
    @NotNullAndEmptyValid
    private String corpName;

    // 平台为班组分配的接入编号
    private Integer teamSysNo;

    // 工资单编号
    private String payrollCode;

    // 证件类型。参考证件类型字典表
    private String idCardType;

    // 工人身份证号。AES
    @ColumnProperties(aes = true)
    private String idCardNumber;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
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

    public String getPayrollCode() {
        return payrollCode;
    }

    public void setPayrollCode(String payrollCode) {
        this.payrollCode = payrollCode;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }
}
