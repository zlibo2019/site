package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class WorkerUpdateEntity extends BaseEntity {
    // 项目编码（必填）
    @NotNullAndEmptyValid
    private String projectCode;

    // 企业统一社会信用代码。班组所在所属企业统一社会信用代码，如果无统一社会信用代码，则填写组织机构代码（必填）
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
    @NotNullAndEmptyValid
    @ColumnProperties(aes = true)
    private String idCardNumber;

    // 当前工种。参考工人工种字典表（必填）
    @NotNullAndEmptyValid
    private String workType;

    // 工人类型。参考工人类型字典表（必填）
    private Integer workRole;

    // 发卡时间。格式：yyyy-MM-dd
    private String issueCardDate;

    // 办卡采集相片。不超过50KB的Base64字符串
    private String issueCardPic;

    // 考勤卡号
    private String cardNumber;

    // 发放工资银行卡号。AES
    @ColumnProperties(aes = true)
    private String payRollBankCardNumber;

    // 发放工资银行名称
    private String payRollBankName;

    // 工资卡银行联号。
    private String bankLinkNumber;

    // 工资卡银行。参考银行代码字典表
    private String payRollTopBankCode;

    // 是否购买工伤或意外伤害保险 。参考是否字典表
    private Integer hasBuyInsurance;

    // 民族。身份证上民族信息，如：汉，回，藏等（必填）
    @NotNullAndEmptyValid
    private String nation;

    // 住址（必填）
    @NotNullAndEmptyValid
    private String address;

    // 头像。不超过50KB的Base64字符串（必填）
    @NotNullAndEmptyValid
    @ColumnProperties(image = true)
    private String headImage;

    // 政治面貌。参考政治面貌字典表
    private String politicsType;

    // 加入工会时间。格式yyyy-MM-dd
    private String joinedTime;

    // 手机号码（必填）
    @NotNullAndEmptyValid
    private String cellPhone;

    // 文化程度。参考文化程度字典表
    private String cultureLevelType;

    // 特长
    private String Specialty;

    // 是否有重大病史。参考是否字典表
    private Integer hasBadMedicalHistory;

    // 紧急联系人姓名
    private String urgentLinkMan;

    // 紧急联系方式
    private String urgentLinkManPhone;

    // 开始工作日期。格式yyyy-MM-dd
    private String workDate;

    // 婚姻状况。参考婚姻状况字典表
    private String maritalStatus;

    // 发证机关（必填）
    @NotNullAndEmptyValid
    private String grantOrg;

    // 正面照。不超过500KB的BASE64字符串
    private String positiveIDCardImage;

    // 反面照。不超过500KB的BASE64字符串
    private String negativeIDCardImage;

    // 证件有效期开始日期。格式yyyy-MM-dd
    private String startDate;

    // 证件有效期结束日期。格式yyyy-MM-dd
    private String expiryDate;

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

    public String getIssueCardDate() {
        return issueCardDate;
    }

    public void setIssueCardDate(String issueCardDate) {
        this.issueCardDate = issueCardDate;
    }

    public String getIssueCardPic() {
        return issueCardPic;
    }

    public void setIssueCardPic(String issueCardPic) {
        this.issueCardPic = issueCardPic;
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

    public Integer getHasBuyInsurance() {
        return hasBuyInsurance;
    }

    public void setHasBuyInsurance(Integer hasBuyInsurance) {
        this.hasBuyInsurance = hasBuyInsurance;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getPoliticsType() {
        return politicsType;
    }

    public void setPoliticsType(String politicsType) {
        this.politicsType = politicsType;
    }

    public String getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(String joinedTime) {
        this.joinedTime = joinedTime;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCultureLevelType() {
        return cultureLevelType;
    }

    public void setCultureLevelType(String cultureLevelType) {
        this.cultureLevelType = cultureLevelType;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public void setSpecialty(String specialty) {
        Specialty = specialty;
    }

    public Integer getHasBadMedicalHistory() {
        return hasBadMedicalHistory;
    }

    public void setHasBadMedicalHistory(Integer hasBadMedicalHistory) {
        this.hasBadMedicalHistory = hasBadMedicalHistory;
    }

    public String getUrgentLinkMan() {
        return urgentLinkMan;
    }

    public void setUrgentLinkMan(String urgentLinkMan) {
        this.urgentLinkMan = urgentLinkMan;
    }

    public String getUrgentLinkManPhone() {
        return urgentLinkManPhone;
    }

    public void setUrgentLinkManPhone(String urgentLinkManPhone) {
        this.urgentLinkManPhone = urgentLinkManPhone;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGrantOrg() {
        return grantOrg;
    }

    public void setGrantOrg(String grantOrg) {
        this.grantOrg = grantOrg;
    }

    public String getPositiveIDCardImage() {
        return positiveIDCardImage;
    }

    public void setPositiveIDCardImage(String positiveIDCardImage) {
        this.positiveIDCardImage = positiveIDCardImage;
    }

    public String getNegativeIDCardImage() {
        return negativeIDCardImage;
    }

    public void setNegativeIDCardImage(String negativeIDCardImage) {
        this.negativeIDCardImage = negativeIDCardImage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
