package com.youyicn.service.impl;

import com.youyicn.mapper.OsceSortMapper;
import com.youyicn.model.OsceSort;
import com.youyicn.service.OsceSortService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

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


}
