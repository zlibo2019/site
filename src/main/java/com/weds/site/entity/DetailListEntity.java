package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

public class DetailListEntity {
//	private Integer bh;
	private Integer userSerial;

	// 证件类型。参考人员证件类型字典表（必填）
	@NotNullAndEmptyValid
	private String idCardType;

	// 证件号码。AES（必填）
	@NotNullAndEmptyValid
	@ColumnProperties(aes = true)
	private String idCardNumber;

	// 出勤天数，单位：天
	private Integer days;

	// 总工时，单位：小时
	private Long workHours;

	// 工人工资卡号。AES（必填）
	@NotNullAndEmptyValid
	@ColumnProperties(aes = true)
	private String payRollBankCardNumber;

	// 工人工资卡银行代码, 参考银行代码字典表（必填）
	@NotNullAndEmptyValid
	private String payRollBankCode;

	// 工人工资卡开户行名称（必填）
	@NotNullAndEmptyValid
	private String payRollBankName;

	// 工资代发银行卡号。AES（必填）
	@NotNullAndEmptyValid
	@ColumnProperties(aes = true)
	private String payBankCardNumber;

	// 工资代发银行代码, 参考银行代码字典表（必填）
	@NotNullAndEmptyValid
	private String payBankCode;

	// 工资代发开户行名称（必填）
	@NotNullAndEmptyValid
	private String payBankName;

	// 应发金额，单位为元（必填）
//	private Long totalPayAmount;
	private double totalPayAmount;

	// 实发金额，单位为元（必填）
	@NotNullAndEmptyValid
//	private Long actualAmount;
	private double actualAmount;

	// 是否为补发。参考是否字典表（必填）
	private Integer isBackPay;

	// 发放日期。格式yyyy-MM-dd（必填）
	@NotNullAndEmptyValid
	private String balanceDate;

	// 补发月份，如果是补发，此字段必填。格式yyyy-MM
	private String backPayMonth;

	// 第三方工资单编号（必填）
	@NotNullAndEmptyValid
	private String thirdPayRollCode;

	public Integer getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(Integer userSerial) {
		this.userSerial = userSerial;
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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Long getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Long workHours) {
		this.workHours = workHours;
	}

	public String getPayRollBankCardNumber() {
		return payRollBankCardNumber;
	}

	public void setPayRollBankCardNumber(String payRollBankCardNumber) {
		this.payRollBankCardNumber = payRollBankCardNumber;
	}

	public String getPayRollBankCode() {
		return payRollBankCode;
	}

	public void setPayRollBankCode(String payRollBankCode) {
		this.payRollBankCode = payRollBankCode;
	}

	public String getPayRollBankName() {
		return payRollBankName;
	}

	public void setPayRollBankName(String payRollBankName) {
		this.payRollBankName = payRollBankName;
	}

	public String getPayBankCardNumber() {
		return payBankCardNumber;
	}

	public void setPayBankCardNumber(String payBankCardNumber) {
		this.payBankCardNumber = payBankCardNumber;
	}

	public String getPayBankCode() {
		return payBankCode;
	}

	public void setPayBankCode(String payBankCode) {
		this.payBankCode = payBankCode;
	}

	public String getPayBankName() {
		return payBankName;
	}

	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}

	public double getTotalPayAmount() {
		return totalPayAmount;
	}

	public void setTotalPayAmount(double totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}

	public double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Integer getIsBackPay() {
		return isBackPay;
	}

	public void setIsBackPay(Integer isBackPay) {
		this.isBackPay = isBackPay;
	}

	public String getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getBackPayMonth() {
		return backPayMonth;
	}

	public void setBackPayMonth(String backPayMonth) {
		this.backPayMonth = backPayMonth;
	}

	public String getThirdPayRollCode() {
		return thirdPayRollCode;
	}

	public void setThirdPayRollCode(String thirdPayRollCode) {
		this.thirdPayRollCode = thirdPayRollCode;
	}
}
