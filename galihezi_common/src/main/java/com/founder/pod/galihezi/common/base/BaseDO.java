package com.founder.pod.galihezi.common.base;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDO implements Serializable {
	private static final long serialVersionUID = -7462083912498888895L;
	
	protected final Log log = LogFactory.getLog(this.getClass().getName());
}
