package com.weds.site.service;

import com.google.gson.reflect.TypeToken;
import com.weds.core.base.BaseService;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.CommonService;
import com.weds.site.base.PageSearchRespEntity;
import com.weds.site.constant.SiteConstants;
import com.weds.site.entity.CorpInfoEntity;
import com.weds.site.entity.req.CorpInfoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/corpService")
@Api(description = "企业信息")
public class CorpService extends BaseService {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "上传企业基本信息")
    @RequestMapping(value = "/corpUpload", method = RequestMethod.POST)
    public JsonResult<Object> corpUpload(@RequestBody @Valid CorpInfoEntity corpInfoEntity) {
        // CorpInfoEntity record = new CorpInfoEntity();
        // record.setCorpCode("91370613584514282U");
        // record.setCorpName("烟台惠百家房地产经纪有限公司");
        // record.setAreaCode("370613");
        // record.setRegisterDate(DateUtils.getDate());
        // record.setLegalManIDCardNumber("350301198906180060");
        // record.setLegalManIDCardType("01");
        return commonService.sendPostRequest(SiteConstants.CORP_UPLOAD, corpInfoEntity);
    }

    @ApiOperation(value = "查询企业基本信息")
    @RequestMapping(value = "/corpQuery", method = RequestMethod.POST)
    public JsonResult corpQuery(@RequestBody CorpInfoReq corpInfoReq) {
        TypeToken typeToken = new TypeToken<PageSearchRespEntity<CorpInfoEntity>>() {
        };
        return commonService.sendPostQueryRequest(SiteConstants.CORP_QUERY, corpInfoReq, typeToken);
    }

    @ApiOperation(value = "查询企业资质信息")
    @RequestMapping(value = "/corpCredentialQuery", method = RequestMethod.POST)
    public JsonResult<Object> corpCredentialQuery(@RequestBody CorpInfoReq corpInfoReq) {
        return commonService.sendPostRequest(SiteConstants.CORPCREDENTIAL_QUERY, corpInfoReq);
    }
}
