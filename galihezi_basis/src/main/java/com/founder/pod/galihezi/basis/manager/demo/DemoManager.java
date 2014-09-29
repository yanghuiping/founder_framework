package com.founder.pod.galihezi.basis.manager.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.founder.pod.galihezi.basis.dao.demo.IDemoMapper;
import com.founder.pod.galihezi.basis.domain.demo.DemoDO;

@Repository("demoManager")
public class DemoManager {
	@Autowired
	private IDemoMapper demoMapper;
	
	public List<DemoDO> findDemoDO(DemoDO demo) throws Exception{
		return demoMapper.findDemoDO(demo);
	}
}
