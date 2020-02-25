package com.weds.site.base;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weds.core.base.BaseService;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.constant.SiteConstants;
import com.weds.site.util.CustomJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class CommonService extends BaseService {

    @Value("${param.secretkey}")
    private String secretKey;

    @Autowired
    private HttpRequestService httpRequestService;

    public JsonResult<Object> sendPostRequest(String methodName, Object requestData) {
        try {
            ResponseEntity<HttpResponEntity> obj = httpRequestService.sendPostRequest(methodName, requestData, HttpResponEntity.class);
            if (!obj.getBody().getCode().equals(SiteConstants.REQUEST_SUCCESS)) {
                return failMsg(obj.getBody().getMessage());
            } else {
                return succMsg(obj.getBody().getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failMsg();
        }
    }

    public <T> JsonResult<T> sendPostQueryRequest(String methodName, Object requestData, TypeToken<T> typeToken) {
        try {
            ResponseEntity<HttpResponEntity> obj = httpRequestService.sendPostRequest(methodName, requestData, HttpResponEntity.class);
            if (!obj.getBody().getCode().equals(SiteConstants.REQUEST_SUCCESS)) {
                return failMsg(obj.getBody().getMessage());
            } else {
            	
//                String appId=JSONObject.parseObject(JSONObject.toJSONString(requestData)).get("appId").toString();
                String secKey=JSONObject.parseObject(JSONObject.toJSONString(requestData)).get("secretKey").toString();
            	
                Type type = typeToken.getType();
                Gson gson = new Gson();
                T rtn = gson.fromJson(JSONObject.toJSONString(obj.getBody().getData()), type);
                CustomJsonUtils.decryptObject(rtn, secKey);
                return succMsg(rtn);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failMsg();
        }
    }
}
