package com.founder.pod.galihezi.basis.service.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.pod.galihezi.basis.domain.demo.DemoDO;
import com.founder.pod.galihezi.basis.manager.demo.DemoManager;
import com.founder.pod.galihezi.basis.service.demo.IDemoService;

@Service("demoService")
public class DemoServiceImpl implements IDemoService {
	
	@Autowired
	private DemoManager demoManager;
	
	public List<DemoDO> findDemoDO(DemoDO demo){
		try {
			return demoManager.findDemoDO(demo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
