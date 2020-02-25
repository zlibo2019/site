package com.weds.site.entity;

import com.weds.framework.core.validation.NotNullAndEmptyValid;
import com.weds.site.annotation.ColumnProperties;

public class DockingDataListEntity {
	// 记录bh
	private Integer bh;

	// 证件类型。参考人员证件类型字典表（必填）
	@NotNullAndEmptyValid
	private String idCardType;

	// 证件号码。AES（必填）
	@NotNullAndEmptyValid
	@ColumnProperties(aes = true)
	private String idCardNumber;

	// 刷卡时间，yyyy-MM-dd HH:mm:ss.fff（必填）
	@NotNullAndEmptyValid
	private String date;

	// 刷卡进出方向。参考工人进退场类型字典表（必填）
	private String direction;

	// 刷卡近照。Base64字符串，不超过50KB
	private String image;

	// 通道的名称
	private String channel;

	// 通行方式。参考工人通行方式字典表
	private Integer attendType;

	// WGS84经度
	private Long lng;

	// WGS84纬度
	private Long lat;

	public Integer getBh() {
		return bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public Long getLng() {
		return lng;
	}

	public void setLng(Long lng) {
		this.lng = lng;
	}

	public Long getLat() {
		return lat;
	}

	public void setLat(Long lat) {
		this.lat = lat;
	}
}
