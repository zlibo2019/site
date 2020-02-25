package com.weds.site.entity;

import java.util.List;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

// 企业信息
public class DockingProBasicInfoEntity extends BaseEntity {

	// 日志表XH
	private Integer xh;

	// 项目编号
	private Integer bh;

	// 动作：0新增、1修改
	private Integer sendFun;

	// 对接帐号
	private String appId;

	// 对接密钥
	private String secretKey;

	// 对接使用新方式密钥还是老密钥
	private Integer isNewpro;

	// 总承包企业统一社会信用代码或者组织机构代码（必填）
	@NotNullAndEmptyValid
	private String contractorCorpCode;

	// 企业名称（必填）
	@NotNullAndEmptyValid
	private String contractorCorpName;

	// 项目编号（必填）
	@NotNullAndEmptyValid
	private String projectCode;

	// 项目名称（必填）
	@NotNullAndEmptyValid
	private String name;

	// 项目分类（必填）
	@NotNullAndEmptyValid
	private String category;

	// 项目所在地。参考行政区划字典表（必填）
	@NotNullAndEmptyValid
	private String areaCode;

	// 项目状态（必填）
	@NotNullAndEmptyValid
	private String prjStatus;

	// 施工许可证编号（必填）
	@NotNullAndEmptyValid
	private String builderLicenseNum;

	// 施工许可证。JSON数组（必填）
	@ColumnProperties(aes = true)
	private List<BuilderLicensesEntity> builderLicenses;

	// 项目简介
	private String description;

	// 建设单位名称
	private String buildCorpName;

	// 建设单位统一社会信用代码，如果无统一社会信用代码，则用组织机构代码
	private String buildCorpCode;

	// 建设用地规划许可证编号。AES
	private String buildPlanNum;

	// 建设工程规划许可证编号。AES
	private String prjPlanNum;

	// 总投资，单位：（万元）
	private Long invest;

	// 总面积，单位：平方米
	private Long buildingArea;

	// 总长度，单位：米
	private Long buildingLength;

	// 开工日期，精确到天，格式：yyyy-MM-dd
	private String startDate;

	// 竣工日期，精确到天，格式：yyyy-MM-dd
	private String completeDate;

	// 联系人姓名
	private String linkMan;

	// 联系人办公电话
	private String linkPhone;

	// WGS84经度
	private Long lat;

	// WGS84纬度
	private Long lng;

	// 项目地点
	private String address;

	// 立项文号
	private String approvalNum;

	// 立项级别
	private String approvalLevelNum;

	// 建设规模
	private String prjSize;

	// 建设性质
	private String propertyNum;

	// 工程用途
	private String functionNum;

	// 国籍或地区
	private String nationNum;

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public Integer getBh() {
		return bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	public Integer getSendFun() {
		return sendFun;
	}

	public void setSendFun(Integer sendFun) {
		this.sendFun = sendFun;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Integer getIsNewpro() {
		return isNewpro;
	}

	public void setIsNewpro(Integer isNewpro) {
		this.isNewpro = isNewpro;
	}

	public String getContractorCorpCode() {
		return contractorCorpCode;
	}

	public void setContractorCorpCode(String contractorCorpCode) {
		this.contractorCorpCode = contractorCorpCode;
	}

	public String getContractorCorpName() {
		return contractorCorpName;
	}

	public void setContractorCorpName(String contractorCorpName) {
		this.contractorCorpName = contractorCorpName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPrjStatus() {
		return prjStatus;
	}

	public void setPrjStatus(String prjStatus) {
		this.prjStatus = prjStatus;
	}

	public String getBuilderLicenseNum() {
		return builderLicenseNum;
	}

	public void setBuilderLicenseNum(String builderLicenseNum) {
		this.builderLicenseNum = builderLicenseNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBuildCorpName() {
		return buildCorpName;
	}

	public void setBuildCorpName(String buildCorpName) {
		this.buildCorpName = buildCorpName;
	}

	public String getBuildCorpCode() {
		return buildCorpCode;
	}

	public void setBuildCorpCode(String buildCorpCode) {
		this.buildCorpCode = buildCorpCode;
	}

	public String getBuildPlanNum() {
		return buildPlanNum;
	}

	public void setBuildPlanNum(String buildPlanNum) {
		this.buildPlanNum = buildPlanNum;
	}

	public String getPrjPlanNum() {
		return prjPlanNum;
	}

	public void setPrjPlanNum(String prjPlanNum) {
		this.prjPlanNum = prjPlanNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public Long getInvest() {
		return invest;
	}

	public void setInvest(Long invest) {
		this.invest = invest;
	}

	public Long getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(Long buildingArea) {
		this.buildingArea = buildingArea;
	}

	public Long getBuildingLength() {
		return buildingLength;
	}

	public void setBuildingLength(Long buildingLength) {
		this.buildingLength = buildingLength;
	}

	public Long getLat() {
		return lat;
	}

	public void setLat(Long lat) {
		this.lat = lat;
	}

	public Long getLng() {
		return lng;
	}

	public void setLng(Long lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApprovalNum() {
		return approvalNum;
	}

	public void setApprovalNum(String approvalNum) {
		this.approvalNum = approvalNum;
	}

	public String getApprovalLevelNum() {
		return approvalLevelNum;
	}

	public void setApprovalLevelNum(String approvalLevelNum) {
		this.approvalLevelNum = approvalLevelNum;
	}

	public String getPrjSize() {
		return prjSize;
	}

	public void setPrjSize(String prjSize) {
		this.prjSize = prjSize;
	}

	public String getPropertyNum() {
		return propertyNum;
	}

	public void setPropertyNum(String propertyNum) {
		this.propertyNum = propertyNum;
	}

	public String getFunctionNum() {
		return functionNum;
	}

	public void setFunctionNum(String functionNum) {
		this.functionNum = functionNum;
	}

	public String getNationNum() {
		return nationNum;
	}

	public void setNationNum(String nationNum) {
		this.nationNum = nationNum;
	}

	public List<BuilderLicensesEntity> getBuilderLicenses() {
		return builderLicenses;
	}

	public void setBuilderLicenses(List<BuilderLicensesEntity> builderLicenses) {
		this.builderLicenses = builderLicenses;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

}
