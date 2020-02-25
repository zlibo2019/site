package com.weds.site.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.weds.framework.core.annotation.MyBatisDao;
import com.weds.site.entity.AttachmentsEntity;
import com.weds.site.entity.BankInfosEntity;
import com.weds.site.entity.BuilderLicensesEntity;
import com.weds.site.entity.ContractListEntity;
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
import com.weds.site.entity.DockingQueryResultInfoEntity;
import com.weds.site.entity.DockingRequestLogEntity;
import com.weds.site.entity.DockingTeamEntity;
import com.weds.site.entity.DockingWorkerAttendanceTeamEntity;
import com.weds.site.entity.DockingWorkerEntity;
import com.weds.site.entity.DockingWorkerProTeamEntity;
import com.weds.site.entity.DockingWorkerUpdateEntity;
import com.weds.site.entity.PayUserEntity;
import com.weds.site.entity.UpdateSendLogEntity;
import com.weds.site.entity.UpdateSendLogbyUseridEntity;
import com.weds.site.entity.WorkerListEntity;
import com.weds.site.entity.WorkerListEntryEntity;
import com.weds.site.entity.WorkersEntity;

@MyBatisDao
public interface DataDockingMapper {
 
	List<DockingCorpInfoEntity> getCorpInfo();
	
	List<DockingProBasicInfoEntity> getProBasicInfo();
	
	List<DockingProContractorEntity> getProSubContractorInfo();
	
	List<DockingTeamEntity> getTeamInfo();
	
	List<DockingProTrainingEntity> getProTrainingInfo();
	
	void updateSendLog(UpdateSendLogEntity updateSendLogEntity);
	
	void updateSendLogbyUserid(UpdateSendLogbyUseridEntity updateSendLogbyUseridEntity);
	
	
	void updateSendLogbySerial(UpdateSendLogbyUseridEntity updateSendLogbyUseridEntity);
	
	List<BuilderLicensesEntity> getProBuilderLicenses(Integer bh);
	
	void addRequestLog(DockingRequestLogEntity dockingRequestLogEntity);
	
	void updateProCode(@Param("requestSerialCode") String requestSerialCode,@Param("projectCode") String projectCode);
	
	List<BankInfosEntity> getProContractorBankInfo(String corpCode);
	
	void updateTeamCode(@Param("requestSerialCode") String requestSerialCode,@Param("teamSysNo") String teamSysNo);
	
	List<WorkersEntity> getProTrainingDetailInfo(@Param("id") Integer id,@Param("regSerial") Integer regSerial);
	
	List<DockingWorkerEntity> getWorkerInfo();
	
	List<WorkerListEntity> getWorkerListInfo(@Param("teamName") String teamName,@Param("regSerial") String regSerial);
	
	//查询新增人员所属班组
//	List<DockingAddWorkerTeamListEntity> getWorkerTeamListInfo();
	
	//根据班组找企业及项目
// 	List<DockingWorkerProTeamEntity> getWorkerProCorpInfo(@Param("teamName") String teamName,@Param("regSerial") String regSerial);
	List<DockingWorkerProTeamEntity> getWorkerProCorpInfo();
	
	//此次上传班组共多少人
	Integer getTeamWorkerCnt(@Param("teamName") String teamName,@Param("regSerial") String regSerial);
	
	
	//此次上传班组共多少人-进场
	Integer getTeamWorkerCnt_Entry(@Param("teamName") String teamName,@Param("regSerial") String regSerial);
	
	
	//此次上传班组共多少人-出场
	Integer getTeamWorkerCnt_Exit(@Param("teamName") String teamName,@Param("regSerial") String regSerial);
	
	//修改人员
	List<DockingWorkerUpdateEntity> getUpdateWorkerInfo();
	
	Integer getParentId(@Param("userID") String userID,@Param("requestSerial") String requestSerial);
	
	
	//班组及工地信息
	List<DockingWorkerProTeamEntity> getProjectTeamList();
	
	//进场人员信息
	List<WorkerListEntryEntity> getWorkerEntryList(@Param("teamName") String teamName,@Param("regSerial") String regSerial);
	
	//退场人员信息
	List<WorkerListEntryEntity> getWorkerExitList(@Param("teamName") String teamName,@Param("regSerial") String regSerial);
	
	//工人合同信息-项目列表
	List<DockingProContractListEntity> getProContractList();
	
	//工人合同信息
	List<ContractListEntity> getWorkerContractList(Integer proCode);
	
	//此项目需上传合同人数
	Integer getWorkerContractCnt(Integer proCode);
	
	//工人合同请求日志
	void addSendContractLog(DockingAddSendContractLogEntity dockingAddSendContractLogEntity);
	
	void addSendContractLogByID(DockingAddSendContractLogEntity dockingAddSendContractLogEntity);
	
	//更新工人合同状态
	void updateWorkerContractStateByID(@Param("userId") String userId,@Param("proCode") String proCode);
	
