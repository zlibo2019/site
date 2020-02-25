package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class WorkerEntryExitQueryEntity extends BaseEntity {
    // 项目编码（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 工人所属企业统一社会信用编码（必填）
    @NotNullAndEmptyValid
    private String corpCode;

    // 工人所属企业名称（必填）
    @NotNullAndEmptyValid
    private String corpName;

    // 班组编号（必填）
    private Integer teamSysNo;

    // 班组名称（必填）
    @NotNullAndEmptyValid
    private String teamName;

    // 证件类型。参考人员证件类型字典表（必填）
    @NotNullAndEmptyValid
    private String idCardType;

    // 证件号码。AES（必填）
    @ColumnProperties(aes = true)
    @NotNullAndEmptyValid
    private String idCardNumber;

    // 进退场日期（必填）
    @NotNullAndEmptyValid
    private String date;

    // 类型。参考工人进退场类型字典表（必填）
    private Integer type;

    // 凭证扫描件资源地址
    private String voucher;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }
}
