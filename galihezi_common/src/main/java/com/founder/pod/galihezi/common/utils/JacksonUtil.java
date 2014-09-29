package com.founder.pod.galihezi.common.utils;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;



public class JacksonUtil {
	protected static Logger log = Logger.getLogger(JacksonUtil.class);
	public static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
	}
	
	/**
	 * Json字符串转化成Java对象
	 * @param json
	 * @param clas
	 * @return Object
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getObjectForJson(String json,Class clas){
		Object object = null;
		try {
			object = mapper.readValue(json, clas);
		} catch (Exception e) {
			log.error("json to object error", e);
		}
		return object;
	}
	

	/**
	 * 根据json返回list
	 * @param json
	 * @param typeClas 指的是List的类�?比如List<MyBean> 中的MyBean.class
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public static List getListForJson(String json, Class typeClas){
		List list = null;
		try {
			list = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, typeClas));
		} catch (Exception e) {
			log.error("json to object error", e);
		}
		return list;
	}
	
	/**
	 * Json字符串转化成Map
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapForJson(String json){
		Map<String,Object> map = null;
		try {
			map = mapper.readValue(json, Map.class);
		} catch (Exception e) {
			log.error("json to object error", e);
		}
		return map;
	}
	
	/**
	 * Java对象转化成Json字符�?
	 * @param obj
	 * @return
	 */
	public static String getJsonForObject(Object obj){
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error("object to json error", e);
		}
		return json;
	}
	
	public static ObjectMapper getMapper(){
		return mapper;
	}

	public static void main(String[] arg) throws Exception{
	
	}
}