	//更新工人合同照片状态
	void updateWorkerContractPhotoState(@Param("userSerial") Integer userSerial,@Param("proCode") String proCode);
	
	
	//更新工人合同照片合并状态
	void updateWorkerContractPhotoState1(@Param("userSerial") Integer userSerial,@Param("proCode") String proCode);
	
	void updateWorkerContractState(@Param("xh") Integer xh,@Param("proCode") String proCode);
	
	//考勤记录-项目、班组列表
	List<DockingWorkerAttendanceTeamEntity> getWorkerAttendancePro();
	
	//此班组需上传考勤记录数量
	Integer getWorkerJlCnt(@Param("teamName") String teamName,@Param("regSerial") String regSerial);
	
	//待上传考勤记录
	List<DataListEntity>  getWorkerAttendanceList(@Param("teamName") String teamName,@Param("regSerial") String regSerial);
	
	//更新工人考勤记录状态
	void updateWorkerJlBybhStatus(Integer bh);
	
	//考勤记录请求日志
	void addSendJlLog(DockingAddSendContractLogEntity dockingAddSendContractLogEntity);
	
	
	//工资记录请求日志
	void addSendPayLog(DockingAddSendContractLogEntity dockingAddSendContractLogEntity);
	
	//待上传培训总人数
	Integer getProTrainingCnt(Integer id);
	
	//更新培训详情状态
	void updateProTrainingByIdStatus(Integer id);
	
	//工资-项目、班组列表
	List<DockingPayRollTeamEntity> getPayRollPro();
	
	//此班组需上传工资记录数量
	Integer getPayRollCnt(@Param("teamName") String teamName,@Param("proBh") String proBh,@Param("regSerial") String regSerial,@Param("payMonth") String payMonth,@Param("isBackPay") Integer isBackPay);
	
	//待上传工资列表
	List<DetailListEntity>  getPayRollList(@Param("teamName") String teamName,@Param("proBh") String proBh,@Param("regSerial") String regSerial,@Param("payMonth") String payMonth,@Param("isBackPay") Integer isBackPay);
	
	//待上传工资列表-根据人员序号、企业号查找
	List<DetailListEntity>  getPayRollList1(@Param("proBh") String proBh,@Param("userSerial") Integer userSerial,@Param("payMonth") String payMonth);
	
	//更新记录表状态
	void updateWagesBybhStatus(Integer bh);
	
	//更新记录表状态根据人员序号
	void updateWagesBySerialStatus(@Param("userSerial") Integer userSerial,@Param("proBh") Integer proBh,@Param("waMonth") String waMonth);
	
	//更新工资表编号
	void updateWagesCode(@Param("requestSerialCode") String requestSerialCode,@Param("payrollCode") String payrollCode);
	
	
	void updateProTraByIdStatus(Integer id);
	
//	//根据项目编号查找企业编号
	List<DockingGetCorpKeyIntity>  getCorpCodebyPro(String proCode);
	
	//插入成功日志
	void insertSuccess(DockingInsertSuccessLogEntity  insertLogEntity);
	
	void delSuccess(DockingInsertSuccessLogEntity  insertLogEntity);
	
	
	void insertSuccessbyUserID(DockingInsertSuccessLogEntity  insertLogEntity);
	
	void delSuccessbyUserID(DockingInsertSuccessLogEntity  insertLogEntity);
	
	Integer cxUserID(DockingInsertSuccessLogEntity  insertLogEntity);
	
	void updateLogStatus(String requestSerial);

	//查询等待处理的记录
	List<DockingQueryResultInfoEntity>  getResultInfo1();
	
	//查询此请求serial数据否存在
	Integer getJlCnt(@Param("sendLx") Integer sendLx,@Param("requestSerial") String requestSerial);
	
	void delRequestInfo(@Param("sendLx") Integer sendLx,@Param("requestSerial") String requestSerial);
	
	
	Integer cxUserBySerial(DockingInsertSuccessLogEntity  insertLogEntity);
	
	void delSuccessbySerial(DockingInsertSuccessLogEntity  insertLogEntity);
	
	void insertSuccessbySerial(DockingInsertSuccessLogEntity  insertLogEntity);

	//查找人员合同照片
	List<AttachmentsEntity> getAttachment(@Param("userSerial")  Integer userSerial,@Param("scmUrl") String scmUrl,@Param("regSerial")  Integer regSerial);
	
	//查找人员合同是否生成附件
	Integer getAttachmentCnt(@Param("userSerial")  Integer userSerial,@Param("regSerial")  Integer regSerial);
	
	//查找人员是否有合同文件
	Integer getAttCnt(@Param("userSerial")  Integer userSerial,@Param("regSerial")  Integer regSerial);
	
	//待发工资人员列表
	List<PayUserEntity>  getPayUser(@Param("teamName") String teamName,@Param("proBh") String proBh,@Param("regSerial") String regSerial,@Param("payMonth") String payMonth);
	
	
}
