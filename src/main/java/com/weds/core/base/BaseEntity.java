package com.weds.core.base;

import com.weds.framework.core.common.model.OrderBy;

import java.util.Map;

/**
 * 实体父类
 *
 * @author sxm
 */
public class BaseEntity extends OrderBy {
	@Override
	public Map<String, String> getOrderByFieldMap() {
		return null;
	}

	private Map<String, String> dicFmtMap;

	public Map<String, String> getDicFmtMap() {
		return dicFmtMap;
	}

	public void setDicFmtMap(Map<String, String> dicFmtMap) {
		this.dicFmtMap = dicFmtMap;
	}

	protected String appId;

	protected String secretKey;

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
}
