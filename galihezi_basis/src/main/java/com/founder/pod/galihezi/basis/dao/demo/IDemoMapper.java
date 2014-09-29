package com.founder.pod.galihezi.basis.dao.demo;

import java.util.List;

import com.founder.pod.galihezi.basis.domain.demo.DemoDO;

public interface IDemoMapper {
	public List<DemoDO> findDemoDO(DemoDO demo) throws Exception;
}
