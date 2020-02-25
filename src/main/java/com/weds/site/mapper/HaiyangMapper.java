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
import com.weds.site.entity.DtProjectRsp;
import com.weds.site.entity.HaiyangRecordListEntity;
import com.weds.site.entity.HaiyangWorkerListEntity;
import com.weds.site.entity.PayUserEntity;
import com.weds.site.entity.UpdateSendLogEntity;
import com.weds.site.entity.UpdateSendLogbyUseridEntity;
import com.weds.site.entity.WorkerListEntity;
import com.weds.site.entity.WorkerListEntryEntity;
import com.weds.site.entity.WorkersEntity;

@MyBatisDao
public interface HaiyangMapper {
 
	List<DtProjectRsp> getProjectList();

	
	List<HaiyangWorkerListEntity> getWorkerListByRegserial(@Param("sendfun") String sendfun,@Param("regSerial") String regSerial);
	
	
	List<HaiyangRecordListEntity> getHaiyangRecordList(@Param("regSerial") String regSerial);
	
	 
	void updateSendUserResult(@Param("userSerial") String userSerial);
	
	//更新工人考勤记录状态
	void updateWorkerJlBybhStatus(@Param("bh") Integer bh);
	
	
}
