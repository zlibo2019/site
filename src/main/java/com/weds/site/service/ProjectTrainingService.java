package com.weds.site.service;

import com.google.gson.reflect.TypeToken;
import com.weds.core.base.BaseService;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.CommonService;
import com.weds.site.base.PageSearchRespEntity;
import com.weds.site.constant.SiteConstants;
import com.weds.site.entity.ProjectTrainingEntity;
import com.weds.site.entity.req.ProjectTrainingReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/projectTrainingService")
@Api(description = "项目培训")
public class ProjectTrainingService extends BaseService {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "上传项目培训")
    @RequestMapping(value = "/projectTrainingAdd", method = RequestMethod.POST)
    public JsonResult<Object> projectTrainingAdd(@RequestBody @Valid ProjectTrainingEntity projectTrainingEntity) {
        return commonService.sendPostRequest(SiteConstants.PROJECTTRAINING_ADD, projectTrainingEntity);
    }

    @ApiOperation(value = "查询项目培训")
    @RequestMapping(value = "/projectTrainingQuery", method = RequestMethod.POST)
    public JsonResult projectTrainingQuery(@RequestBody ProjectTrainingReq projectTrainingReq) {
        TypeToken typeToken = new TypeToken<PageSearchRespEntity<ProjectTrainingEntity>>() {
        };
        return commonService.sendPostQueryRequest(SiteConstants.PROJECTTRAINING_QUERY, projectTrainingReq, typeToken);
    }
}
