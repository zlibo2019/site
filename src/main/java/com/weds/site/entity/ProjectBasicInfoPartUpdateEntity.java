package com.weds.site.entity;

import java.util.List;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

public class ProjectBasicInfoPartUpdateEntity  extends BaseEntity {

	// 项目编码（必填）
	@NotNullAndEmptyValid
	private String projectCode;

	/*
	 * // 项目名称（必填）
	 * 
	 * @NotNullAndEmptyValid private String name;
	 */

	// 项目简介
	private String description;

	// 建设单位名称
	private String buildCorpName;

	// 建设单位统一社会信用代码，如果无统一社会信用代码，则填写组织机构代码
	private String buildCorpCode;

	/*
	 * // 施工许可证。JSON数组（必填）
	 * 
	 * @ColumnProperties(aes = true) private List<BuilderLicensesEntity>
	 * builderLicenses;
	 */

	// 建设用地规划许可证编号。AES
	@ColumnProperties(aes = true)
	private String buildPlanNum;

	// 建设工程规划许可证编号。AES
	@ColumnProperties(aes = true)
	private String prjPlanNum;

	// 项目分类。参见项目分类字典表（必填）
	@NotNullAndEmptyValid
	private String category;

	/*
	 * // 项目所在地。参考行政区划字典表（必填）
	 * 
	 * @NotNullAndEmptyValid private String areaCode;
	 */

	// 总投资，单位：（万元）
	private Long invest;

	// 总面积，单位：平方米
	private Long buildingArea;

	// 总长度，单位：米
	private Long buildingLength;

	/*
	 * // 开工日期，精确到天，格式yyyy-MM-dd private String startDate;
	 * 
	 * // 竣工日期，精确到天，格式yyyy-MM-dd private String completeDate;
	 */

	// 联系人姓名
	private String linkman;

	// 联系人办公电话
	private String linkPhone;

	// 项目状态。参考项目状态字典表（必填）
	@NotNullAndEmptyValid
	private String prjStatus;

	// WGS84经度
	private Long lat;

	// WGS84纬度
	private Long lng;

	// 项目地点
	private String address;

	// 立项文号
	private String approvalNum;

	// 立项级别。参考立项级别字典表
	private String approvalLevelNum;

	// 建设规模。参考建设规模字典表
	private String prjSize;

	// 建设性质。参考建设性质分类字典表
	private String propertyNum;

	// 工程用途。参考工程用途字典表
	private String functionNum;

	// 国籍或地区。参考国籍及地区字典表
	private Integer nationNum;

	// 第三方项目编码（必填）
	@NotNullAndEmptyValid
	private String thirdPartyProjectCode;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getPrjStatus() {
		return prjStatus;
	}

	public void setPrjStatus(String prjStatus) {
		this.prjStatus = prjStatus;
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

	public Integer getNationNum() {
		return nationNum;
	}

	public void setNationNum(Integer nationNum) {
		this.nationNum = nationNum;
	}

	public String getThirdPartyProjectCode() {
		return thirdPartyProjectCode;
	}

	public void setThirdPartyProjectCode(String thirdPartyProjectCode) {
		this.thirdPartyProjectCode = thirdPartyProjectCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
