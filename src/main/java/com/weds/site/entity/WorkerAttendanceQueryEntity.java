package com.weds.site.entity;

import com.weds.core.base.BaseEntity;
import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

import java.util.List;

public class WorkerAttendanceQueryEntity extends BaseEntity {
	// 平台为项目分配的接入编号（必填）
	@NotNullAndEmptyValid
	private String projectCode;

	// 平台生成的班组编号（必填）
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

	// 刷卡时间。格式：yyyy-MM-dd HH:mm:ss.fff（必填）
	@NotNullAndEmptyValid
	private String date;

	// 刷卡进出方向。参考工人进退场类型字典表（必填）
	private Integer direction;

	// 刷卡近照资源地址。
	private String imageUrl;

	// 通道的名称
	private String channel;

	// 通行方式。参考工人通行方式字典表
	private Integer attendType;

	// WGS84经度
	// private Long lng;
	private double lng;

	// WGS84纬度
	// private Long lat;
	private double lat;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getAttendType() {
		return attendType;
	}

	public void setAttendType(Integer attendType) {
		this.attendType = attendType;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

}
