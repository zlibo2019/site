package com.weds.site.base;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.weds.core.base.BaseEntity;
import com.weds.core.base.BaseService;
import com.weds.core.utils.DateUtils;
import com.weds.core.utils.StringUtils;
import com.weds.core.utils.coder.AES7Coder;
import com.weds.core.utils.coder.AESCoder;
import com.weds.core.utils.coder.Coder;
import com.weds.core.utils.http.HttpUtils;
import com.weds.site.util.CustomJsonUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class HttpRequestService extends BaseService {

    @Value("${request.url}")
    private String requestUrl;

//    @Value("${param.appid}")
//    private String appId;

//    @Value("${param.secretkey}")
//    private String secretKey;
   
    @Value("${param.imageRoot}")
    private String imageRoot;
    
    @Value("${param.imageType}")
    private String imageType;

    @Autowired
    private RestTemplate restTemplate;
    
/*    public static void main(String[] args) {
       String aaa = "/ry1xfMD4i2Da2vFrNc1m5mlOE39gkLBlQfrCZPjVqc=";
//    	String aaa="370602198512305792";
       try {
//		System.out.println(AES7Coder.encrypt(aaa, "d2eea4fadb76f6cd81190bb8bd5fea2b"));
		System.out.println(AES7Coder.decrypt("/ry1xfMD4i2Da2vFrNc1m5mlOE39gkLBlQfrCZPjVqc=", "d2eea4fadb76f6cd81190bb8bd5fea2b"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }*/

    public <T> ResponseEntity<T> sendPostRequest(String methodName, Object requestData, Class<T> responseType) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
//        String appId= ((BaseEntity)requestData).getAppId();
//        String secretKey= ((BaseEntity)requestData).getSecretKey();
 
        String appId=JSONObject.parseObject(JSONObject.toJSONString(requestData)).get("appId").toString();
        String secretKey=JSONObject.parseObject(JSONObject.toJSONString(requestData)).get("secretKey").toString();
        SimpleDateFormat tmdf = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
    	
        param.add("appid", appId);
        param.add("data", CustomJsonUtils.parseObjToJson(requestData, secretKey, imageRoot, imageType));
        param.add("format", "json");
        param.add("method", methodName);
        param.add("nonce", StringUtils.getUUID());
//        param.add("timestamp", DateUtils.getCurrentDateTime());
        param.add("timestamp", tmdf.format(new Date()));
        param.add("version", "1.0");
        param.add("appsecret", secretKey);

        Map<String, String> map = param.toSingleValueMap();
        String sign = DigestUtils.sha256Hex(HttpUtils.parseUrlParam(map, false).toLowerCase());
        param.add("sign", sign);
        param.remove("appsecret");
        //将参数和header组成一个请求
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(param, headers);
        return restTemplate.postForEntity(requestUrl, request, responseType);
    }
}
