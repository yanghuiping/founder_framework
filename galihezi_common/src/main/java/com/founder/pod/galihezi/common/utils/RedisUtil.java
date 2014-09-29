package com.founder.pod.galihezi.common.utils;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.founder.pod.galihezi.common.constants.ConfigConstants;

public class RedisUtil {
	private static JedisPool pool;
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(ConfigConstants.redis_MaxActive); 
        config.setMaxIdle(ConfigConstants.redis_MaxIdle); 
        config.setMaxWait(ConfigConstants.redis_MaxWait); 
        config.setTestOnBorrow(ConfigConstants.redis_TestOnBorrow); 
		pool = new JedisPool(config, ConfigConstants.redis_ServerIP,ConfigConstants.redis_ServerPort);
	}
	
	
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public static boolean existskey(String key){
		Jedis jedis = pool.getResource();
		boolean b = jedis.exists(key);
		pool.returnResource(jedis);
		return b;
	}
	
	/**
	 * 更新Key�?
	 * @param key
	 * @return
	 */
	public static void renamekey(String oldKey,String newKey){
		Jedis jedis = pool.getResource();
		jedis.rename(oldKey, newKey);
		pool.returnResource(jedis);
	}
	
	/**
	 * 通过key 获取value
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		Jedis jedis = pool.getResource();
		String result = jedis.get(key);
		pool.returnResource(jedis);
		return result;
	}
	
	/**
	 * 通过key 获取value并同时修改value
	 * @param key
	 * @return
	 */
	public static String getSetValue(String key,String value){
		Jedis jedis = pool.getResource();
		String result = jedis.getSet(key,value);
		pool.returnResource(jedis);
		return result;
	}
	
	/**
	 * 设置key value
	 * @param key
	 * @param value
	 */
	public static void setValue(String key,String value){
		Jedis jedis = pool.getResource();
		jedis.set(key, value);
		pool.returnResource(jedis);
	}
	
	/**
	 * 设置key value 如果不存�?
	 * @param key
	 * @param value
	 */
	public static void setValueIfNotExists(String key,String value){
		Jedis jedis = pool.getResource();
		jedis.setnx(key, value);
		pool.returnResource(jedis);
	}
	
	/**
	 * 追加数据
	 * @param key
	 * @param value
	 */
	public static void setValueAppend(String key,String value){
		Jedis jedis = pool.getResource();
		jedis.append(key, value);
		pool.returnResource(jedis);
	}
	
	/**
	 * 设置key的失效时�?
	 * @param key
	 * @param seconds
	 */
	public static void setKeyEx(String key,int seconds){
		Jedis jedis = pool.getResource();
		jedis.expire(key, seconds);
		pool.returnResource(jedis);
	}
	
	/**
	 * 删除Key
	 * @param key
	 * @param seconds
	 */
	public static void removeKey(String... key){
		Jedis jedis = pool.getResource();
		jedis.del(key);
		pool.returnResource(jedis);
	}
	
	/**
	 * 放置value到某个List�?从头部开始放
	 * pustList("list","listvalue1")
	 * pustList("list","listvalue2")
	 * @param key
	 * @param value
	 */
	public static void pustListHead(String key,String value){
		Jedis jedis = pool.getResource();
		jedis.lpush(key, value);
		pool.returnResource(jedis);
	}
	
	/**
	 * 放置value到某个List�?从尾部开始放
	 * pustList("list","listvalue1")
	 * pustList("list","listvalue2")
	 * @param key
	 * @param value
	 */
	public static void pustListTail(String key,String value){
		Jedis jedis = pool.getResource();
		jedis.rpush(key, value);
		pool.returnResource(jedis);
	}
	
	/**
	 * 修改List中的某个位置的�?
	 * @param key
	 * @param value
	 */
	public static void setList(String key,long index,String value){
		if(getListSize(key) > index){
			Jedis jedis = pool.getResource();
			jedis.lset(key, index,value);
			pool.returnResource(jedis);
		}
	}
	
	/**
	 * 获取List中下标为index的对�?
	 * @param key
	 * @param index
	 * @return
	 */
	public static String getListByIndex(String key,long index){
		Jedis jedis = pool.getResource();
		String result = jedis.lindex(key, index);
		pool.returnResource(jedis);
		return result;
	}
	
	/**
	 * 获取List长度
	 * @param key
	 * @return
	 */
	public static long getListSize(String key){
		Jedis jedis = pool.getResource();
		long result = jedis.llen(key);
		pool.returnResource(jedis);
		return result;
	}
	
	/**
	 * 获取List中的某一�?
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getList(String key,long start,long end){
		Jedis jedis = pool.getResource();
		List<String> result = jedis.lrange(key, start, end);
		pool.returnResource(jedis);
		return result;
	}
	
	/**
	 * 获取整个List<String>
	 * @param key
	 * @return
	 */
	public static List<String> getListByKey(String key){
		Jedis jedis = pool.getResource();
		List<String> result = jedis.lrange(key, 0, -1);
		pool.returnResource(jedis);
		return result;
	}
	
	/**
	 * 删除list指定value的�?，count为个�?负数为从后往前删,0代表删除�?���?
	 * @param key
	 * @param count 
	 * @param value
	 */
	public static long removeList(String key,long count,String value){
		Jedis jedis = pool.getResource();
		long result = jedis.lrem(key, count, value);
		pool.returnResource(jedis);
		return result;
	}
	
	/**
	 * 安装list的index序号删除list中的元素
	 * @param key
	 * @param index
	 * @return 删除的个�?
	 */
	public static long removeListIndex(String key,long index){
		String deleteStr = "@#_delete_#@";
		setList(key, index, deleteStr);
		return removeList(key,0,deleteStr);
	}
	
	/**
	 * 删除区间之外的�?
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static String removeList(String key,long start,long end){
		Jedis jedis = pool.getResource();
		String result = jedis.ltrim(key, start, end);
		pool.returnResource(jedis);
		return result;
	}
	
	/**
	 * 出栈�?��List对象
	 * @param key
	 * @return
	 */
	public static String popList(String key){
		Jedis jedis = pool.getResource();
		String result = jedis.lpop(key);
		pool.returnResource(jedis);
		return result;
	}
	

	public static void main(String[] arg) throws Exception{
		
	}
}
