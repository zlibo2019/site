package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class WorkerQueryEntity extends BaseEntity {
    // 项目编码（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 工人所在企业统一社会信用代码。如果无统一社会信用代码，则为织机构代码（必填）
    @NotNullAndEmptyValid
    private String corpCode;

    // 企业名称（必填）
    @NotNullAndEmptyValid
    private String corpName;

    // 班组名称（必填）
    @NotNullAndEmptyValid
    private String teamName;

    // 班组编号（必填）
    private Integer teamSysNo;

    // 工人姓名（必填）
    @NotNullAndEmptyValid
    private String workerName;

    // 是否班组长。参考是否字典表（必填）
    private Integer isTeamLeader;

    // 证件类型。参考人员证件类型字典表（必填）
    @NotNullAndEmptyValid
    private String idCardType;

    // 证件号码。AES（必填）
    @ColumnProperties(aes = true)
    @NotNullAndEmptyValid
    private String idCardNumber;

    // 工种。参考工人工种字典表（必填）
    @NotNullAndEmptyValid
    private String workType;

    // 工人类型。参考工人类型字典表（必填）
    private Integer workRole;

    // 进场时间
    private String entryTime;

    // 退场时间
    private String exitTime;

    // 进场确认附件资源地址
    private String entryAttachmentUrl;

    // 退场确认附件资源地址
    private String exitAttachmentUrl;

    // 发卡时间。格式：yyyy-MM-dd
    private String issueCardDate;

    // 办卡采集相片资源地址
    private String issueCardPicUrl;

    // 考勤卡号
    private String cardNumber;

    // 发放工资银行卡号。AES
    @ColumnProperties(aes = true)
    private String payRollBankCardNumber;

    // 发放工资银行名称
    private String payRollBankName;

    // 发放工资总行名称
    private String payRollTopBankName;

    // 工资卡银行联号。
    private String bankLinkNumber;

    // 工资卡银行。参考银行代码字典表
    private String payRollTopBankCode;

    // 是否有劳动合同。参考是否字典表（必填）
    private Integer hasContract;

    // 是否购买工伤或意外伤害保险 。参考是否字典表
    private Integer hasBuyInsurance;

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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(Integer teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public Integer getIsTeamLeader() {
        return isTeamLeader;
    }

    public void setIsTeamLeader(Integer isTeamLeader) {
        this.isTeamLeader = isTeamLeader;
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

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Integer getWorkRole() {
        return workRole;
    }

    public void setWorkRole(Integer workRole) {
        this.workRole = workRole;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getEntryAttachmentUrl() {
        return entryAttachmentUrl;
    }

    public void setEntryAttachmentUrl(String entryAttachmentUrl) {
        this.entryAttachmentUrl = entryAttachmentUrl;
    }

    public String getExitAttachmentUrl() {
        return exitAttachmentUrl;
    }

    public void setExitAttachmentUrl(String exitAttachmentUrl) {
        this.exitAttachmentUrl = exitAttachmentUrl;
    }

    public String getIssueCardDate() {
        return issueCardDate;
    }

    public void setIssueCardDate(String issueCardDate) {
        this.issueCardDate = issueCardDate;
    }

    public String getIssueCardPicUrl() {
        return issueCardPicUrl;
    }

    public void setIssueCardPicUrl(String issueCardPicUrl) {
        this.issueCardPicUrl = issueCardPicUrl;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPayRollBankCardNumber() {
        return payRollBankCardNumber;
    }

    public void setPayRollBankCardNumber(String payRollBankCardNumber) {
        this.payRollBankCardNumber = payRollBankCardNumber;
    }

    public String getPayRollBankName() {
        return payRollBankName;
    }

    public void setPayRollBankName(String payRollBankName) {
        this.payRollBankName = payRollBankName;
    }

    public String getPayRollTopBankName() {
        return payRollTopBankName;
    }

    public void setPayRollTopBankName(String payRollTopBankName) {
        this.payRollTopBankName = payRollTopBankName;
    }

    public String getBankLinkNumber() {
        return bankLinkNumber;
    }

    public void setBankLinkNumber(String bankLinkNumber) {
        this.bankLinkNumber = bankLinkNumber;
    }

    public String getPayRollTopBankCode() {
        return payRollTopBankCode;
    }

    public void setPayRollTopBankCode(String payRollTopBankCode) {
        this.payRollTopBankCode = payRollTopBankCode;
    }

    public Integer getHasContract() {
        return hasContract;
    }

    public void setHasContract(Integer hasContract) {
        this.hasContract = hasContract;
    }

    public Integer getHasBuyInsurance() {
        return hasBuyInsurance;
    }

    public void setHasBuyInsurance(Integer hasBuyInsurance) {
        this.hasBuyInsurance = hasBuyInsurance;
    }
}
