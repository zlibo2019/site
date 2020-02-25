package com.weds.site.service;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.weds.core.utils.coder.AES7Coder;
import com.weds.core.utils.coder.Coder;
import com.weds.framework.core.common.model.JsonResult;
import com.weds.site.base.PageSearchRespEntity;
import com.weds.site.entity.AttachmentsEntity;
import com.weds.site.entity.BankInfosEntity;
import com.weds.site.entity.BuilderLicensesEntity;
import com.weds.site.entity.ContractListEntity;
import com.weds.site.entity.CorpInfoEntity;
import com.weds.site.entity.DataListEntity;
import com.weds.site.entity.DetailListEntity;
import com.weds.site.entity.DockingAddSendContractLogEntity;
import com.weds.site.entity.DockingCorpInfoEntity;
import com.weds.site.entity.DockingGetCorpKeyIntity;
import com.weds.site.entity.DockingInsertSuccessLogEntity;
import com.weds.site.entity.DockingPayRollTeamEntity;
import com.weds.site.entity.DockingProBasicInfoEntity;
import com.weds.site.entity.DockingProContractListEntity;
import com.weds.site.entity.DockingProContractorEntity;
import com.weds.site.entity.DockingProTrainingEntity;
import com.weds.site.entity.DockingQueryResultEntity;
import com.weds.site.entity.DockingQueryResultInfoEntity;
import com.weds.site.entity.DockingRequestLogEntity;
import com.weds.site.entity.DockingTeamEntity;
import com.weds.site.entity.DockingWorkerAttendanceTeamEntity;
import com.weds.site.entity.DockingWorkerProTeamEntity;
import com.weds.site.entity.DockingWorkerUpdateEntity;
import com.weds.site.entity.PayUserEntity;
import com.weds.site.entity.ProjectBasicInfoAddEntity;
import com.weds.site.entity.ProjectBasicInfoPartUpdateEntity;
import com.weds.site.entity.ProjectBasicInfoUpdateEntity;
import com.weds.site.entity.ProjectContractorEntity;
import com.weds.site.entity.ProjectTeamAddEntity;
import com.weds.site.entity.ProjectTeamQueryEntity;
import com.weds.site.entity.ProjectTeamUpdateEntity;
import com.weds.site.entity.ProjectTrainingEntity;
import com.weds.site.entity.QueryResultEntity;
import com.weds.site.entity.UpdateSendLogEntity;
import com.weds.site.entity.UpdateSendLogbyUseridEntity;
import com.weds.site.entity.WorkerAddEntity;
import com.weds.site.entity.WorkerAttendanceAddEntity;
import com.weds.site.entity.WorkerContractAddEntity;
import com.weds.site.entity.WorkerEntryExitAddEntity;
import com.weds.site.entity.WorkerListEntity;
import com.weds.site.entity.WorkerListEntryEntity;
import com.weds.site.entity.WorkerPayrollAddEntity;
import com.weds.site.entity.WorkerQueryEntity;
import com.weds.site.entity.WorkerUpdateEntity;
import com.weds.site.entity.WorkersEntity;
import com.weds.site.entity.req.CorpInfoReq;
import com.weds.site.entity.req.ProjectBasicInfoReq;
import com.weds.site.entity.req.ProjectContractorReq;
import com.weds.site.entity.req.ProjectTeamReq;
import com.weds.site.entity.req.WorkerReq;
import com.weds.site.mapper.DataDockingMapper;

@Service
public class DataDockingService {

	@Autowired
	private DataDockingMapper dataDockingMapper;

	@Autowired
	private CorpService corpService;

	@Autowired
	private ProjectBasicService projectBasicService;
	
	@Autowired
	private ProjectContractorService projectContractorService;
	
	@Autowired
	private ProjectTeamService projectTeamService;
	
	@Autowired
	private ProjectTrainingService projectTrainingService;
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private WorkerEntryExitService workerEntryExitService;
	
	@Autowired
	private WorkerContractService workerContractService;
	
	@Autowired
	private WorkerAttendanceService workerAttendanceService;
	
	@Autowired
	private WorkerPayrollService workerPayrollService;
	
	@Autowired
	private ResultService resultService;
	
    @Value("${param.secretkey}")
    private String secretKey;
    
    @Value("${PDFUrl}")
    private String PDFUrl;
    
	public void SendCorpInfo()  {// 0-企业信息
	try {
		
		CorpInfoReq corpInfoReq = new CorpInfoReq();
		Integer corpCnt =-1;
		String appId;
		String secretKey;
		List<DockingCorpInfoEntity> list = dataDockingMapper.getCorpInfo();
		if (list.size() != 0) {
			for (DockingCorpInfoEntity dockingCorpInfoEntity : list) {//a.
				appId=dockingCorpInfoEntity.getZhangHao();
				secretKey=dockingCorpInfoEntity.getMiYao();
				if(appId!="" && appId != null & secretKey !="" && secretKey != null) {//b.
					
					CorpInfoEntity corpInfoEntity = new CorpInfoEntity();
					
					if (dockingCorpInfoEntity.getSendFun() == 0) {
	//					新增企业需判断企业是否已上传
						corpInfoReq.setCorpCode(dockingCorpInfoEntity.getCorpCode());
						corpInfoReq.setPageIndex(0);
						corpInfoReq.setPageSize(50);
						corpInfoReq.setAppId(appId);//"37061320190310001"
						corpInfoReq.setSecretKey(secretKey);//"6ae19b6719bff8745fcadae189682ec6"
						JsonResult<PageSearchRespEntity<CorpInfoEntity>> pro1 = corpService.corpQuery(corpInfoReq);
						if(pro1.getCode() == 600) {
						corpCnt=pro1.getData().getTotalCount();}
						
					}

					if((dockingCorpInfoEntity.getSendFun() == 0 && corpCnt == 0) ||dockingCorpInfoEntity.getSendFun() == 1) {
						
						BeanUtils.copyProperties(dockingCorpInfoEntity, corpInfoEntity);
						corpInfoEntity.setAppId(appId); 
						corpInfoEntity.setSecretKey(secretKey);
						JSONObject res = JSONObject.parseObject(JSONObject.toJSONString(corpService.corpUpload(corpInfoEntity)));
						
	//					更新日志表记录返回信息
						Map<String, Object> res1  = new HashMap<String, Object>();
						res1=getResult(res);
						String requestSerial = res1.get("requestSerial").toString();
						String msg =res1.get("msg").toString();
						Integer code = Integer.parseInt(res1.get("code").toString()) ;
						
	//					更新日志表记录返回信息
						updateSendLog(dockingCorpInfoEntity.getXh(), code , msg , requestSerial );
						
						if(code == 600) {
	/*						//插入成功日志表
							if(dockingCorpInfoEntity.getSendFun() == 0) {
								DockingInsertSuccessLogEntity  insertLogEntity = new DockingInsertSuccessLogEntity();
								insertLogEntity.setParentId(dockingCorpInfoEntity.getCorpCode());
								insertLogEntity.setParentXh(dockingCorpInfoEntity.getId() );
								insertLogEntity.setRegSerial(dockingCorpInfoEntity.getRegSerial());
								insertLogEntity.setSendLx(0);
								
								InsertSuccessLog(insertLogEntity);
							}*/
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							
							//休眠
					        Thread.sleep(2000);
		
							//获取异常调用结果
					        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
					        dockingQueryResultEntity.setLx(0);
					        dockingQueryResultEntity.setRequestSerail(requestSerial);
					        dockingQueryResultEntity.setAppId(appId);
					        dockingQueryResultEntity.setSecretKey(secretKey);
					        QueryProBasicCode(dockingQueryResultEntity);
						}
					}else {
						
						updateSendLog(dockingCorpInfoEntity.getXh(), 600 , "企业已存在" , "" );
					}
				}//b.
			}//a.
		}
		
    } catch (Exception e) {
    	System.err.println(e.getMessage());
        e.printStackTrace();
    }
}

	
	
