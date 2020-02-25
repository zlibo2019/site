package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class DockingProContractorEntity {

	// 日志表XH
	private Integer xh;

	// 企业ID
	private Integer id;

	// 动作：0新增、1修改
	private Integer sendFun;

	private String zhangHao;

	private String miYao;

	// 项目编码（必填）
	@NotNullAndEmptyValid
	private String projectCode;

	// 统一社会信用代码，如果无统一社会信用代码，则填写组织机构代码（必填）
	@NotNullAndEmptyValid
	private String corpCode;

	// 项目所属企业信用代码
	private String danw;

	// 企业名称（必填）
	@NotNullAndEmptyValid
	private String corpName;

	// 参建类型。参考参建单位类型字典表（必填）
	@NotNullAndEmptyValid
	private String corpType;

	// 进场时间。格式yyyy-MM-dd HH:mm:ss
	private String entryTime;

	// 退场时间。格式yyyy-MM-dd HH:mm:ss
	private String exitTime;

	// 发放工资的银行。JSON数组
	@ColumnProperties(aes = true)
	private List<BankInfosEntity> bankInfos;

	// 项目经理名称
	private String pmName;

	// 项目经理证件类型。参考人员证件类型字典表
	private String pmIDCardType;

	// 项目经理证件号码。AES
	@ColumnProperties(aes = true)
	private String pmIDCardNumber;

	// 项目经理电话
	private String pmPhone;

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSendFun() {
		return sendFun;
	}

	public void setSendFun(Integer sendFun) {
		this.sendFun = sendFun;
	}

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

	public String getCorpType() {
		return corpType;
	}

	public void setCorpType(String corpType) {
		this.corpType = corpType;
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

	public List<BankInfosEntity> getBankInfos() {
		return bankInfos;
	}

	public void setBankInfos(List<BankInfosEntity> bankInfos) {
		this.bankInfos = bankInfos;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public String getPmIDCardType() {
		return pmIDCardType;
	}

	public void setPmIDCardType(String pmIDCardType) {
		this.pmIDCardType = pmIDCardType;
	}

	public String getPmIDCardNumber() {
		return pmIDCardNumber;
	}

	public void setPmIDCardNumber(String pmIDCardNumber) {
		this.pmIDCardNumber = pmIDCardNumber;
	}

	public String getPmPhone() {
		return pmPhone;
	}

	public void setPmPhone(String pmPhone) {
		this.pmPhone = pmPhone;
	}

	public String getDanw() {
		return danw;
	}

	public void setDanw(String danw) {
		this.danw = danw;
	}

	public String getZhangHao() {
		return zhangHao;
	}

	public void setZhangHao(String zhangHao) {
		this.zhangHao = zhangHao;
	}

	public String getMiYao() {
		return miYao;
	}

	public void setMiYao(String miYao) {
		this.miYao = miYao;
	}

}
