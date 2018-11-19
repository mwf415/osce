package com.youyicn.service.impl;

import com.youyicn.mapper.ExamStationRecordMapper;
import com.youyicn.mapper.ExamUserMapper;
import com.youyicn.mapper.OsceSortMapper;
import com.youyicn.model.*;
import com.youyicn.service.OsceSortService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

@Service("osceSortService")
@Transactional
public class OsceSortServiceImpl extends BaseService<OsceSort> implements OsceSortService {


    @Resource
    private OsceSortMapper osceSortMapper;

    @Resource
    private ExamStationRecordMapper examStationRecordMapper;

    @Resource
    private ExamUserMapper examUserMapper;

    @Override
    public OsceSort getByExamIdAndUserId(Integer examId, String userId) {

        Example example = new Example(OsceSort.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("examid", examId);
        criteria.andEqualTo("userid", userId);

        List<OsceSort> osceSorts = mapper.selectByExample(example);
        OsceSort osceSort = new OsceSort();

        if (osceSorts.size() > 0) {
            osceSort = osceSorts.get(0);
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

    /**
     * 查询未报名的同学
     * @param examId
     * @return
     */
    @Override
    public List<UserParm> getUnInUserByExamId(Integer examId) {
        List<ExamUser> unInUserList= examUserMapper.getUnInUserByExamId(examId);
        List<UserParm> userParms = new ArrayList<>();
        if(unInUserList.size()>0){
            for (ExamUser examUser : unInUserList) {
                UserParm userParm = new UserParm();
                userParm.setUserId(examUser.getUserId());
                userParm.setUserName(examUser.getRealName());
                userParm.setFinished("");
                userParm.setUnFinished("");
                userParms.add(userParm);
            }
        }
        return userParms;
    }

    /**
     * 根据入参查询对应的学生，主要功能事查看站点的数据
     *
     * @param examId
     * @param stationId
     * @param state
     * @return
     */
    @Override
    public List<String> getUserByExamId(Integer examId, Integer stationId, Integer state) {

        Map<String, Integer> parm = new HashMap<>();
        parm.put("examId", examId);
        parm.put("stationId", stationId);
        parm.put("state", state);
        osceSortMapper.getUserByExamId(parm);
        return null;
    }


    public List<UserParm> getUserDetailService(Integer examId) {
        List<ExamStationRecord> userDetailList = examStationRecordMapper.getUserDetailByExamId(examId);
        Map<String, List<ExamStationRecord>> userExamStationMap = new HashMap<>();
        Set<String> userIds = new HashSet<>();

        if (userDetailList.size() > 0) {
            for (ExamStationRecord examStationRecord : userDetailList) {

                String userId = examStationRecord.getUserId();
                userIds.add(userId);
                if (userExamStationMap.containsKey(userId)) {
                    List<ExamStationRecord> examStationRecords = userExamStationMap.get(userId);
                    examStationRecords.add(examStationRecord);
                } else {
                    List<ExamStationRecord> examStationRecords = new ArrayList<>();
                    examStationRecords.add(examStationRecord);
                    userExamStationMap.put(userId, examStationRecords);
                }
            }
        }

        List<UserParm> userParms = new ArrayList<>();
        for (String userId : userIds) {
            List<ExamStationRecord> examStationRecords = userExamStationMap.get(userId);
            UserParm userParm = new UserParm();
            userParm.setUserId(userId);
            StringBuffer finished = new StringBuffer();
            StringBuffer unfinished = new StringBuffer();
            for (ExamStationRecord examStationRecord : examStationRecords) {
                Byte state = examStationRecord.getState();
                userParm.setUserName(examStationRecord.getRealName());
                switch (state) {
                    case 0:
                        unfinished = unfinished.append(examStationRecord.getStationName() + ";");
                        continue;
                    case 1:
                        finished = finished.append(examStationRecord.getStationName() + "");
                        continue;
                }

            }
            userParm.setFinished(finished.toString());
            userParm.setUnFinished(unfinished.toString());
            userParms.add(userParm);
        }
        return userParms;
    }

    /**
     * 查找完成的学员
     * @param examId
     * @return
     */
    @Override
    public String getFinishedUser (Integer examId){
        StringBuilder stringBuilder = new StringBuilder();
        List<ExamUser> finishedUsers = examUserMapper.getFinishedUserByExamId(examId);
        if(finishedUsers.size()>0){
            for (ExamUser finishedUser : finishedUsers) {
                stringBuilder.append(finishedUser.getRealName()+":"+finishedUser.getUserId()+";");
            }
        }
        return stringBuilder.toString();
    }

}
