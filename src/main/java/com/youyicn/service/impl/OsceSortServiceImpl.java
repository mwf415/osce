package com.youyicn.service.impl;

import com.youyicn.mapper.OsceSortMapper;
import com.youyicn.model.ExamStationRecord;
import com.youyicn.model.OsceSort;
import com.youyicn.model.UserParm;
import com.youyicn.service.OsceSortService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("osceSortService")
@Transactional
public class OsceSortServiceImpl extends BaseService<OsceSort> implements OsceSortService {


    @Resource
    private OsceSortMapper osceSortMapper;

    @Override
    public OsceSort getByExamIdAndUserId(Integer examId, String userId) {

        Example example = new Example(OsceSort.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("examid", examId);
        criteria.andEqualTo("userid", userId);

        List<OsceSort> osceSorts = mapper.selectByExample(example);
        OsceSort osceSort = new OsceSort();

        if (osceSorts.size()>0){
            osceSort= osceSorts.get(0);
        }
        return osceSort;
    }

    @Override
    public OsceSort getMaxOsceSort(Integer examId) {
        return osceSortMapper.getMaxOsceSort(examId);
    }

	@Override
	public List<OsceSort> getOsceSortByExamId(Integer examId) {
		  Example example = new Example(OsceSort.class);
	        Example.Criteria criteria = example.createCriteria();
	        criteria.andEqualTo("examid", examId);
	        	
	        List<OsceSort> osceSorts = mapper.selectByExample(example);

	        return osceSorts;
	}

    @Override
    public List<String> getInUserByExamId(Integer examId) {


        return osceSortMapper.getInUserByExamId(examId);
    }

    @Override
    public List<String> getUnInUserByExamId(Integer examId) {
        return osceSortMapper.getUnInUserByExamId(examId);
    }

    /**
     * 根据入参查询对应的学生，主要功能事查看站点的数据
     * @param examId
     * @param stationId
     * @param state
     * @return
     */
    @Override
    public List<String> getUserByExamId(Integer examId, Integer stationId, Integer state) {

        Map<String,Integer> parm = new HashMap<>();
        parm.put("examId" ,examId);
        parm.put("stationId" ,stationId);
        parm.put("state" ,state);
         osceSortMapper.getUserByExamId(parm);
        return  null;
    }


    public List<UserParm> getUserDetailService(Integer examId){
        List<ExamStationRecord> userDetailList = osceSortMapper.getUserDetailByExamId(examId);
        Map<String , List<ExamStationRecord>> userExamStationMap = new HashMap<>();
        List<String> userIds = new ArrayList<>();

        if(userDetailList.size()>0){
        for (ExamStationRecord examStationRecord : userDetailList) {

            String userId = examStationRecord.getUserId();
            userIds.add(userId);
            if(userExamStationMap.containsKey(userId)){
                List<ExamStationRecord> examStationRecords = userExamStationMap.get(userId);
                examStationRecords.add(examStationRecord);
            }else {
                List<ExamStationRecord> examStationRecords = new ArrayList<>();
                examStationRecords.add(examStationRecord);
                userExamStationMap.put(userId,examStationRecords);
            }
        }}

        List<UserParm> userParms = new ArrayList<>();
        for (String userId : userIds) {
            List<ExamStationRecord> examStationRecords = userExamStationMap.get(userId);
            UserParm userParm = new UserParm();
            userParm.setUserId(userId);
            StringBuffer finished = new StringBuffer();
            StringBuffer unfinished = new StringBuffer();
            for (ExamStationRecord examStationRecord : examStationRecords) {
                Byte state = examStationRecord.getState();
                switch (state){
                    case 0:
                        unfinished=unfinished.append(examStationRecord.getStationName()+";");
                    case 1:
                        finished = finished.append(examStationRecord.getStationName()+"");
                }
                userParm.setUserName(examStationRecord.getRealName());
            }
            userParm.setFinished(finished.toString());
            userParm.setUnFinished(unfinished.toString());
            userParms.add(userParm);
        }
return userParms;
    }

}