	public void SendProBasicInfo() throws Exception {// 1-项目信息
		
		JSONObject res = null;
		ProjectBasicInfoAddEntity projectBasicInfoAddEntit = new ProjectBasicInfoAddEntity();
		ProjectBasicInfoUpdateEntity projectBasicInfoUpdateEntity = new ProjectBasicInfoUpdateEntity();
		ProjectBasicInfoPartUpdateEntity partUpdateEntity = new ProjectBasicInfoPartUpdateEntity();
		BuilderLicensesEntity builderLicensesEntity = new BuilderLicensesEntity();
		ProjectBasicInfoReq proBasicInfoReq = new ProjectBasicInfoReq();
		CorpInfoReq corpInfoReq = new CorpInfoReq();
		Integer corpCnt =-1;
		Integer proCnt =-1;
		
		List<DockingProBasicInfoEntity> list = dataDockingMapper.getProBasicInfo();
		if (list.size() != 0) {
			for (DockingProBasicInfoEntity dockingProBasicInfoEntity : list) {//a.
				
				//判断项目是否已存在
				String zhangHao=dockingProBasicInfoEntity.getAppId();
				String miYao=dockingProBasicInfoEntity.getSecretKey();
				proCnt=ProjectCnt(dockingProBasicInfoEntity.getContractorCorpCode(),dockingProBasicInfoEntity.getProjectCode(),zhangHao,miYao);
				/*proBasicInfoReq.setContractorCorpCode(dockingProBasicInfoEntity.getContractorCorpCode());
				proBasicInfoReq.setProjectCode(dockingProBasicInfoEntity.getProjectCode());
				proBasicInfoReq.setPageIndex(0);
				proBasicInfoReq.setPageSize(50);
				JsonResult<PageSearchRespEntity<ProjectBasicInfoAddEntity>> pro2 = projectBasicService.projectQuery(proBasicInfoReq);
				proCnt=pro2.getData().getTotalCount();*/
/*					
				if (dockingProBasicInfoEntity.getSendFun() == 0 && corpCnt > 0 && proCnt == 0|| 
						(dockingProBasicInfoEntity.getSendFun() == 1 && dockingProBasicInfoEntity.getPrjStatus() !="004" && proCnt > 0)) {
						b. 修改时过滤掉已竣工的项目

					if (dockingProBasicInfoEntity.getSendFun() == 0) {//新增处理			
						
						BeanUtils.copyProperties(dockingProBasicInfoEntity, projectBasicInfoAddEntit);
					
						List<BuilderLicensesEntity> list1 = dataDockingMapper.getProBuilderLicenses(dockingProBasicInfoEntity.getBh());
						if (list1.size() != 0) {
							projectBasicInfoAddEntit.setBuilderLicenses(list1);
						}
						
						res = JSONObject.parseObject(JSONObject.toJSONString(projectBasicService.projectAdd(projectBasicInfoAddEntit)));
						
					}else {//修改项目
				
*/
			if (dockingProBasicInfoEntity.getSendFun() == 1 && dockingProBasicInfoEntity.getPrjStatus() !="004" && proCnt > 0) {//b. 修改时过滤掉已竣工的项目
				
//				if (dockingProBasicInfoEntity.getSendFun() == 1) {	
					if(dockingProBasicInfoEntity.getIsNewpro() == 1) { //旧方式
						BeanUtils.copyProperties(dockingProBasicInfoEntity, projectBasicInfoUpdateEntity);
						
						List<BuilderLicensesEntity> list1 = dataDockingMapper.getProBuilderLicenses(dockingProBasicInfoEntity.getBh());
						projectBasicInfoUpdateEntity.setBuilderLicenses(list1);
							
						res = JSONObject.parseObject(JSONObject.toJSONString(projectBasicService.projectUpdate(projectBasicInfoUpdateEntity)));	
						
					}else {
						BeanUtils.copyProperties(dockingProBasicInfoEntity, partUpdateEntity);
						partUpdateEntity.setAppId(zhangHao);
						partUpdateEntity.setSecretKey(miYao);
												
						res = JSONObject.parseObject(JSONObject.toJSONString(projectBasicService.PartUpdate(partUpdateEntity)));	
					}

//				}
//					System.out.println("jsonResult--" + res);
					
//					更新日志表记录返回信息
					Map<String, Object> res1  = new HashMap<String, Object>();
					res1=getResult(res);
					String requestSerial = res1.get("requestSerial").toString();
					String msg =res1.get("msg").toString();
					Integer code = Integer.parseInt(res1.get("code").toString()) ;	
					
//					更新日志表记录返回信息
					updateSendLog(dockingProBasicInfoEntity.getXh(), code , msg , requestSerial );
					
					if(code == 600) {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						//休眠
				        try {
				        	Thread.sleep(2000);
	
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
						
						//获取异常调用结果
				        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
				        dockingQueryResultEntity.setLx(1);
				        dockingQueryResultEntity.setRequestFun(dockingProBasicInfoEntity.getSendFun());
				        dockingQueryResultEntity.setRequestSerail(requestSerial);
				        if(dockingProBasicInfoEntity.getIsNewpro() == 2) {
				        dockingQueryResultEntity.setAppId(zhangHao);
				        dockingQueryResultEntity.setSecretKey(miYao);
				        }
				        QueryProBasicCode(dockingQueryResultEntity);
					}
				}//b.
			}//a.
		}
	}
	
	

	public void SendProContractorInfo()  {// 3-参建单位信息- throws Exception
		try {
			JSONObject res = null;
			ProjectContractorEntity projectContractorEntity = new ProjectContractorEntity();
			ProjectContractorReq proContractorReq = new ProjectContractorReq();
			ProjectBasicInfoReq proBasicInfoReq = new ProjectBasicInfoReq();
			Integer Contract =-1;
			Integer proCnt =-1;
			String zhangHao;
			String miYao;
			List<DockingProContractorEntity> list = dataDockingMapper.getProSubContractorInfo();
			if (list.size() != 0) {
				for (DockingProContractorEntity dockingProContractorEntity : list) {//a.
					Contract =-1;
					proCnt =-1;
					zhangHao=dockingProContractorEntity.getZhangHao();
					miYao=dockingProContractorEntity.getMiYao();
					if(zhangHao !="" && zhangHao != null && miYao !="" && miYao != null) {//c.
						if (dockingProContractorEntity.getSendFun() == 0) {//新增参建单位
							//项目是否已上传 					
							proCnt=ProjectCnt(dockingProContractorEntity.getDanw(),dockingProContractorEntity.getProjectCode(),zhangHao,miYao);
						}
						
						//参建单位是否已存在
						Contract=ContractorCnt(dockingProContractorEntity.getCorpCode(),dockingProContractorEntity.getProjectCode(),zhangHao,miYao);
						System.out.println("proCnt:"+proCnt);
						System.out.println("Contract:"+Contract);
						if((dockingProContractorEntity.getSendFun() == 0 && proCnt >0 && Contract == 0)||
								(dockingProContractorEntity.getSendFun() == 1 && Contract > 0)) {//b. 新增判断项目已存在且参建单位无，修改判断参建单位已存在
							
							BeanUtils.copyProperties(dockingProContractorEntity, projectContractorEntity);
							projectContractorEntity.setAppId(zhangHao);
							projectContractorEntity.setSecretKey(miYao);
							
							//发工资银行信息
							List<BankInfosEntity> list1 = dataDockingMapper.getProContractorBankInfo(dockingProContractorEntity.getCorpCode());
							if (list1.size() != 0) {
								projectContractorEntity.setBankInfos(list1);
							}
							
							if (dockingProContractorEntity.getSendFun() == 0) {//新增参建单位
		
								res = JSONObject.parseObject(JSONObject.toJSONString(projectContractorService.projectContractorAdd(projectContractorEntity)));
								
							}else {//修改参建单位信息
								
								res = JSONObject.parseObject(JSONObject.toJSONString(projectContractorService.projectContractorUpdate(projectContractorEntity)));	
							}
							
							//返回结果
							Map<String, Object> res1  = new HashMap<String, Object>();
							res1=getResult(res);
							String requestSerial = res1.get("requestSerial").toString();
							String msg =res1.get("msg").toString();
							Integer code = Integer.parseInt(res1.get("code").toString()) ;
							
							//更新日志表记录返回信息
							updateSendLog(dockingProContractorEntity.getXh(), code , msg , requestSerial );
							
							if(code == 600) {
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								//休眠
						        
//								Thread.sleep(2000);
								
								//获取异常调用结果
						        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
						        dockingQueryResultEntity.setLx(3);
						        dockingQueryResultEntity.setRequestSerail(requestSerial);
						        dockingQueryResultEntity.setAppId(zhangHao);
						        dockingQueryResultEntity.setSecretKey(miYao);
						        QueryProBasicCode(dockingQueryResultEntity);
							}
						}else{//b.
							if(dockingProContractorEntity.getSendFun() == 0  && Contract >0) {//新增且参建单位已存在，修改日志状态
								updateSendLog(dockingProContractorEntity.getXh(), 600 , "单位已存在，无需再上传" , "subcontractor-add-allready" );
							}else if (Contract == -1 ||proCnt == -1){
								updateSendLog(dockingProContractorEntity.getXh(), 601 , "上传失败，参建单位数量："+Contract+",项目数量："+proCnt , "" );//项目或参建单位不存在
							}
						}
					}//c.
				}//a.
			}
			
        } catch (Exception e) {
        	System.err.println(e.getMessage());
            e.printStackTrace();
        }
	}
	
	
	
	public void SendTeamInfo()  {// 5-班组信息     0企业1项目2人员3参建单位4培训5班组 throws Exception
		try {
			JSONObject res = null;
			ProjectTeamAddEntity projectTeamAddEntity = new ProjectTeamAddEntity();
			ProjectTeamUpdateEntity projectTeamUpdateEntity = new ProjectTeamUpdateEntity();
			Integer proCnt =-1;
			Integer Contract=-1;
			Integer TeamCnt=-1;
			String cxCorpCode;
			String zhangHao;
			String miYao;
			
			List<DockingTeamEntity> list = dataDockingMapper.getTeamInfo();
			if (list.size() != 0) {
				for (DockingTeamEntity dockingTeamEntity : list) {//b.
					proCnt =-1;
					Contract=-1;
					TeamCnt=-1;
					
					List<DockingGetCorpKeyIntity> listc=dataDockingMapper.getCorpCodebyPro(dockingTeamEntity.getProjectCode());
					zhangHao=listc.get(0).getAppId();
					miYao=listc.get(0).getSecretKey();
					if(zhangHao!="" && zhangHao != null & miYao !="" && miYao != null) {//c.
						if (dockingTeamEntity.getSendFun() == 0) {//新增班组信息
							//判断项目是否已上传
							cxCorpCode=listc.get(0).getCorpCode();
							proCnt=ProjectCnt(cxCorpCode,dockingTeamEntity.getProjectCode(),zhangHao,miYao);
							//判断参建单位是否已上传
							Contract=ContractorCnt(dockingTeamEntity.getCorpCode(),dockingTeamEntity.getProjectCode(),zhangHao,miYao);
						}
						
						//此项目下此班组是否已存在
						if (dockingTeamEntity.getSendFun() == 0) {//新增班组信息
							TeamCnt=TeamCnt(dockingTeamEntity.getCorpCode(),dockingTeamEntity.getProjectCode(),dockingTeamEntity.getTeamName(),zhangHao,miYao);
						}else {
							TeamCnt=TeamCntbyNo(dockingTeamEntity.getCorpCode(),dockingTeamEntity.getProjectCode(),dockingTeamEntity.getTeamSysNo(),zhangHao,miYao);
						}
						
						if (dockingTeamEntity.getSendFun() == 0 && proCnt>0 && Contract>0 && TeamCnt == 0||
								dockingTeamEntity.getSendFun() == 1 &&  TeamCnt > 0) {//a.
		
							if (dockingTeamEntity.getSendFun() == 0) {//新增班组信息
		
								BeanUtils.copyProperties(dockingTeamEntity, projectTeamAddEntity);
								projectTeamAddEntity.setAppId(zhangHao);
								projectTeamAddEntity.setSecretKey(miYao);
								
								res = JSONObject.parseObject(JSONObject.toJSONString(projectTeamService.teamAdd(projectTeamAddEntity)));
								
							}else {//修改班组信息
								
								BeanUtils.copyProperties(dockingTeamEntity, projectTeamUpdateEntity);
								projectTeamUpdateEntity.setAppId(zhangHao);
								projectTeamUpdateEntity.setSecretKey(miYao);
								
								res = JSONObject.parseObject(JSONObject.toJSONString(projectTeamService.teamUpdate(projectTeamUpdateEntity)));	
							}
							
							//返回结果
							Map<String, Object> res1  = new HashMap<String, Object>();
							res1=getResult(res);
							String requestSerial = res1.get("requestSerial").toString();
							String msg =res1.get("msg").toString();
							Integer code = Integer.parseInt(res1.get("code").toString()) ;
							
							//更新日志表记录返回信息
							updateSendLog(dockingTeamEntity.getXh(), code , msg , requestSerial );
							
							if(code == 600) {
		/*						if (dockingTeamEntity.getSendFun() == 0) {//新增班组信息
									DockingInsertSuccessLogEntity  insertLogEntity = new DockingInsertSuccessLogEntity();
									insertLogEntity.setParentId(dockingTeamEntity.getBh().toString());
									insertLogEntity.setParentXh(dockingTeamEntity.getXh());
									insertLogEntity.setRegSerial(dockingTeamEntity.getRegSerial());
									insertLogEntity.setSendLx(5);
									
									InsertSuccessLog(insertLogEntity);
								}*/
								
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								//休眠
//						        Thread.sleep(2000);
	
								//获取异步调用结果
						        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
						        dockingQueryResultEntity.setLx(5);
						        dockingQueryResultEntity.setRequestFun(dockingTeamEntity.getSendFun());
						        dockingQueryResultEntity.setRequestSerail(requestSerial);
						        dockingQueryResultEntity.setAppId(zhangHao);
						        dockingQueryResultEntity.setSecretKey(miYao);
						        QueryProBasicCode(dockingQueryResultEntity);
							}
						}//a.	
					}//c.
				}//b.
			}
		
        } catch (Exception e) {
        	System.err.println(e.getMessage());
            e.printStackTrace();
        }
	}
	
	
	
	
	public void SendWorkerInfo()  {// 2-工人信息  0企业1项目2人员3参建单位4培训5班组 throws Exception
		try {
			JSONObject res = null;
			Integer arri=0;
			Integer xi=0;
			Integer proCnt=-1;
			Integer TeamCnt=-1;
			String regSerial="";
			String cxCorpCode;
			String zhangHao;
			String miYao;
			WorkerAddEntity workerAddEntity = new WorkerAddEntity();
			WorkerUpdateEntity workerUpdateEntity = new WorkerUpdateEntity();
			
			//新增人员处理
					List<DockingWorkerProTeamEntity> proTeamList=dataDockingMapper.getWorkerProCorpInfo();
					if(proTeamList.size()  != 0) {
						for (DockingWorkerProTeamEntity dockingWorkerProTeamEntity : proTeamList) {//a.
							proCnt=-1;
							TeamCnt=-1;
							cxCorpCode="";
							//判断项目、班组是否已上传
							List<DockingGetCorpKeyIntity> listc=dataDockingMapper.getCorpCodebyPro(dockingWorkerProTeamEntity.getProjectCode());
							zhangHao=listc.get(0).getAppId();
							miYao=listc.get(0).getSecretKey();
							cxCorpCode=listc.get(0).getCorpCode();
							if(zhangHao!="" && zhangHao!=null && miYao!="" && miYao!=null) {//d.
								if(cxCorpCode!="") {
									proCnt=ProjectCnt(cxCorpCode,dockingWorkerProTeamEntity.getProjectCode(),zhangHao,miYao);
								}
								TeamCnt=TeamCntbyNo(dockingWorkerProTeamEntity.getCorpCode() ,dockingWorkerProTeamEntity.getProjectCode(),dockingWorkerProTeamEntity.getTeamSysNo(),zhangHao,miYao);
								
								if(proCnt>0 && TeamCnt>0) {//b.
									
									BeanUtils.copyProperties(dockingWorkerProTeamEntity, workerAddEntity);
									
									//此班组待上传总人数
									Integer rsCnt=dataDockingMapper.getTeamWorkerCnt(
											dockingWorkerProTeamEntity.getTeamName(),
											dockingWorkerProTeamEntity.getRegSerial());
									
									for (int i = 0; i <= rsCnt/5 ; i++) {//b.
										Thread.sleep(2000);
										
										//3.找到上传人员详细信息，比对人员是否已存在 ，在语句中判断
										List<WorkerListEntity> list1 = dataDockingMapper.getWorkerListInfo(
												dockingWorkerProTeamEntity.getTeamName(),
												dockingWorkerProTeamEntity.getRegSerial());
										if (list1.size() != 0) {
											workerAddEntity.setWorkerList(list1);
											workerAddEntity.setAppId(zhangHao);
											workerAddEntity.setSecretKey(miYao);
		
											res = JSONObject.parseObject(JSONObject.toJSONString(workerService.workerAdd(workerAddEntity)));
											
			//								返回结果					
											Map<String, Object> res1 = new HashMap<String, Object>();
//											System.out.println("ry_msg--" + res.getString("msg"));
											String requestSerial = "";
											Integer code = res.getInteger("code");
											String msg = res.getString("msg");
											String enUserId="";
											String decUserId="";
											if(code == 600) {
		
												//更新日志表记录返回信息
												for (int j = 0; j < list1.size(); j++) {
													enUserId=list1.get(j).getIdCardNumber().toString();
											        decUserId=AES7Coder.decrypt(enUserId, miYao);
			
													JSONObject data=JSONObject.parseObject( res.getString("data"));
													requestSerial= data.getString("requestSerialCode");
													
	//												updateSendLogbyUserid(decUserId,workerAddEntity.getTeamName(),code,msg,requestSerial,0,"",2);
//													updateSendLogbySerial(decUserId,workerAddEntity.getTeamName(),code,msg,requestSerial,0,"",2,list1.get(j).getUserSerial());
													updateSendLogbySerial(decUserId,workerAddEntity.getTeamName(),code,msg,requestSerial,0,"",2,list1.get(j).getUserSerial(),dockingWorkerProTeamEntity.getRegSerial());
													
//													System.out.println("update_ry");
													//插入成功日志表，获取结果后修改表中结果
													DockingInsertSuccessLogEntity  insertLogEntity = new DockingInsertSuccessLogEntity();
													insertLogEntity.setIdCardNumber(decUserId);
													insertLogEntity.setRequestSerial(requestSerial);
													insertLogEntity.setRegSerial(dockingWorkerProTeamEntity.getRegSerial());
													insertLogEntity.setSendLx(2);
													insertLogEntity.setUserSerial(list1.get(j).getUserSerial());
//													System.out.println("InsertSuccessLogbySerial_ry");
	//												InsertSuccessLogbyUserID(insertLogEntity);
													InsertSuccessLogbySerial(insertLogEntity);
//													System.out.println("InsertSuccessLogbySerial_ry_log");
													
												}
			
											}else {
											if(msg.indexOf("[workerList]")!=-1){
											
												String[] strarray=msg.split(";"); 
												for (int i1 = 0; i1 < strarray.length; i1++) {
													System.out.println("yyyy---"+strarray[i1]); 
													xi=0;
			
													Pattern pattern = Pattern.compile("\\[(.*?)\\]");
												    Matcher matcher = pattern.matcher(strarray[i1]);
											        while(matcher.find()){
											            System.out.println("www---"+matcher.group(1));
											            if(xi==1) {arri=Integer.parseInt(matcher.group(1));}
												        if(xi==2) {msg=strarray[i1].toString();}
												        xi=xi+1;
											        }
			
											        enUserId=workerAddEntity.getWorkerList().get(arri).getIdCardNumber().toString();
											        decUserId=AES7Coder.decrypt(enUserId, miYao);
													//更新日志表记录返回信息
											        updateSendLogbyUserid(decUserId,workerAddEntity.getTeamName(),code,msg,requestSerial,0,"",2);
												}		
											}else{
												
										        enUserId=workerAddEntity.getWorkerList().get(arri).getIdCardNumber().toString();
										        decUserId=AES7Coder.decrypt(enUserId, miYao);
												//更新日志表记录返回信息
										        updateSendLogbyUserid(decUserId,workerAddEntity.getTeamName(),code,msg,requestSerial,0,"",2);
											}
										}
											
			
											 
										if(code == 600) {
											SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
											//休眠
	//								        System.out.println("s1-------"+df.format(new Date()));// new Date()为获取当前系统时间
//								        	Thread.sleep(2000);
	//								        System.out.println("s2-------"+df.format(new Date()));// new Date()为获取当前系统时间
	
											//获取异常调用结果
									        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
									        dockingQueryResultEntity.setLx(2);
									        dockingQueryResultEntity.setRequestFun(0);
									        dockingQueryResultEntity.setRequestSerail(requestSerial);
									        dockingQueryResultEntity.setWorkerList(list1);
									        dockingQueryResultEntity.setAppId(zhangHao);
									        dockingQueryResultEntity.setSecretKey(miYao);
									        QueryProBasicCode(dockingQueryResultEntity);
										}
									}
								}//c.
							}//b.
						}//d.
					}//a.
				}
	
			
			
			//修改人员处理
			Integer userCnt=-1;
			proCnt=-1;
			List<DockingWorkerUpdateEntity> list2 = dataDockingMapper.getUpdateWorkerInfo();
			if (list2.size()  != 0) {
				for (DockingWorkerUpdateEntity dockingWorkerUpdateEntity : list2) {
					
						userCnt=-1;
						proCnt=-1;
						//判断项目、人员是否已上传
						List<DockingGetCorpKeyIntity> listd=dataDockingMapper.getCorpCodebyPro(dockingWorkerUpdateEntity.getProjectCode());
						cxCorpCode=listd.get(0).getCorpCode();
						zhangHao=listd.get(0).getAppId();
						miYao=listd.get(0).getSecretKey();
						if(zhangHao!="" && zhangHao!=null && miYao!="" && miYao!=null) {
							if(cxCorpCode !="") {
							proCnt=ProjectCnt(cxCorpCode,dockingWorkerUpdateEntity.getProjectCode(),zhangHao,miYao);
							userCnt=PersionCnt(dockingWorkerUpdateEntity.getCorpCode(),dockingWorkerUpdateEntity.getProjectCode(),
									dockingWorkerUpdateEntity.getTeamSysNo(),dockingWorkerUpdateEntity.getIdCardType(),
									dockingWorkerUpdateEntity.getIdCardNumber(),zhangHao,miYao);
							}
							if(proCnt>0 && userCnt>0) {
								
								BeanUtils.copyProperties(dockingWorkerUpdateEntity, workerUpdateEntity);
								workerUpdateEntity.setAppId(zhangHao);
								workerUpdateEntity.setSecretKey(miYao);
									
								res = JSONObject.parseObject(JSONObject.toJSONString(workerService.workerUpdate(workerUpdateEntity)));	
								
//								System.out.println("jsonResult--" + res);
								
			//					返回结果
								Map<String, Object> res1  = new HashMap<String, Object>();
								res1=getResult(res);
								String requestSerial = res1.get("requestSerial").toString();
								String msg =res1.get("msg").toString();
								Integer code = Integer.parseInt(res1.get("code").toString()) ;
								
			//					更新日志表记录返回信息
								updateSendLog(dockingWorkerUpdateEntity.getXh(), code , msg , requestSerial );
								
								if(code == 600) {
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
									//休眠
//						        	System.out.println("s1-------"+df.format(new Date()));// new Date()为获取当前系统时间
//						        	Thread.sleep(2000);
//						        	System.out.println("s2-------"+df.format(new Date()));// new Date()为获取当前系统时间
									
									//获取异常调用结果
							        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
							        dockingQueryResultEntity.setLx(2);
							        dockingQueryResultEntity.setRequestFun(dockingWorkerUpdateEntity.getSendFun());
							        dockingQueryResultEntity.setRequestSerail(requestSerial);
							        dockingQueryResultEntity.setAppId(zhangHao);
							        dockingQueryResultEntity.setSecretKey(miYao);
							        QueryProBasicCode(dockingQueryResultEntity);
							}
						}		
					}
				}
			}
			
        } catch (Exception e) {
        	System.err.println(e.getMessage());
        	e.printStackTrace();
        }

	}
	
	
	
	//**************************************************************************
	public void SendWorkerEntryInfo()  {// 6-工人进场  0企业1项目2人员3参建单位4培训5班组6工人进退场7工人合同8工人考勤
		try {
			JSONObject res = null;
			Integer arri=0;
			Integer xi=0;
			Integer proCnt=-1;
			String cxCorpCode;
			String zhangHao;
			String miYao;
			WorkerEntryExitAddEntity workerEntryExitAddEntity = new WorkerEntryExitAddEntity(); 
			
			//1.根据班组及工地信息循环上传进出场人员
			List<DockingWorkerProTeamEntity> teamList= dataDockingMapper.getProjectTeamList();
			if (teamList .size() != 0) {
				for (DockingWorkerProTeamEntity dockingWorkerProTeamEntity : teamList) {//a.
					proCnt=-1;
					cxCorpCode="";
					
					//判断项目、人员是否已上传
					List<DockingGetCorpKeyIntity> listc=dataDockingMapper.getCorpCodebyPro(dockingWorkerProTeamEntity.getProjectCode());
					cxCorpCode=listc.get(0).getCorpCode();
					zhangHao=listc.get(0).getAppId();
					miYao=listc.get(0).getSecretKey();
					if(cxCorpCode != "") {
					proCnt=ProjectCnt(cxCorpCode,dockingWorkerProTeamEntity.getProjectCode(),zhangHao,miYao);
					}
					if(proCnt>0) {//b.
					
						BeanUtils.copyProperties(dockingWorkerProTeamEntity, workerEntryExitAddEntity);
						//此班组待上传总人数
						Integer rsCnt=dataDockingMapper.getTeamWorkerCnt_Entry(
								dockingWorkerProTeamEntity.getTeamName(),
								dockingWorkerProTeamEntity.getRegSerial());
						
//						System.out.println(rsCnt/50);
						for (int i = 0; i <= rsCnt/50 ; i++) {
							
							//2.根据班组、项目查找待上传的进退场人员
							List<WorkerListEntryEntity> list1=dataDockingMapper.getWorkerEntryList(
									dockingWorkerProTeamEntity.getTeamName(),
									dockingWorkerProTeamEntity.getRegSerial());
							if (list1.size() != 0) {
								workerEntryExitAddEntity.setWorkerList(list1);
								workerEntryExitAddEntity.setAppId(zhangHao);
								workerEntryExitAddEntity.setSecretKey(miYao);
								
								res = JSONObject.parseObject(JSONObject.toJSONString(workerEntryExitService.workerEntryExitAdd(workerEntryExitAddEntity)));
								
		//						返回结果					
								Map<String, Object> res1 = new HashMap<String, Object>();
//								System.out.println("msg--" + res.getString("msg"));
								String requestSerial = "";
								Integer code = res.getInteger("code");
								String msg = res.getString("msg");
								String enUserId="";
								String decUserId="";
								if(code == 600) {
									//更新日志表记录返回信息
									for (int j = 0; j < list1.size(); j++) {
										enUserId=list1.get(j).getIdCardNumber().toString();
								        decUserId=AES7Coder.decrypt(enUserId, miYao);
		
										JSONObject data=JSONObject.parseObject( res.getString("data"));
										requestSerial= data.getString("requestSerialCode");
										
	//									updateSendLogbyUserid(decUserId,"",code,msg,requestSerial,list1.get(j).getType(),list1.get(j).getDate(),6);
										updateSendLogbySerial(decUserId,"",code,msg,requestSerial,list1.get(j).getType(),list1.get(j).getDate(),6,list1.get(j).getUserSerial(),dockingWorkerProTeamEntity.getRegSerial());
										
										//插入进场人员信息，获取结果后修改表中结果
										DockingInsertSuccessLogEntity  insertLogEntity = new DockingInsertSuccessLogEntity();
										insertLogEntity.setIdCardNumber(decUserId);
										insertLogEntity.setRequestSerial(requestSerial);
										insertLogEntity.setRegSerial(dockingWorkerProTeamEntity.getRegSerial());
										insertLogEntity.setSendLx(6);
										insertLogEntity.setUserSerial(list1.get(j).getUserSerial());
							
	//									InsertSuccessLogbyUserID(insertLogEntity);
										InsertSuccessLogbySerial(insertLogEntity);
										
									}
		
								}else {
								if(msg.indexOf("[workerList]")!=-1){
								
									String[] strarray=msg.split(";"); 
									for (int i1 = 0; i1 < strarray.length; i1++) {
										System.out.println("yyyy---"+strarray[i1]); 
										xi=0;
		
										Pattern pattern = Pattern.compile("\\[(.*?)\\]");
								        Matcher matcher = pattern.matcher(strarray[i1]);
								        while(matcher.find()){
								            System.out.println("www---"+matcher.group(1));
								            if(xi==1) {arri=Integer.parseInt(matcher.group(1));}
									        if(xi==2) {msg=strarray[i1].toString();}
									        xi=xi+1;
								        }
		
								        enUserId=workerEntryExitAddEntity.getWorkerList().get(arri).getIdCardNumber().toString();
								        decUserId=AES7Coder.decrypt(enUserId, miYao);
										//更新日志表记录返回信息
								        updateSendLogbyUserid(decUserId,"",code,msg,requestSerial,
								        			workerEntryExitAddEntity.getWorkerList().get(arri).getType(),
								        			workerEntryExitAddEntity.getWorkerList().get(arri).getDate(),6);//进出场状态，时间
									}		
								}else {
									
							        enUserId=workerEntryExitAddEntity.getWorkerList().get(arri).getIdCardNumber().toString();
							        decUserId=AES7Coder.decrypt(enUserId, miYao);
									//更新日志表记录返回信息
							        updateSendLogbyUserid(decUserId,"",code,msg,requestSerial,
							        		workerEntryExitAddEntity.getWorkerList().get(arri).getType(),
						        			workerEntryExitAddEntity.getWorkerList().get(arri).getDate(),6);//进出场状态，时间
								}
							}
								
							 
							if(code == 600) {
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								//休眠
//						        Thread.sleep(2000);
								
								//获取异常调用结果
						        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
						        dockingQueryResultEntity.setLx(6);
						        dockingQueryResultEntity.setRequestFun(0);
						        dockingQueryResultEntity.setRequestSerail(requestSerial);
						        dockingQueryResultEntity.setWorkerEntryList(list1);
						        dockingQueryResultEntity.setAppId(zhangHao);
						        dockingQueryResultEntity.setSecretKey(miYao);
						        dockingQueryResultEntity.setEnterExitStatus(1);
						        QueryProBasicCode(dockingQueryResultEntity);
							}
						}
					}
				}//b.		
			}//a.
		}	
	} catch (Exception e) {
		System.err.println(e.getMessage());
    	e.printStackTrace();
    }	
}

	
	
	//**************************************************************************
	public void SendWorkerExitInfo()  {// 6-工人退场  0企业1项目2人员3参建单位4培训5班组6工人进退场7工人合同8工人考勤
		try {
			JSONObject res = null;
			Integer arri=0;
			Integer xi=0;
			Integer proCnt=-1;
			String cxCorpCode;
			String zhangHao;
			String miYao;
			WorkerEntryExitAddEntity workerEntryExitAddEntity = new WorkerEntryExitAddEntity(); 
			
			//1.根据班组及工地信息循环上传进出场人员
			List<DockingWorkerProTeamEntity> teamList= dataDockingMapper.getProjectTeamList();
			if (teamList .size() != 0) {
				for (DockingWorkerProTeamEntity dockingWorkerProTeamEntity : teamList) {//a.
					proCnt=-1;
					cxCorpCode="";
					
					//判断项目、人员是否已上传
					List<DockingGetCorpKeyIntity> listc=dataDockingMapper.getCorpCodebyPro(dockingWorkerProTeamEntity.getProjectCode());
					cxCorpCode=listc.get(0).getCorpCode();
					zhangHao=listc.get(0).getAppId();
					miYao=listc.get(0).getSecretKey();
					if(cxCorpCode != "") {
					proCnt=ProjectCnt(cxCorpCode,dockingWorkerProTeamEntity.getProjectCode(),zhangHao,miYao);
					}
					if(proCnt>0) {//b.
					
						BeanUtils.copyProperties(dockingWorkerProTeamEntity, workerEntryExitAddEntity);
						//此班组待上传总人数
						Integer rsCnt=dataDockingMapper.getTeamWorkerCnt_Exit(
								dockingWorkerProTeamEntity.getTeamName(),
								dockingWorkerProTeamEntity.getRegSerial());
						
//						System.out.println(rsCnt/50);
						for (int i = 0; i <= rsCnt/50 ; i++) {
							
							//2.根据班组、项目查找待上传的进退场人员
							List<WorkerListEntryEntity> list1=dataDockingMapper.getWorkerExitList(
									dockingWorkerProTeamEntity.getTeamName(),
									dockingWorkerProTeamEntity.getRegSerial());
							if (list1.size() != 0) {
								workerEntryExitAddEntity.setWorkerList(list1);
								workerEntryExitAddEntity.setAppId(zhangHao);
								workerEntryExitAddEntity.setSecretKey(miYao);
								
								res = JSONObject.parseObject(JSONObject.toJSONString(workerEntryExitService.workerEntryExitAdd(workerEntryExitAddEntity)));
								
		//						返回结果					
								Map<String, Object> res1 = new HashMap<String, Object>();
//								System.out.println("msg--" + res.getString("msg"));
								String requestSerial = "";
								Integer code = res.getInteger("code");
								String msg = res.getString("msg");
								String enUserId="";
								String decUserId="";
								if(code == 600) {
									//更新日志表记录返回信息
									for (int j = 0; j < list1.size(); j++) {
										enUserId=list1.get(j).getIdCardNumber().toString();
								        decUserId=AES7Coder.decrypt(enUserId, miYao);
		
										JSONObject data=JSONObject.parseObject( res.getString("data"));
										requestSerial= data.getString("requestSerialCode");
										
//										updateSendLogbyUserid(decUserId,"",code,msg,requestSerial,list1.get(j).getType(),list1.get(j).getDate(),6);
										updateSendLogbySerial(decUserId,"",code,msg,requestSerial,list1.get(j).getType(),list1.get(j).getDate(),6,list1.get(j).getUserSerial(),dockingWorkerProTeamEntity.getRegSerial());
										
		/*								//插入进场人员信息，获取结果后修改表中结果
										DockingInsertSuccessLogEntity  insertLogEntity = new DockingInsertSuccessLogEntity();
										insertLogEntity.setIdCardNumber(decUserId);
										insertLogEntity.setRequestSerial(requestSerial);
										insertLogEntity.setRegSerial(dockingWorkerProTeamEntity.getRegSerial());
										insertLogEntity.setSendLx(6);
										insertLogEntity.setUserSerial(list1.get(j).getUserSerial());
							
										InsertSuccessLogbySerial(insertLogEntity);*/
										
									}
		
								}else {
								if(msg.indexOf("[workerList]")!=-1){
								
									String[] strarray=msg.split(";"); 
									for (int i1 = 0; i1 < strarray.length; i1++) {
										System.out.println("yyyy---"+strarray[i1]); 
										xi=0;
		
										Pattern pattern = Pattern.compile("\\[(.*?)\\]");
								        Matcher matcher = pattern.matcher(strarray[i1]);
								        while(matcher.find()){
								            System.out.println("www---"+matcher.group(1));
								            if(xi==1) {arri=Integer.parseInt(matcher.group(1));}
									        if(xi==2) {msg=strarray[i1].toString();}
									        xi=xi+1;
								        }
		
								        enUserId=workerEntryExitAddEntity.getWorkerList().get(arri).getIdCardNumber().toString();
								        decUserId=AES7Coder.decrypt(enUserId, miYao);
										//更新日志表记录返回信息
								        updateSendLogbyUserid(decUserId,"",code,msg,requestSerial,
								        			workerEntryExitAddEntity.getWorkerList().get(arri).getType(),
								        			workerEntryExitAddEntity.getWorkerList().get(arri).getDate(),6);//进出场状态，时间
									}		
								}else {
									
							        enUserId=workerEntryExitAddEntity.getWorkerList().get(arri).getIdCardNumber().toString();
							        decUserId=AES7Coder.decrypt(enUserId, miYao);
									//更新日志表记录返回信息
							        updateSendLogbyUserid(decUserId,"",code,msg,requestSerial,
							        		workerEntryExitAddEntity.getWorkerList().get(arri).getType(),
						        			workerEntryExitAddEntity.getWorkerList().get(arri).getDate(),6);//进出场状态，时间
								}
							}
								
							 
							if(code == 600) {
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								//休眠
//						       Thread.sleep(2000);

								//获取异常调用结果
						        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
						        dockingQueryResultEntity.setLx(6);
						        dockingQueryResultEntity.setRequestFun(0);
						        dockingQueryResultEntity.setRequestSerail(requestSerial);
						        dockingQueryResultEntity.setWorkerEntryList(list1);
						        dockingQueryResultEntity.setAppId(zhangHao);
						        dockingQueryResultEntity.setSecretKey(miYao);
						        dockingQueryResultEntity.setEnterExitStatus(0);
						        QueryProBasicCode(dockingQueryResultEntity);
							}
						}
					}
				}//b.		
			}//a.
		}
			
	} catch (Exception e) {
		System.err.println(e.getMessage());
    	e.printStackTrace();
    }		
}
	
	
	
	
	
	//**************************************************************************
	public void SendWorkerContractInfo()  {// 7-工人合同  0企业1项目2人员3参建单位4培训5班组6工人进退场7工人合同8工人考勤
		try {
			JSONObject res = null;
			Integer arri=0;
			Integer xi=0;
			Integer proCnt=-1;
			String cxCorpCode;
			String zhangHao;
			String miYao;
			Integer htCnt=-1;
			WorkerContractAddEntity workerContractAddEntity = new WorkerContractAddEntity(); 
			
			//1.获取项目
			List<DockingProContractListEntity> teamList= dataDockingMapper.getProContractList();
			if (teamList.size() != 0) {
				for (DockingProContractListEntity dockingProContractListEntity : teamList) {//a.
					
					//判断项目、人员是否已上传
					List<DockingGetCorpKeyIntity> listc=dataDockingMapper.getCorpCodebyPro(dockingProContractListEntity.getProjectCode());
					cxCorpCode=listc.get(0).getCorpCode();
					zhangHao=listc.get(0).getAppId();
					miYao=listc.get(0).getSecretKey();
					if(cxCorpCode != "") {
					proCnt=ProjectCnt(cxCorpCode,dockingProContractListEntity.getProjectCode(),zhangHao,miYao);
					}
					if(proCnt>0) {
					
						workerContractAddEntity.setProjectCode(dockingProContractListEntity.getProjectCode());
		
						//此项目待上传总人数
						Integer rsCnt=dataDockingMapper.getWorkerContractCnt(dockingProContractListEntity.getProCode());
						
						for (int i = 0; i <= rsCnt/5 ; i++) {
							
							//2.根据项目查找待上传的人员合同
							List<ContractListEntity> list1=dataDockingMapper.getWorkerContractList(dockingProContractListEntity.getProCode());
							if (list1.size() != 0) {
								
								List<ContractListEntity> list_ok = new ArrayList<ContractListEntity>();
								//查询人员合同照片
								for (int j = 0; j < list1.size(); j++) {
									
									if(list1.get(j).getIsImportContract() == 0) {
									//无合同的传输
								/*	htCnt=dataDockingMapper.getAttCnt(list1.get(j).getUserSerial(),dockingProContractListEntity.getRegSerial());
									if(htCnt <= 0) {*/
										list_ok.add(list1.get(j));
									}else {
										//要上传合同附件，但附件暂未生成的先不传输
										htCnt=-1;
										htCnt=dataDockingMapper.getAttachmentCnt(list1.get(j).getUserSerial(),dockingProContractListEntity.getRegSerial());
										if(htCnt>0) {
											List<AttachmentsEntity> list2=dataDockingMapper.getAttachment(list1.get(j).getUserSerial(),PDFUrl,dockingProContractListEntity.getRegSerial());
											for (int jw = 0; jw < list2.size(); jw++) {
												//System.out.println(URLEncoder.encode(Coder.encryptBASE64("contractAttachment\\500080\\20001\\20001805.pdf".getBytes())));
												list2.get(jw).setData(PDFUrl +"/send/getPdfFile/"+URLEncoder.encode(Coder.encryptBASE64(list2.get(jw).getData().getBytes())));
											}
											list1.get(j).setAttachments(list2);
											list_ok.add(list1.get(j));
										}
									}
								}
								workerContractAddEntity.setContractList(list_ok);
								workerContractAddEntity.setAppId(zhangHao);
								workerContractAddEntity.setSecretKey(miYao);
								
								res = JSONObject.parseObject(JSONObject.toJSONString(workerContractService.workerContractAdd(workerContractAddEntity)));
							
		//						返回结果					
								Map<String, Object> res1 = new HashMap<String, Object>();
								System.out.println("msg--" + res.getString("msg"));
								String requestSerial = "";
								Integer code = res.getInteger("code");
								String msg = res.getString("msg");
								String enUserId="";
								String decUserId="";
								if(code == 600) {
									//更新日志表记录返回信息
									for (int j = 0; j < list_ok.size(); j++) {
										enUserId=list_ok.get(j).getIdCardNumber().toString();
								        decUserId=AES7Coder.decrypt(enUserId, miYao);
		
										JSONObject data=JSONObject.parseObject( res.getString("data"));
										requestSerial= data.getString("requestSerialCode");
										
	//									dataDockingMapper.updateWorkerContractState(decUserId,dockingProContractListEntity.getProCode().toString());
										dataDockingMapper.updateWorkerContractState(list_ok.get(j).getXh(),dockingProContractListEntity.getProCode().toString());
										//插入请求日志表
	//									insertSendContractLog(7,decUserId,dockingProContractListEntity.getProCode(),code,msg,requestSerial,null);
										insertSendContractLog(7,decUserId,dockingProContractListEntity.getProCode(),code,msg,requestSerial,null,list_ok.get(j).getUserSerial());
		
										//更新合同照片表状态
										dataDockingMapper.updateWorkerContractPhotoState(list_ok.get(j).getUserSerial(), dockingProContractListEntity.getProCode().toString());
										
										//更新合同照片附件表状态
										dataDockingMapper.updateWorkerContractPhotoState1(list_ok.get(j).getUserSerial(), dockingProContractListEntity.getProCode().toString());
										
									}
									
								}else {
								if(msg.indexOf("[workerList]")!=-1){
									String[] strarray=msg.split(";"); 
									for (int i1 = 0; i1 < strarray.length; i1++) {
										xi=0;
										Pattern pattern = Pattern.compile("\\[(.*?)\\]");
								        Matcher matcher = pattern.matcher(strarray[i1]);
								        while(matcher.find()){
								            System.out.println("www---"+matcher.group(1));
								            if(xi==1) {arri=Integer.parseInt(matcher.group(1));}
									        if(xi==2) {msg=strarray[i1].toString();}
									        xi=xi+1;
								        }
		
								        enUserId=workerContractAddEntity.getContractList().get(arri).getIdCardNumber();
								        decUserId=AES7Coder.decrypt(enUserId, miYao);
								        dataDockingMapper.updateWorkerContractStateByID(decUserId ,dockingProContractListEntity.getProCode().toString());								        
										//插入请求日志表
								        insertSendContractLogByID(7,decUserId,dockingProContractListEntity.getProCode(),code,msg,requestSerial,null);
								        
									}		
								}else {
									
									for (int j1 = 0; j1 < list1.size(); j1++) {
								        enUserId=workerContractAddEntity.getContractList().get(j1).getIdCardNumber();
								        decUserId=AES7Coder.decrypt(enUserId, miYao);
		
								        dataDockingMapper.updateWorkerContractState(list1.get(j1).getXh(),dockingProContractListEntity.getProCode().toString());
						        		//插入请求日志表
								        insertSendContractLog(7,decUserId,dockingProContractListEntity.getProCode(),code,msg,requestSerial,null,list1.get(j1).getUserSerial());
		
									}
								}
							}
								
		
							if(code == 600) {
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
								//休眠
//						        Thread.sleep(2000);

								//获取异常调用结果
						        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
						        dockingQueryResultEntity.setLx(7);
						        dockingQueryResultEntity.setRequestFun(0);
						        dockingQueryResultEntity.setRequestSerail(requestSerial);
						        dockingQueryResultEntity.setAppId(zhangHao);
						        dockingQueryResultEntity.setSecretKey(miYao);
						        QueryProBasicCode(dockingQueryResultEntity);
							}
						}
					}
				}//b.
			}//a.
		}
	} catch (Exception e) {
		System.err.println(e.getMessage());
    	e.printStackTrace();
    }	
}
	
	
	
	
	
	//**************************************************************************
	public void SendWorkerAttendanceInfo()  {// 8-工人考勤  0企业1项目2人员3参建单位4培训5班组6工人进退场7工人合同8工人考勤
		try {
			JSONObject res = null;
			Integer arri=0;
			Integer xi=0;
			Integer proCnt=-1;
			String cxCorpCode;
			String zhangHao;
			String miYao;
			WorkerAttendanceAddEntity WorkerAttendanceAddEntity = new WorkerAttendanceAddEntity(); 
			
			//1.获取项目、班组
			List<DockingWorkerAttendanceTeamEntity> teamList= dataDockingMapper.getWorkerAttendancePro();
			if (teamList.size() != 0) {
				for (DockingWorkerAttendanceTeamEntity docking : teamList) {
		
					proCnt=-1;
					List<DockingGetCorpKeyIntity> listc=dataDockingMapper.getCorpCodebyPro(docking.getProjectCode());
					cxCorpCode=listc.get(0).getCorpCode();
					zhangHao=listc.get(0).getAppId();
					miYao=listc.get(0).getSecretKey();
					if(cxCorpCode != "") {
					proCnt=ProjectCnt(cxCorpCode,docking.getProjectCode(),zhangHao,miYao);}
					if(proCnt >0) {//b.
							WorkerAttendanceAddEntity.setProjectCode(docking.getProjectCode());
							WorkerAttendanceAddEntity.setTeamSysNo(docking.getTeamSysNo());
	
							//此项目待上传总人数
							Integer rsCnt=dataDockingMapper.getWorkerJlCnt(docking.getTeamName(),docking.getRegSerial());
							
//							System.out.println(rsCnt/20);
							for (int i = 0; i <= rsCnt/20 ; i++) {//c.
								
								//2.根据项目查找待上传的人员考勤
								List<DataListEntity> list1=dataDockingMapper.getWorkerAttendanceList(docking.getTeamName(),docking.getRegSerial());
								if (list1.size() != 0) {
									List<DataListEntity> list2 = new ArrayList<DataListEntity>();
						 								 
									WorkerAttendanceAddEntity.setDataList(list1);
									WorkerAttendanceAddEntity.setAppId(zhangHao);
									WorkerAttendanceAddEntity.setSecretKey(miYao);
									
									res = JSONObject.parseObject(JSONObject.toJSONString(workerAttendanceService.workerAttendanceAdd(WorkerAttendanceAddEntity)));
									
	//								返回结果					
									Map<String, Object> res1 = new HashMap<String, Object>();
//									System.out.println("msg--" + res.getString("msg"));
									String requestSerial = "";
									Integer code = res.getInteger("code");
									String msg = res.getString("msg");
									String enUserId="";
									String decUserId="";
									if(code == 600) {
										//更新日志表记录返回信息
										for (int j = 0; j < list1.size(); j++) {
	
											JSONObject data=JSONObject.parseObject( res.getString("data"));
											requestSerial= data.getString("requestSerialCode");
											
											dataDockingMapper.updateWorkerJlBybhStatus(WorkerAttendanceAddEntity.getDataList().get(j).getBh());
											
											//插入请求日志表
											DockingAddSendContractLogEntity  addContractLog = new DockingAddSendContractLogEntity();
											addContractLog.setLx(8);
											addContractLog.setParentId(list1.get(j).getBh());
											addContractLog.setRequestJg(code);
											addContractLog.setRequestMessage(msg);
											addContractLog.setRequestSerial(requestSerial);
											addContractLog.setRegserial(Integer.parseInt(docking.getRegSerial()));
											dataDockingMapper.addSendJlLog(addContractLog);
	
										}
										
									}else {
										
									for (int j = 0; j < list1.size(); j++) {
	
										JSONObject data=JSONObject.parseObject( res.getString("data"));
										requestSerial= data.getString("requestSerialCode");
										//更新记录表状态
										dataDockingMapper.updateWorkerJlBybhStatus(WorkerAttendanceAddEntity.getDataList().get(j).getBh());
		
									}
									
									
									if(msg.indexOf("[datalist")!=-1){
										
										String[] strarray=msg.split(";"); 
										for (int i1 = 0; i1 < strarray.length; i1++) {
											System.out.println("yyyy---"+strarray[i1]); 
											xi=0;
	
											Pattern pattern = Pattern.compile("\\[(.*?)\\]");
									        Matcher matcher = pattern.matcher(strarray[i1]);
									        while(matcher.find()){
									            System.out.println("www---"+matcher.group(1));
									            if(xi==1) {arri=Integer.parseInt(matcher.group(1));}
										        if(xi==2) {msg=strarray[i1].toString();}
										        xi=xi+1;
									        }
							        								        
											//插入请求日志表
											DockingAddSendContractLogEntity  addContractLog = new DockingAddSendContractLogEntity();
											addContractLog.setLx(8);
											addContractLog.setParentId(list1.get(arri).getBh());
											addContractLog.setRequestJg(code);
											addContractLog.setRequestMessage(msg);
											addContractLog.setRequestSerial(requestSerial);
											dataDockingMapper.addSendJlLog(addContractLog);
										        
										}		
									}else {
										for (int k = 0; k < list1.size(); k++) {
						        		//插入请求日志表
										DockingAddSendContractLogEntity  addContractLog = new DockingAddSendContractLogEntity();
										addContractLog.setLx(8);
										addContractLog.setParentId(list1.get(k).getBh());
										addContractLog.setRequestJg(code);
										addContractLog.setRequestMessage(msg);
										addContractLog.setRequestSerial(requestSerial);
										dataDockingMapper.addSendJlLog(addContractLog);
								        
								        
										}
									}
								}
									
								 
								if(code == 600) {
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
									//休眠
//							        Thread.sleep(2000);
									
									//获取异常调用结果
							        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
							        dockingQueryResultEntity.setLx(8);
							        dockingQueryResultEntity.setRequestFun(0);
							        dockingQueryResultEntity.setRequestSerail(requestSerial);
							        dockingQueryResultEntity.setAppId(zhangHao);
							        dockingQueryResultEntity.setSecretKey(miYao);
							        QueryProBasicCode(dockingQueryResultEntity);
								}
							}
						}//c.
					}//b.		
				}//a.
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
	    	e.printStackTrace();
	    }	
	}

	
	
	
	//**************************************************************************
	public void SendPayrollInfo() {// 9-工资  0企业1项目2人员3参建单位4培训5班组6工人进退场7工人合同8工人考勤9工人工资
		try {
			JSONObject res = null;
			Integer arri=0;
			Integer xi=0;
			Integer proCnt=-1;
			Integer TeamCnt=-1;
			String cxCorpCode;
			String zhangHao;
			String miYao;
			WorkerPayrollAddEntity workerPayrollAddEntity = new WorkerPayrollAddEntity(); 
			
			//1.获取项目、班组 
			List<DockingPayRollTeamEntity> teamList= dataDockingMapper.getPayRollPro();
			if (teamList.size() != 0) {
				for (DockingPayRollTeamEntity docking : teamList) {//a.
					//判断项目、班组是否已存在
					proCnt=-1;
					TeamCnt=-1;
					List<DockingGetCorpKeyIntity> listc=dataDockingMapper.getCorpCodebyPro(docking.getProjectCode());
					cxCorpCode=listc.get(0).getCorpCode();
					zhangHao=listc.get(0).getAppId();
					miYao=listc.get(0).getSecretKey();
					if(cxCorpCode != "") {
					proCnt=ProjectCnt(cxCorpCode,docking.getProjectCode(),zhangHao,miYao);}
					TeamCnt=TeamCntbyNo(docking.getCorpCode(),docking.getProjectCode(),docking.getTeamSysNo(),zhangHao,miYao);
					if(proCnt>0 && TeamCnt>0) {
						
							workerPayrollAddEntity.setProjectCode(docking.getProjectCode());
							workerPayrollAddEntity.setCorpCode(docking.getCorpCode());
							workerPayrollAddEntity.setCorpName(docking.getCorpName());
							workerPayrollAddEntity.setTeamSysNo(docking.getTeamSysNo());
							workerPayrollAddEntity.setPayMonth(docking.getPayMonth());

							//此项目待上传工资总人数
							Integer rsCnt=dataDockingMapper.getPayRollCnt(docking.getTeamName(),docking.getProBh().toString(),docking.getRegSerial(),docking.getPayMonth(),0);
							
//							System.out.println(rsCnt/20);
							for (int i = 0; i <= rsCnt/20 ; i++) {
								
								//2.根据项目查找待上传的人员工资
								List<DetailListEntity> list1=dataDockingMapper.getPayRollList(docking.getTeamName(),docking.getProBh().toString(),docking.getRegSerial(),docking.getPayMonth(),0);
								if (list1.size() != 0) {
	/*								//判断有一条非补发记录即可 是否补发  1是0否
									Integer sbsCnt=0;
									Integer fbsCnt=0;
									for (int s = 0; s < list1.size() ; s++) {
										if(list1.get(s).getIsBackPay()==1) {
											sbsCnt=sbsCnt+1;
										}else if(list1.get(s).getIsBackPay()==0) {
											fbsCnt=fbsCnt+1;
										}
									}*/
//									if(sbsCnt>0 || ( sbsCnt>0 && fbsCnt>0 )) {//w1	
									workerPayrollAddEntity.setDetailList(list1);
									workerPayrollAddEntity.setAppId(zhangHao);
									workerPayrollAddEntity.setSecretKey(miYao);
									
									res = JSONObject.parseObject(JSONObject.toJSONString(workerPayrollService.workerPayrollAdd(workerPayrollAddEntity)));
									
//									返回结果					
									Map<String, Object> res1 = new HashMap<String, Object>();
//									System.out.println("msg--" + res.getString("msg"));
									String requestSerial = "";
									Integer code = res.getInteger("code");
									String msg = res.getString("msg");
									String enUserId="";
									String decUserId="";
									if(code == 600) {
										//更新日志表记录返回信息
										for (int j = 0; j < list1.size(); j++) {

											JSONObject data=JSONObject.parseObject( res.getString("data"));
											requestSerial= data.getString("requestSerialCode");
											
//											dataDockingMapper.updateWagesBybhStatus(list1.get(j).getBh());
											dataDockingMapper.updateWagesBySerialStatus(list1.get(j).getUserSerial(),docking.getProBh(),docking.getPayMonth());
											
											//插入请求日志表
//											insertSendLogById(9,null,null,code,msg,requestSerial,list1.get(j).getBh());
											insertSendLogBypay(9,null,Integer.parseInt(docking.getRegSerial()),code,msg,requestSerial,list1.get(j).getUserSerial(),docking.getPayMonth());

										}
										
									}else {
										
								
									for (int j1 = 0; j1 < list1.size(); j1++) {
//										dataDockingMapper.updateWagesBybhStatus(list1.get(j1).getBh());
										dataDockingMapper.updateWagesBySerialStatus(list1.get(j1).getUserSerial(),docking.getProBh(),docking.getPayMonth());
									}
										
									if(msg.indexOf("[detailList")!=-1){
									
										String[] strarray=msg.split(";"); 
										for (int i1 = 0; i1 < strarray.length; i1++) {
											System.out.println("yyyy---"+strarray[i1]); 
											xi=0;

											Pattern pattern = Pattern.compile("\\[(.*?)\\]");
									        Matcher matcher = pattern.matcher(strarray[i1]);
									        while(matcher.find()){
									            System.out.println("www---"+matcher.group(1));
									            if(xi==1) {arri=Integer.parseInt(matcher.group(1));}
										        if(xi==2) {msg=strarray[i1].toString();}
										        xi=xi+1;
									        }
							        
											//插入请求日志表
//									        insertSendLogById(9,null,null,code,msg,requestSerial,list1.get(arri).getBh());
									        insertSendLogBypay(9,null,Integer.parseInt(docking.getRegSerial()),code,msg,requestSerial,list1.get(arri).getUserSerial(),docking.getPayMonth());
									        
										        
										}		
									}else {
								        
										for (int k = 0; k < list1.size(); k++) {
						        		//插入请求日志表
//								        insertSendLogById(9,null,null,code,msg,requestSerial,list1.get(k).getBh());
								        insertSendLogBypay(9,null,Integer.parseInt(docking.getRegSerial()),code,msg,requestSerial,list1.get(arri).getUserSerial(),docking.getPayMonth());
										}

									}
								}
									
								 
								if(code == 600) {
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
									//休眠
//							        Thread.sleep(2000);
									
									//获取异常调用结果
							        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
							        dockingQueryResultEntity.setLx(9);
							        dockingQueryResultEntity.setRequestFun(0);
							        dockingQueryResultEntity.setRequestSerail(requestSerial);
							        dockingQueryResultEntity.setAppId(zhangHao);
							        dockingQueryResultEntity.setSecretKey(miYao);
							        QueryProBasicCode(dockingQueryResultEntity);
								}
							}
//							}//w1	
						}
					}//b.		
				}//a.
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
	    	e.printStackTrace();
	    }
	}

	
	
	
	/*public void SendPayrollInfoByUser() {// 9-工资按个人  0企业1项目2人员3参建单位4培训5班组6工人进退场7工人合同8工人考勤9工人工资
		try {
			JSONObject res = null;
			Integer arri=0;
			Integer xi=0;
			Integer proCnt=-1;
			Integer TeamCnt=-1;
			String cxCorpCode;
			String zhangHao;
			String miYao;
			WorkerPayrollAddEntity workerPayrollAddEntity = new WorkerPayrollAddEntity(); 
			
			//1.获取项目、班组 
			List<DockingPayRollTeamEntity> teamList= dataDockingMapper.getPayRollPro();
			if (teamList.size() != 0) {
				for (DockingPayRollTeamEntity docking : teamList) {//a.
					//判断项目、班组是否已存在
					proCnt=-1;
					TeamCnt=-1;
					List<DockingGetCorpKeyIntity> listc=dataDockingMapper.getCorpCodebyPro(docking.getProjectCode());
					cxCorpCode=listc.get(0).getCorpCode();
					zhangHao=listc.get(0).getAppId();
					miYao=listc.get(0).getSecretKey();
					if(cxCorpCode != "") {
					proCnt=ProjectCnt(cxCorpCode,docking.getProjectCode(),zhangHao,miYao);}
					TeamCnt=TeamCnt(docking.getCorpCode(),docking.getProjectCode(),docking.getTeamName(),zhangHao,miYao);
					if(proCnt>0 && TeamCnt>0) {
						
							workerPayrollAddEntity.setProjectCode(docking.getProjectCode());
							workerPayrollAddEntity.setCorpCode(docking.getCorpCode());
							workerPayrollAddEntity.setCorpName(docking.getCorpName());
							workerPayrollAddEntity.setTeamSysNo(docking.getTeamSysNo());
							workerPayrollAddEntity.setPayMonth(docking.getPayMonth());
							
							List<PayUserEntity> list5 = dataDockingMapper.getPayUser(docking.getTeamName(),docking.getProBh().toString(),docking.getRegSerial(),docking.getPayMonth());
							if(list5.size() != 0 ) {
							for (int i = 0; i <  list5.size() ; i++) {
								
								//2.根据项目查找待上传的人员工资
								List<DetailListEntity> list1=dataDockingMapper.getPayRollList1(docking.getProBh().toString(),list5.get(i).getUserSerial(),docking.getPayMonth());
								if (list1.size() != 0) {//a1.
		 
									Integer sbsCnt=0;//是补发
									Integer fbsCnt=0;//非补发
									//是否补发  1是0否
									for(int k = 0; k <  list1.size() ; k++) {
										if(list1.get(k).getIsBackPay()==1) {
											sbsCnt=sbsCnt+1;
										}if(list1.get(k).getIsBackPay()==0) {
											fbsCnt=fbsCnt+1;
										}
									}
									
									if(fbsCnt>0 || fbsCnt>0 && sbsCnt>0){//a2
										
										workerPayrollAddEntity.setDetailList(list1);
										workerPayrollAddEntity.setAppId(zhangHao);
										workerPayrollAddEntity.setSecretKey(miYao);
										
										res = JSONObject.parseObject(JSONObject.toJSONString(workerPayrollService.workerPayrollAdd(workerPayrollAddEntity)));
										
	//									返回结果					
										Map<String, Object> res1 = new HashMap<String, Object>();
										System.out.println("msg--" + res.getString("msg"));
										String requestSerial = "";
										Integer code = res.getInteger("code");
										String msg = res.getString("msg");
										String enUserId="";
										String decUserId="";
										if(code == 600) {
											//更新日志表记录返回信息
											for (int j = 0; j < list1.size(); j++) {
	
												JSONObject data=JSONObject.parseObject( res.getString("data"));
												requestSerial= data.getString("requestSerialCode");
												
												dataDockingMapper.updateWagesBybhStatus(list1.get(j).getBh());
												
												//插入请求日志表
												insertSendLogById(9,null,null,code,msg,requestSerial,list1.get(j).getBh());

											}
											
										}else {
											
									
										for (int j1 = 0; j1 < list1.size(); j1++) {
											dataDockingMapper.updateWagesBybhStatus(list1.get(j1).getBh());
										}
											
										if(msg.indexOf("[detailList")!=-1){
										
											String[] strarray=msg.split(";"); 
											for (int i1 = 0; i1 < strarray.length; i1++) {
												System.out.println("yyyy---"+strarray[i1]); 
												xi=0;
	
												Pattern pattern = Pattern.compile("\\[(.*?)\\]");
										        Matcher matcher = pattern.matcher(strarray[i1]);
										        while(matcher.find()){
										            System.out.println("www---"+matcher.group(1));
										            if(xi==1) {arri=Integer.parseInt(matcher.group(1));}
											        if(xi==2) {msg=strarray[i1].toString();}
											        xi=xi+1;
										        }
								        
												//插入请求日志表
										        insertSendLogById(9,null,null,code,msg,requestSerial,list1.get(arri).getBh());
											        
											}		
										}else {
									        
											for (int k = 0; k < list1.size(); k++) {
							        		//插入请求日志表
									        insertSendLogById(9,null,null,code,msg,requestSerial,list1.get(k).getBh());
											}
	
										}
									}
										
									 
									if(code == 600) {
										SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
										//休眠
								        Thread.sleep(2000);
										
										//获取异常调用结果
								        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
								        dockingQueryResultEntity.setLx(9);
								        dockingQueryResultEntity.setRequestFun(0);
								        dockingQueryResultEntity.setRequestSerail(requestSerial);
								        dockingQueryResultEntity.setAppId(zhangHao);
								        dockingQueryResultEntity.setSecretKey(miYao);
								        QueryProBasicCode(dockingQueryResultEntity);
									}
								}//a2.
							}//a1.
						}
						}	
					}//b.		
				}//a.
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
	    	e.printStackTrace();
	    }
	}*/

	
	
	
	
	//**************************************************************************
	public void SendProTrainingInfo()  {// 4-项目培训信息  0企业1项目2人员3参建单位4培训5班组
		try {
			JSONObject res = null;
			Integer arri=0;
			Integer xi=0;
			Integer isUserId =0;
			Integer proCnt=-1;
			String cxCorpCode;
			String zhangHao;
			String miYao;
			ProjectTrainingEntity projectTrainingEntity = new ProjectTrainingEntity();
			List<DockingProTrainingEntity> list = dataDockingMapper.getProTrainingInfo();
			if (list.size() != 0) {
				for (DockingProTrainingEntity docking : list) {//a.
					//判断项目是否已上传
					proCnt=-1;
					List<DockingGetCorpKeyIntity> listc=dataDockingMapper.getCorpCodebyPro(docking.getProjectCode());
					cxCorpCode=listc.get(0).getCorpCode();
					zhangHao=listc.get(0).getAppId();
					miYao=listc.get(0).getSecretKey();
					if(cxCorpCode != "") {
					proCnt=ProjectCnt(cxCorpCode,docking.getProjectCode(),zhangHao,miYao);
	//				proCnt=1;
					}
					if(proCnt >0) {//b.
						
						projectTrainingEntity.setProjectCode(docking.getProjectCode());
						projectTrainingEntity.setTrainingDate(docking.getTrainingDate());
						projectTrainingEntity.setTrainingDuration(docking.getTrainingDuration());
						projectTrainingEntity.setTrainingName(docking.getTrainingName());
						projectTrainingEntity.setTrainingTypeCode(docking.getTrainingTypeCode());
						projectTrainingEntity.setTrainer(docking.getTrainer());
						projectTrainingEntity.setTrainingOrg(docking.getTrainingOrg());
						projectTrainingEntity.setTrainingAddress(docking.getTrainingAddress());
						projectTrainingEntity.setDescription(docking.getDescription());
									
						
						//此培训待上传总人数
						//Integer rsCnt=dataDockingMapper.getProTrainingCnt(docking.getId());
						
						//System.out.println(rsCnt/10);
						//for (int i = 0; i <= rsCnt/10 ; i++) {
							
							List<WorkersEntity>  list1 = dataDockingMapper.getProTrainingDetailInfo(docking.getId(),docking.getRegSerial());
							if (list1.size() != 0) {
								projectTrainingEntity.setWorkers(list1);
							}
							projectTrainingEntity.setAppId(zhangHao);
							projectTrainingEntity.setSecretKey(miYao);
							
							res = JSONObject.parseObject(JSONObject.toJSONString(projectTrainingService.projectTrainingAdd(projectTrainingEntity)));
	
	//						返回结果    {"msg":"没有找到证件类型为01,证件号码为51112419730205643X的对应信息,上传失败!\r\n\r\n[trainingTypeCode]值不正确，请参见[培训类型]字典表!","code":601}					
							Map<String, Object> res1 = new HashMap<String, Object>();
							System.out.println("msg--" + res.getString("msg"));
							String requestSerial = "";
							Integer code = res.getInteger("code");
							String msg = res.getString("msg");
							String enUserId="";
							String decUserId="";
							isUserId=0;
							
							//不论成功与否，更新总表状态
							dataDockingMapper.updateProTraByIdStatus(docking.getId());
							if(code == 600) {
								//更新日志表记录返回信息
								for (int j = 0; j < list1.size(); j++) {
	
					
									JSONObject data=JSONObject.parseObject( res.getString("data"));
									requestSerial= data.getString("requestSerialCode");
			
									dataDockingMapper.updateProTrainingByIdStatus(list1.get(j).getId());
									
									//插入请求日志表
									insertSendLogById(4,null,null,code,msg,requestSerial,list1.get(j).getId());
					/*				DockingAddSendContractLogEntity  addContractLog = new DockingAddSendContractLogEntity();
									addContractLog.setLx(4);
									addContractLog.setParentId(list1.get(j).getId());
									addContractLog.setRequestJg(code);
									addContractLog.setRequestMessage(msg);
									addContractLog.setRequestSerial(requestSerial);
									dataDockingMapper.addSendJlLog(addContractLog);*/
								}
								
							}else {
								
								if(msg.indexOf("trainingDate")!=-1 || msg.indexOf("trainingDuration")!=-1 || msg.indexOf("trainingTypeCode")!=-1 ||
										msg.indexOf("trainer")!=-1|| msg.indexOf("trainingOrg")!=-1	|| msg.indexOf("trainingAddress")!=-1) {
								for (int j1 = 0; j1 < list1.size(); j1++) {
									
							        dataDockingMapper.updateProTrainingByIdStatus(list1.get(j1).getId());
					        		//插入请求日志表
							        insertSendLogById(4,null,null,code,msg,requestSerial,list1.get(j1).getId());
			/*						DockingAddSendContractLogEntity  addContractLog = new DockingAddSendContractLogEntity();
									addContractLog.setLx(4);
									addContractLog.setParentId(list1.get(j1).getId());
									addContractLog.setRequestJg(code);
									addContractLog.setRequestMessage(msg);
									addContractLog.setRequestSerial(requestSerial);
									dataDockingMapper.addSendJlLog(addContractLog);*/
									}
								}else {
									for (int j3 = 0; j3 < list1.size(); j3++) {
										dataDockingMapper.updateProTrainingByIdStatus(list1.get(j3).getId());
									}
									
									for (int j2 = 0; j2 < list1.size(); j2++) {
								        enUserId=list1.get(j2).getIdCardNumber();
								        decUserId=AES7Coder.decrypt(enUserId, miYao);
										
										
										if(msg.indexOf(decUserId) != -1) {
											isUserId=1;
											
	//								        dataDockingMapper.updateProTrainingByIdStatus(list1.get(j2).getId());
							        		//插入请求日志表
									        insertSendLogById(4,null,null,code,msg,requestSerial,list1.get(j2).getId());
					/*						DockingAddSendContractLogEntity  addContractLog = new DockingAddSendContractLogEntity();
											addContractLog.setLx(4);
											addContractLog.setParentId(list1.get(j2).getId());
											addContractLog.setRequestJg(code);
											addContractLog.setRequestMessage(msg);
											addContractLog.setRequestSerial(requestSerial);
											dataDockingMapper.addSendJlLog(addContractLog);*/
										}
									}
									
								if(isUserId == 0) {//培训记录已存在，未说明哪条的则全部置失败
									
									for (int j3 = 0; j3 < list1.size(); j3++) {
	
	//							        dataDockingMapper.updateProTrainingByIdStatus(list1.get(j3).getId());
						        		//插入请求日志表
								        insertSendLogById(4,null,null,code,msg,requestSerial,list1.get(j3).getId());
			/*							DockingAddSendContractLogEntity  addContractLog = new DockingAddSendContractLogEntity();
										addContractLog.setLx(4);
										addContractLog.setParentId(list1.get(j3).getId());
										addContractLog.setRequestJg(code);
										addContractLog.setRequestMessage(msg);
										addContractLog.setRequestSerial(requestSerial);
										dataDockingMapper.addSendJlLog(addContractLog);*/
	
									}
								}
							}
								
						}
							
						 
						if(code == 600) {
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
							//休眠
//					        Thread.sleep(2000);
							
							//获取异常调用结果
					        DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
					        dockingQueryResultEntity.setLx(4);
					        dockingQueryResultEntity.setRequestFun(0);
					        dockingQueryResultEntity.setRequestSerail(requestSerial);
					        dockingQueryResultEntity.setAppId(zhangHao);
					        dockingQueryResultEntity.setSecretKey(miYao);
					        QueryProBasicCode(dockingQueryResultEntity);
						}
					//}	
					}//b.
				}//a.		
			} 
		} catch (Exception e) {
			System.err.println(e.getMessage());
	    	e.printStackTrace();
	    }
	}
	
	
	public void GetRuesultInfo()  {// 10-结果查询
		try {
			DockingQueryResultEntity dockingQueryResultEntity = new DockingQueryResultEntity();
			
			List<DockingQueryResultInfoEntity>  list1 = dataDockingMapper.getResultInfo1();
			if (list1.size() != 0) {
				for (DockingQueryResultInfoEntity queryEntity : list1) {
					//获取异常调用结果
			        dockingQueryResultEntity.setLx(queryEntity.getSendLx());
			        dockingQueryResultEntity.setRequestFun(queryEntity.getSendFun());
			        dockingQueryResultEntity.setRequestSerail(queryEntity.getRequestSerial());
			        dockingQueryResultEntity.setAppId(queryEntity.getAppId());
			        dockingQueryResultEntity.setSecretKey(queryEntity.getSecretKey());
			        dockingQueryResultEntity.setEnterExitStatus(queryEntity.getWorkerEnterExitStatus());
			        QueryProBasicCode(dockingQueryResultEntity);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
	    	e.printStackTrace();
	    }
	}
	
	
	private void insertSendContractLog(Integer lx,String decUserId,Integer regSerial,Integer code,String msg,String requestSerial,Integer parentId,Integer userSerial){
		
		DockingAddSendContractLogEntity  addContractLog = new DockingAddSendContractLogEntity();
		addContractLog.setLx(lx);
		addContractLog.setUserId(decUserId);
		addContractLog.setRegserial(regSerial);
		addContractLog.setRequestJg(code);
		addContractLog.setRequestMessage(msg);
		addContractLog.setRequestSerial(requestSerial);
		addContractLog.setParentId(parentId);
		addContractLog.setUserSerial(userSerial);
		dataDockingMapper.addSendContractLog(addContractLog);
	}
	
	
	
	private void insertSendContractLogByID(Integer lx,String decUserId,Integer regSerial,Integer code,String msg,String requestSerial,Integer parentId){
		
		DockingAddSendContractLogEntity  addContractLog = new DockingAddSendContractLogEntity();
		addContractLog.setLx(lx);
		addContractLog.setUserId(decUserId);
		addContractLog.setRegserial(regSerial);
		addContractLog.setRequestJg(code);
		addContractLog.setRequestMessage(msg);
		addContractLog.setRequestSerial(requestSerial);
		addContractLog.setParentId(parentId);
		dataDockingMapper.addSendContractLogByID(addContractLog);
	}
	
	
	private void insertSendLogById(Integer lx,String decUserId,Integer regSerial,Integer code,String msg,String requestSerial,Integer parentId){
		
		DockingAddSendContractLogEntity  addContractLog = new DockingAddSendContractLogEntity();
		addContractLog.setLx(lx);
		addContractLog.setParentId(parentId);
		addContractLog.setRequestJg(code);
		addContractLog.setRequestMessage(msg);
		addContractLog.setRequestSerial(requestSerial);
		dataDockingMapper.addSendJlLog(addContractLog);
	}
	
	
	private void insertSendLogBypay(Integer lx,String decUserId,Integer regSerial,Integer code,String msg,String requestSerial,Integer parentId,String payMonth){
		
		DockingAddSendContractLogEntity  addContractLog = new DockingAddSendContractLogEntity();
		addContractLog.setLx(9);
		addContractLog.setParentId(parentId);
		addContractLog.setRegserial(regSerial);
		addContractLog.setRequestJg(code);
		addContractLog.setRequestMessage(msg);
		addContractLog.setRequestSerial(requestSerial);
		addContractLog.setPayMonth(payMonth);
		dataDockingMapper.addSendPayLog(addContractLog);
	}
	
	
	
	private Map<String, Object> getResult(JSONObject res) {
		
		Map<String, Object> res1 = new HashMap<String, Object>();
		System.out.println("msg--" + res.getString("msg"));
		String requestSerial = "";
		Integer code = res.getInteger("code");
		String msg = res.getString("msg");
		if(code == 600) {
			JSONObject data=JSONObject.parseObject( res.getString("data"));
			requestSerial= data.getString("requestSerialCode");
		}
		res1.put("code", code);
		res1.put("msg", msg);
		res1.put("requestSerial", requestSerial);
		return res1;
	}
	
	
	//更新wt_gdsend_log日志表
	private void updateSendLog(Integer xh,Integer code,String msg,String requestSerial) {
		
		UpdateSendLogEntity updateSendLogEntity = new UpdateSendLogEntity();
		updateSendLogEntity.setXh(xh);
		updateSendLogEntity.setSendCode(code);
		updateSendLogEntity.setSendMsg(msg);
		updateSendLogEntity.setRequestSerial(requestSerial);

		dataDockingMapper.updateSendLog(updateSendLogEntity);
		
	}
	
	
	
	//更新wt_gdsend_log日志表
	private void updateSendLogbyUserid(String decUserId,String teamName,Integer code,String msg,String requestSerial,Integer type,String entryTime,Integer sendLx) {
		
		//更新日志表记录返回信息
 
		UpdateSendLogbyUseridEntity upByUserid = new UpdateSendLogbyUseridEntity();
		upByUserid.setIdCardNumber(decUserId);
		upByUserid.setTeamName(teamName);
		upByUserid.setSendCode(code);
		upByUserid.setSendMsg(msg);
		upByUserid.setRequestSerial(requestSerial);
		upByUserid.setSendLx(sendLx);
		upByUserid.setEntryTime(entryTime);
		upByUserid.setType(type);
		
		dataDockingMapper.updateSendLogbyUserid(upByUserid);
		
	}
	
	
	
	//更新wt_gdsend_log日志表,身份证号有重复的
	private void updateSendLogbySerial(String decUserId,String teamName,Integer code,String msg,String requestSerial,Integer type,String entryTime,Integer sendLx,Integer userSerial,String regSerial) {
		
		//更新日志表记录返回信息
 
		UpdateSendLogbyUseridEntity upByUserid = new UpdateSendLogbyUseridEntity();
		upByUserid.setIdCardNumber(decUserId);
		upByUserid.setTeamName(teamName);
		upByUserid.setSendCode(code);
		upByUserid.setSendMsg(msg);
		upByUserid.setRequestSerial(requestSerial);
		upByUserid.setSendLx(sendLx);
		upByUserid.setEntryTime(entryTime);
		upByUserid.setType(type);
		upByUserid.setUserSerial(userSerial);
		upByUserid.setRegSerial(regSerial);
		dataDockingMapper.updateSendLogbySerial(upByUserid);
		
	}
	
	
	public void QueryProBasicCode(DockingQueryResultEntity dockingQueryResultEntity)   {// 异常查询结果
		try {
			Thread.sleep(5000);
			
			JSONObject res = null;
			Integer resultJg = 0;
			QueryResultEntity queryResultEntity = new QueryResultEntity();
			queryResultEntity.setRequestSerialCode(dockingQueryResultEntity.getRequestSerail());
			queryResultEntity.setAppId(dockingQueryResultEntity.getAppId());
			queryResultEntity.setSecretKey(dockingQueryResultEntity.getSecretKey());
			res=JSONObject.parseObject(JSONObject.toJSONString(resultService.queryResult(queryResultEntity)));
			
			Integer xi=0;
			Integer arri=0;
			String requestSerial = "";
			String msg ="";
			String projectCode="";
			String teamSysNo="";
			String payrollCode="";
			String enUserId="";
			String decUserId="";
			Integer status=null;
			Integer code = res.getInteger("code");
			if(code == 600) {
				JSONObject data=JSONObject.parseObject( res.getString("data"));
				requestSerial= data.getString("requestSerialCode");
				status= data.getInteger("status");
				
				if(status==20) {
					resultJg = 1;	
					if(dockingQueryResultEntity.getLx() == 1) {//0企业1项目2人员3参建单位4培训5班组
						JSONObject result=JSONObject.parseObject( data.getString("result"));
						projectCode= result.getString("projectCode");
						//新增时，更新项目表项目编码
						if(dockingQueryResultEntity.getRequestFun() == 0) {
								dataDockingMapper.updateProCode(requestSerial, projectCode);
							}
						}else if(dockingQueryResultEntity.getLx() ==2) {
							//更新成功日志状态
							dataDockingMapper.updateLogStatus(requestSerial);
							
						}else if(dockingQueryResultEntity.getLx() == 5){
							JSONObject result=JSONObject.parseObject( data.getString("result"));
							teamSysNo= result.getString("teamSysNo");
							//新增时，更新班组编码
							if(dockingQueryResultEntity.getRequestFun() == 0) {
								dataDockingMapper.updateTeamCode(requestSerial, teamSysNo);
							}
						}else if(dockingQueryResultEntity.getLx() == 6 && dockingQueryResultEntity.getEnterExitStatus() == 1 ) {//入场才更新
							
							//更新成功日志状态
							dataDockingMapper.updateLogStatus(requestSerial);
							
						}else if(dockingQueryResultEntity.getLx() == 9) {
							
							JSONArray result=(JSONArray)JSONObject.parseArray(data.getString("result"));
							JSONObject pcode=  result.getJSONObject(0);
							System.out.println("payrollCode="+payrollCode);
							payrollCode=pcode.getString("payrollCode");
							//更新工资编码
							dataDockingMapper.updateWagesCode(requestSerial, payrollCode);
						}
				}
				
				//写入查询日志表
				DockingRequestLogEntity requestEntity = new DockingRequestLogEntity();
				requestEntity.setLx(dockingQueryResultEntity.getLx());
				requestEntity.setRequestSerial(requestSerial);
				requestEntity.setRequestMessage(res.toString());
				requestEntity.setRequestJg(resultJg);
				requestEntity.setReturnStatus(status);
				//先查询此条请求是否是等待状态，是则先删除
				Integer JlCnt=dataDockingMapper.getJlCnt(dockingQueryResultEntity.getLx(), requestSerial);
				if(JlCnt>0) {
					dataDockingMapper.delRequestInfo(dockingQueryResultEntity.getLx(), requestSerial);
				}
				
				if(dockingQueryResultEntity.getLx() == 2 && dockingQueryResultEntity.getRequestFun() == 0 && status!=20 && status!=0) {//人员档案新增
					List<WorkerListEntity> listworker= dockingQueryResultEntity.getWorkerList();
					//人员档案异步：{"msg":"操作成功！","code":600,"data":{"result":"[workerList][0][idCardType]异常,目前只支持身份证类型的证件!","requestSerialCode":"projectworker-add-2019011413-2-0004","status":10}}
					String getResult=data.getString("result");
					xi=0;
					arri=0;
					if(getResult.indexOf("[workerList]")!=-1){
						
						String[] strarray=getResult.split(";"); 
						for (int i1 = 0; i1 < strarray.length; i1++) {
							xi=0;
	
							Pattern pattern = Pattern.compile("\\[(.*?)\\]");
						        Matcher matcher = pattern.matcher(strarray[i1]);
						        while(matcher.find()){
						            if(xi==1) {arri=Integer.parseInt(matcher.group(1));}
							        if(xi==2) {msg=strarray[i1].toString();}
							        xi=xi+1;
						        }
	
						        enUserId=listworker.get(arri).getIdCardNumber().toString();
						        decUserId=AES7Coder.decrypt(enUserId, secretKey);
						        //根据身份证号查找对应的日志表ID
						        Integer parentId=dataDockingMapper.getParentId(decUserId,requestSerial);
						        
								requestEntity.setParentId(parentId);
								dataDockingMapper.addRequestLog(requestEntity);
						}		
/*					}else if(getResult.indexOf("项目中已存在身份证为")!=-1) {
						//{"msg":"操作成功！","code":600,"data":{"result":"项目中已存在身份证为：211321198911247018的工人!\r\n项目中已存在身份证为：211403195601049218的工人!\r\n项目中已存在身份证为：412724197112044411的工人!\r\n项目中已存在身份证为：211323195811095415的工人!\r\n项目中已存在身份证为：412724196809144450的工人!","requestSerialCode":"projectworker-add-2019040111-1-3050","status":10}}
						String[] strarray=getResult.split("的工人"); 
						for (int i1 = 0; i1 < strarray.length; i1++) {
							xi=0;
	
							String regEx="[^0-9,X,x]";  
							Pattern p = Pattern.compile(regEx); 
						    Matcher matcher = p.matcher(strarray[i1]);
//						    System.out.println(matcher.replaceAll("").trim());
						    //写request表,
							dataDockingMapper.addRequestLog(requestEntity);
						    //更新success表状态
						    dataDockingMapper.updateLogStatus(requestSerial);

						}*/
						
					}else {
						dataDockingMapper.addRequestLog(requestEntity);

					}
	
				}else {
				
					dataDockingMapper.addRequestLog(requestEntity);
				}
			}else {
				msg = res.getString("msg");
			}
		
		} catch (Exception e) {
            // TODO Auto-generated catch block
			System.err.println(e.getMessage());
            e.printStackTrace();
        }
		
	}
	
 
		
	
	
	public Integer ProjectCnt(String ContractorCorpCode,String ProCode,String appId,String secrectKey)   {// 判断项目是否已存在 ,参数：总承包统一社会信用代码，项目编码
		

		ProjectBasicInfoReq proBasicInfoReq = new ProjectBasicInfoReq();
		Integer proCnt =-1;
		
		proBasicInfoReq.setContractorCorpCode(ContractorCorpCode);
		proBasicInfoReq.setProjectCode(ProCode);
		proBasicInfoReq.setPageIndex(0);
		proBasicInfoReq.setPageSize(50);
		proBasicInfoReq.setAppId(appId);
		proBasicInfoReq.setSecretKey(secrectKey);
		JsonResult<PageSearchRespEntity<ProjectBasicInfoAddEntity>> pro2 = projectBasicService.projectQuery(proBasicInfoReq);
		Integer err=pro2.getCode();
		if(err==600) {
			proCnt=pro2.getData().getTotalCount();
		}
		return proCnt;
		
	}
	
	
	public Integer ContractorCnt(String ContractorCorpCode,String ProCode,String appId,String secrectKey)   {// 判断参建单位是否已存在 ,参数：统一社会信用代码，项目编码
		
		ProjectContractorReq proContractorReq = new ProjectContractorReq();
		Integer contractorCnt =-1;
		
		proContractorReq.setCorpCode(ContractorCorpCode);
		proContractorReq.setProjectCode(ProCode);
		proContractorReq.setPageIndex(0);
		proContractorReq.setPageSize(50);
		proContractorReq.setAppId(appId);
		proContractorReq.setSecretKey(secrectKey);
		
		JsonResult<PageSearchRespEntity<ProjectBasicInfoAddEntity>> pro2 = projectContractorService.projectContractorQuery(proContractorReq);
		Integer err=pro2.getCode();
		if(err==600) {
			contractorCnt=pro2.getData().getTotalCount();
		}
		return contractorCnt;
		
	}
	
	
	
	public Integer TeamCnt(String CorpCode,String ProCode,String TeamName,String appId,String secrectKey)   {// 判断班组是否已存在 ,参数：统一社会信用代码，项目编码，班组名称
	 
		ProjectTeamReq projectTeamReq = new ProjectTeamReq();
		Integer teamCnt =-1;
		
		projectTeamReq.setCorpCode(CorpCode);
		projectTeamReq.setProjectCode(ProCode);
		projectTeamReq.setTeamName(TeamName);
		projectTeamReq.setPageIndex(0);
		projectTeamReq.setPageSize(50);
		projectTeamReq.setAppId(appId);
		projectTeamReq.setSecretKey(secrectKey);
		JsonResult<PageSearchRespEntity<ProjectTeamQueryEntity>> pro2 = projectTeamService.teamQuery(projectTeamReq);
		
		Integer err=pro2.getCode();
		if(err==600) {
			teamCnt=pro2.getData().getTotalCount();
		}
		return teamCnt;
		
	}
	
	
	public Integer TeamCntbyNo(String CorpCode,String ProCode,Integer teamSysNo,String appId,String secrectKey)   {// 判断班组是否已存在 ,参数：统一社会信用代码，项目编码，班组编码
		 
		ProjectTeamReq projectTeamReq = new ProjectTeamReq();
		Integer teamCnt =-1;
		
		projectTeamReq.setCorpCode(CorpCode);
		projectTeamReq.setProjectCode(ProCode);
		projectTeamReq.setTeamSysNo(teamSysNo);
		projectTeamReq.setPageIndex(0);
		projectTeamReq.setPageSize(50);
		projectTeamReq.setAppId(appId);
		projectTeamReq.setSecretKey(secrectKey);
		JsonResult<PageSearchRespEntity<ProjectTeamQueryEntity>> pro2 = projectTeamService.teamQuery(projectTeamReq);
		
		Integer err=pro2.getCode();
		if(err==600) {
			teamCnt=pro2.getData().getTotalCount();
		}
		return teamCnt;
		
	}
	
	
	public Integer PersionCnt(String CorpCode,String ProCode,Integer TeamNo,String idCardType,String idCardNumber,String appId,String secrectKey)   {// 判断班组是否已存在 ,参数：统一社会信用代码，项目编码，班组名称
		 
		WorkerReq workerReq = new WorkerReq();
		Integer PersionCnt =-1;
		
		workerReq.setCorpCode(CorpCode);
		workerReq.setProjectCode(ProCode);
		workerReq.setTeamSysNo(TeamNo);
		workerReq.setIdCardType(idCardType);
		workerReq.setIdCardNumber(idCardNumber);
		workerReq.setPageIndex(0);
		workerReq.setPageSize(50);
		workerReq.setAppId(appId);
		workerReq.setSecretKey(secrectKey);
		JsonResult<PageSearchRespEntity<WorkerQueryEntity>> pro2 = workerService.workerQuery(workerReq);
		
		Integer err=pro2.getCode();
		if(err==600) {
			PersionCnt=pro2.getData().getTotalCount();
		}
		return PersionCnt;
		
	}
	
 

	
	
	public void InsertSuccessLog(DockingInsertSuccessLogEntity  insertLogEntity)   {
		
		dataDockingMapper.delSuccess(insertLogEntity);
		dataDockingMapper.insertSuccess(insertLogEntity);

	}
	
	
	public void InsertSuccessLogbyUserID(DockingInsertSuccessLogEntity  insertLogEntity)   {
		Integer userCnt=-1;
		userCnt=dataDockingMapper.cxUserID(insertLogEntity);
		if(userCnt>0) {
		dataDockingMapper.delSuccessbyUserID(insertLogEntity);}
		dataDockingMapper.insertSuccessbyUserID(insertLogEntity);

	}
	
	
	public void InsertSuccessLogbySerial(DockingInsertSuccessLogEntity  insertLogEntity)   {
		Integer userCnt=-1;
		userCnt=dataDockingMapper.cxUserBySerial(insertLogEntity);
		if(userCnt>0) {
		dataDockingMapper.delSuccessbySerial(insertLogEntity);}
		dataDockingMapper.insertSuccessbySerial(insertLogEntity);

	}
	
	
/*    public static void main(String[] args) throws Exception {
    	String getResult="项目中已存在身份证为：21132119891124701x的工人!\\r\\n项目中已存在身份证为：21140319560104921X的工人!\\r\\n项目中已存在身份证为：412724197112044411的工人!\\r\\n项目中已存在身份证为：211323195811095415的工人!\\r\\n项目中已存在身份证为：412724196809144450的工人!\"";
    	String[] strarray=getResult.split("的工人"); 
		for (int i1 = 0; i1 < strarray.length; i1++) {

			String regEx="[^0-9,X,x]";  
			Pattern p = Pattern.compile(regEx); 
		    Matcher matcher = p.matcher(strarray[i1]);
		    System.out.println(matcher.replaceAll("").trim());
 
 
		}
    }*/
	

}