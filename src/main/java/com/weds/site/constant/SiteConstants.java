package com.weds.site.constant;

public class SiteConstants {
    //正确
    public final static String REQUEST_SUCCESS = "0";
    //请求参数错误
    public final static String REQUEST_ERROR_PARAM = "-1";
    //签名校验错误
    public final static String REQUEST_ERROR_SIGN = "-2";
    //无API访问权限
    public final static String REQUEST_ERROR_ROLE = "-3";
    //IP校验错误
    public final static String REQUEST_ERROR_IP = "-4";
    //访问超过限制
    public final static String REQUEST_ERROR_LIMIT = "-5";
    //错误
    public final static String REQUEST_ERROR = "9999";

    // 上传企业基本信息
    public final static String CORP_UPLOAD = "Corp.Upload";
    // 查询企业信息
    public final static String CORP_QUERY = "Corp.Query";
    // 查询企业资质数据
    public final static String CORPCREDENTIAL_QUERY = "CorpCredential.Query";
    // 上传项目信息
    public final static String PROJECT_ADD = "Project.Add";
    // 修改项目信息
    public final static String PROJECT_UPDATE = "Project.Update";
    // 修改项目信息(一项目一密钥)
    public final static String PROJECT_PARTUPDATE = "Project.PartUpdate";
    // 查询项目信息
    public final static String PROJECT_QUERY = "Project.Query";
    // 上传项目参建单位
    public final static String PROJECTSUBCONTRACTOR_ADD = "ProjectSubContractor.Add";
    // 修改项目参建单位信息
    public final static String PROJECTSUBCONTRACTOR_UPDATE = "ProjectSubContractor.Update";
    // 查询项目参建单位信息
    public final static String PROJECTSUBCONTRACTOR_QUERY = "ProjectSubContractor.Query";
    // 上传班组信息
    public final static String TEAM_ADD = "Team.Add";
    // 修改班组信息
    public final static String TEAM_UPDATE = "Team.Update";
    // 查询班组信息
    public final static String TEAM_QUERY = "Team.Query";
    // 上传项目工人信息
    public final static String PROJECTWORKER_ADD = "ProjectWorker.Add";
    // 修改人员基本信息
    public final static String PROJECTWORKER_UPDATE = "ProjectWorker.Update";
    // 分页查询人员基本信息列表
    public final static String PROJECTWORKER_QUERY = "ProjectWorker.Query";
    // 批量导入项目工人进/退场信息
    public final static String WORKERENTRYEXIT_ADD = "WorkerEntryExit.Add";
    // 分页查询项目工人进/退场信息
    public final static String WORKERENTRYEXIT_QUERY = "WorkerEntryExit.Query";
    // 上传工人合同信息
    public final static String WORKERCONTRACT_ADD = "WorkerContract.Add";
    // 分页查询项目工人合同信息
    public final static String WORKERCONTRACT_QUERY = "WorkerContract.Query";
    // 上传工人考勤
    public final static String WORKERATTENDANCE_ADD = "WorkerAttendance.Add";
    // 查询工人考勤
    public final static String ATTENDANCE_QUERY = "WorkerAttendance.Query";
    // 上传工人工资
    public final static String PAYROLL_ADD = "Payroll.Add";
    // 查询工人工资信息
    public final static String PAYROLL_QUERY = "Payroll.Query";
    // 上传项目培训
    public final static String PROJECTTRAINING_ADD = "ProjectTraining.Add";
    // 查询教育培训
    public final static String PROJECTTRAINING_QUERY = "ProjectTraining.Query";
    // 单一查询人员基本信息列表
    public final static String WORKER_QUERY = "Worker.Query";
    // 查询工人资质数据
    public final static String WORKERCREDENTIAL_QUERY = "WorkerCredential.Query";
    // 查询企业不良行为记录
    public final static String CORPBADRECORD_QUERY = "CorpBadRecord.Query";
    // 查询企业良好行为记录
    public final static String CORPGOODRECORD_QUERY = "CorpGoodRecord.Query";
    // 查询企业黑名单
    public final static String CORPBLACKLIST_QUERY = "CorpBlackList.Query";
    // 查询工人不良行为记录
    public final static String WORKERBADRECORD_QUERY = "WorkerBadRecord.Query";
    // 查询工人良好行为记录
    public final static String WORKERGOODRECORD_QUERY = "WorkerGoodRecord.Query";
    // 查询工人黑名单
    public final static String WORKERBLACKLIST_QUERY = "WorkerBlackList.Query";
    // 异步接口调用结果查询
    public final static String ASYNCHANDLERESULT_QUERY = "AsyncHandleResult.Query";
}
