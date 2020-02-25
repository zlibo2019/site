package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

// 企业信息
public class CorpInfoEntity extends BaseEntity {
    // 企业统一社会信用代码或者组织机构代码（必填）
    @NotNullAndEmptyValid
    private String corpCode;

    // 企业名称（必填）
    @NotNullAndEmptyValid
    private String corpName;

    // 单位性质。参考企业登记注册类型字典表
    private String corpType;

    // 工商营业执照注册号
    private String licenseNum;

    // 企业注册地区编码。参考行政区划字典表（必填）
    @NotNullAndEmptyValid
    private String areaCode;

    // 企业营业地址
    private String address;

    // 邮政编码
    private String zipCode;

    // 法定代表人姓名
    private String legalMan;

    // 法定代表人职务
    private String legalManDuty;

    // 法定代表人职称
    private String legaManProTitle;

    // 法定代表人证件类型。参考人员证件类型字典表
    private String legalManIDCardType;

    // 法定代表人证件号码。AES
    @ColumnProperties(aes = true)
    private String legalManIDCardNumber;

    // 注册资本（万元）
    private double regCapital;

    // 实收资本(万元)
    private double factRegCapital;

    // 注册资本币种。参考币种字典表
    private String capitalCurrencyType;

    // 注册日期，格式yyyy-MM-dd（必填）
    @NotNullAndEmptyValid
    private String registerDate;

    // 成立日期，格式yyyy-MM-dd
    private String establishDate;

    // 办公电话
    private String officePhone;

    // 传真号码
    private String faxNumber;

    // 联系人姓名
    private String linkman;

    // 联系人办公电话
    private String linkTel;

    // 企业联系邮箱
    private String email;

    // 企业网址
    private String website;

    // 企业备注
    private String remark;

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

    public String getCorpType() {
        return corpType;
    }

    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLegalMan() {
        return legalMan;
    }

    public void setLegalMan(String legalMan) {
        this.legalMan = legalMan;
    }

    public String getLegalManDuty() {
        return legalManDuty;
    }

    public void setLegalManDuty(String legalManDuty) {
        this.legalManDuty = legalManDuty;
    }

    public String getLegaManProTitle() {
        return legaManProTitle;
    }

    public void setLegaManProTitle(String legaManProTitle) {
        this.legaManProTitle = legaManProTitle;
    }

    public String getLegalManIDCardType() {
        return legalManIDCardType;
    }

    public void setLegalManIDCardType(String legalManIDCardType) {
        this.legalManIDCardType = legalManIDCardType;
    }

    public String getLegalManIDCardNumber() {
        return legalManIDCardNumber;
    }

    public void setLegalManIDCardNumber(String legalManIDCardNumber) {
        this.legalManIDCardNumber = legalManIDCardNumber;
    }

    public double getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(double regCapital) {
        this.regCapital = regCapital;
    }

    public double getFactRegCapital() {
        return factRegCapital;
    }

    public void setFactRegCapital(double factRegCapital) {
        this.factRegCapital = factRegCapital;
    }

    public String getCapitalCurrencyType() {
        return capitalCurrencyType;
    }

    public void setCapitalCurrencyType(String capitalCurrencyType) {
        this.capitalCurrencyType = capitalCurrencyType;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
