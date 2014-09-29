package com.founder.pod.galihezi.common.constants;

import com.founder.pod.galihezi.common.utils.ConfigUtil;


/**
 * 用来存放配置文件中的常量
 * @author hewei
 *
 */
public class ConfigConstants {
	//Memcached配置
	public static final String memcachedServerUrl = ConfigUtil.getProperty("memcached_serversUrl");
	public static final int memcached_InitConn = ConfigUtil.getPropertyInt("memcached_InitConn");
	public static final int memcached_MinConn = ConfigUtil.getPropertyInt("memcached_MinConn");
	public static final int memcached_MaxConn = ConfigUtil.getPropertyInt("memcached_MaxConn");
	public static final int memcached_MaxIdle = ConfigUtil.getPropertyInt("memcached_MaxIdle");
	public static final int memcached_MaintSleep = ConfigUtil.getPropertyInt("memcached_MaintSleep");
	public static final boolean memcached_Nagle = ConfigUtil.getPropertyBoolean("memcached_Nagle");
	public static final int memcached_SocketTO = ConfigUtil.getPropertyInt("memcached_SocketTO");
	public static final int memcached_SocketConnectTO = ConfigUtil.getPropertyInt("memcached_SocketConnectTO");

	//httpclient
	public static final int httpclient_MaxConnectionsPerHost = ConfigUtil.getPropertyInt("httpclient_MaxConnectionsPerHost");
	public static final int httpclient_MaxTotalConnections = ConfigUtil.getPropertyInt("httpclient_MaxTotalConnections");
	
	//redis
	public static final String redis_ServerIP = ConfigUtil.getProperty("redis_ServerIP");
	public static final int redis_ServerPort = ConfigUtil.getPropertyInt("redis_ServerPort");
	public static final int redis_MaxActive = ConfigUtil.getPropertyInt("redis_MaxActive");
	public static final int redis_MaxIdle = ConfigUtil.getPropertyInt("redis_MaxIdle");
	public static final long redis_MaxWait = ConfigUtil.getPropertyLong("redis_MaxWait");
	public static final boolean redis_TestOnBorrow = ConfigUtil.getPropertyBoolean("redis_TestOnBorrow");
	
	//百度统计代码
	public static final String cnzz_statistics= ConfigUtil.getProperty("cnzz_statistics");
	//clicki统计代码
	public static final String clicki_statistics= ConfigUtil.getProperty("clicki_statistics");
	
	//css js版本
	public static final String deploy_version= ConfigUtil.getProperty("deploy_version");
	//css或js文件合并请求
	public static final String merge_http= ConfigUtil.getProperty("merge_http");
}
