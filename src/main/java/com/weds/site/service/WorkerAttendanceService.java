package com.weds.site.service;

import com.google.gson.reflect.TypeToken;
import com.weds.core.base.BaseService;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.CommonService;
import com.weds.site.base.PageSearchRespEntity;
import com.weds.site.constant.SiteConstants;
import com.weds.site.entity.WorkerAttendanceAddEntity;
import com.weds.site.entity.WorkerAttendanceQueryEntity;
import com.weds.site.entity.req.WorkerAttendanceReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/workerAttendanceService")
@Api(description = "工人考勤")
public class WorkerAttendanceService extends BaseService {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "上传工人考勤")
    @RequestMapping(value = "/workerAttendanceAdd", method = RequestMethod.POST)
    public JsonResult<Object> workerAttendanceAdd(@RequestBody @Valid WorkerAttendanceAddEntity workerAttendanceAddEntity) {
        return commonService.sendPostRequest(SiteConstants.WORKERATTENDANCE_ADD, workerAttendanceAddEntity);
    }

    @ApiOperation(value = "查询工人考勤")
    @RequestMapping(value = "/workerAttendanceQuery", method = RequestMethod.POST)
    public JsonResult workerAttendanceQuery(@RequestBody WorkerAttendanceReq workerAttendanceReq) {
        TypeToken typeToken = new TypeToken<PageSearchRespEntity<WorkerAttendanceQueryEntity>>() {
        };
        return commonService.sendPostQueryRequest(SiteConstants.ATTENDANCE_QUERY, workerAttendanceReq, typeToken);
    }
}
