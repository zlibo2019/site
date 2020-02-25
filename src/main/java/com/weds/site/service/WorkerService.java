package com.weds.site.service;

import com.google.gson.reflect.TypeToken;
import com.weds.core.base.BaseService;
import com.weds.core.utils.coder.Coder;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.CommonService;
import com.weds.site.base.PageSearchRespEntity;
import com.weds.site.constant.SiteConstants;
import com.weds.site.entity.ProjectContractorEntity;
import com.weds.site.entity.WorkerAddEntity;
import com.weds.site.entity.WorkerQueryEntity;
import com.weds.site.entity.WorkerUpdateEntity;
import com.weds.site.entity.req.WorkerReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.net.www.content.image.jpeg;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/workerService")
@Api(description = "项目工人信息")
public class WorkerService extends BaseService {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "上传项目工人信息")
    @RequestMapping(value = "/workerAdd", method = RequestMethod.POST)
    public JsonResult<Object> workerAdd(@RequestBody @Valid WorkerAddEntity workerAddEntity) {
        // try {
        //     String temp = "data:image/jpeg;base64," + Coder.encryptBASE64(IOUtils.toByteArray(new FileInputStream("E:\\Users\\SSS\\Downloads\\11112222.jpeg")));
        //     workerAddEntity.getWorkerList().get(0).setHeadImage(temp);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        return commonService.sendPostRequest(SiteConstants.PROJECTWORKER_ADD, workerAddEntity);
    }

    @ApiOperation(value = "修改项目工人信息")
    @RequestMapping(value = "/workerUpdate", method = RequestMethod.POST)
    public JsonResult<Object> workerUpdate(@RequestBody @Valid WorkerUpdateEntity workerUpdateEntity) {
        return commonService.sendPostRequest(SiteConstants.PROJECTWORKER_UPDATE, workerUpdateEntity);
    }

    @ApiOperation(value = "查询项目工人信息")
    @RequestMapping(value = "/workerQuery", method = RequestMethod.POST)
    public JsonResult workerQuery(@RequestBody WorkerReq workerReq) {
        TypeToken typeToken = new TypeToken<PageSearchRespEntity<WorkerQueryEntity>>() {
        };
        return commonService.sendPostQueryRequest(SiteConstants.PROJECTWORKER_QUERY, workerReq, typeToken);
    }
}
