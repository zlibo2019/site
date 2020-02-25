package com.weds.site.entity;

import java.util.List;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

public class DockingQueryResultEntity extends BaseEntity {
	// 类型(0企业1项目2人员3参建单位4培训5班组)
	private Integer lx;

	// 请求类型（0新增1修改）
	private Integer requestFun;

	// 请求序号
	private String requestSerail;

	// 人员列表数据,JSON数组，数量不能超过5（必填）
	private List<WorkerListEntity> workerList;

	// 人员列表数据,JSON数组，数量不能超过50（必填）
	private List<WorkerListEntryEntity> workerEntryList;

	// 进出场方向
	private Integer enterExitStatus; // 1-进 0-出

	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
	}

	public Integer getRequestFun() {
		return requestFun;
	}

	public void setRequestFun(Integer requestFun) {
		this.requestFun = requestFun;
	}

	public String getRequestSerail() {
		return requestSerail;
	}

	public void setRequestSerail(String requestSerail) {
		this.requestSerail = requestSerail;
	}

	public List<WorkerListEntity> getWorkerList() {
		return workerList;
	}

	public void setWorkerList(List<WorkerListEntity> workerList) {
		this.workerList = workerList;
	}

	public List<WorkerListEntryEntity> getWorkerEntryList() {
		return workerEntryList;
	}

	public void setWorkerEntryList(List<WorkerListEntryEntity> workerEntryList) {
		this.workerEntryList = workerEntryList;
	}

	public Integer getEnterExitStatus() {
		return enterExitStatus;
	}

	public void setEnterExitStatus(Integer enterExitStatus) {
		this.enterExitStatus = enterExitStatus;
	}

}
