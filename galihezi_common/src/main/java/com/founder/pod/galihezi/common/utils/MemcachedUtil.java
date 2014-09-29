package com.founder.pod.galihezi.common.utils;

import java.util.Date;

import com.founder.pod.galihezi.common.constants.ConfigConstants;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcachedUtil {
	protected static MemCachedClient mcc = new MemCachedClient();

	static {

		// server list and weights
		//String[] servers = "192.168.8.213:11212".split(",");
		String[] servers = ConfigConstants.memcachedServerUrl.split(",");
		// Integer[] weights = { 1, 1, 1 };

		// grab an instance of our connection pool
		SockIOPool pool = SockIOPool.getInstance();

		// set the servers and the weights
		pool.setServers(servers);
		// pool.setWeights( weights );

		// set some basic pool settings
		// 5 initial, 5 min, and 250 max conns
		// and set the max idle time for a conn
		// to 6 hours
		// 设置初始连接数�?�?��和最大连接数以及�?��处理时间
		pool.setInitConn(ConfigConstants.memcached_InitConn);
		pool.setMinConn(ConfigConstants.memcached_MinConn);
		pool.setMaxConn(ConfigConstants.memcached_MaxConn);
		pool.setMaxIdle(ConfigConstants.memcached_MaxIdle);

		// set the sleep for the maint thread
		// it will wake up every x seconds and
		// maintain the pool size
		// 设置主线程的睡眠时间
		pool.setMaintSleep(ConfigConstants.memcached_MaintSleep);

		// set some TCP settings
		// disable nagle
		// set the read timeout to 3 secs
		// and don't set a connect timeout
		pool.setNagle(ConfigConstants.memcached_Nagle);
		pool.setSocketTO(ConfigConstants.memcached_SocketTO);
		pool.setSocketConnectTO(ConfigConstants.memcached_SocketConnectTO);

		// use a compatible hashing algorithm
		pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);

		// By default the java client will failover to a new server when a
		// server
		// dies. It will also failback to the original if it detects that the
		// server comes back (it checks the server in a falling off pattern).
		//
		// If you want to disable this (useful if you have flapping servers),
		// there are two settings to handle this.

		// pool.setFailover( false );
		// pool.setFailback( false );

		// initialize the connection pool
		pool.initialize();

		// lets set some compression on for the client
		// compress anything larger than 64k
//		 mcc.setCompressEnable( true );
//		 mcc.setCompressThreshold( 64 * 1024 );
	}

	public static boolean setObject(String key, Object value) {
		return mcc.set(key, value);
	}

	/**
	 * @param key
	 *            设置的名�?
	 * @param value
	 *            设置的�?
	 * @param time
	 *            失效时间，单位秒
	 */
	public static boolean setObject(String key, Object value, long time) {
		Date date = new Date();
		date.setTime(System.currentTimeMillis() + time * 1000);
		return mcc.set(key, value, date);
	}

	public static boolean setObject(String key, Object value, Date date) {
		return mcc.set(key, value, date);
	}

	public static boolean deleteObject(String key) {
		return mcc.delete(key);
	}

	public static Object getObject(String key) {
		return mcc.get(key);
	}

	public static Object[] getMultiObject(String[] key) {
		return mcc.getMultiArray(key);
	}

	public static void main(String[] arg) {

		long stime = System.currentTimeMillis();
		int number = 1000;
		for (int i = 0; i < number; i++) {
			MemcachedUtil.setObject("foo" + i, "This is a test String");
		}
		long etime = System.currentTimeMillis();
		System.out.println("set time:" + number * 1000.0 / (etime - stime)
				+ " ops");

		stime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			MemcachedUtil.getObject("foo" + i);
		}
		etime = System.currentTimeMillis();
		System.out.println("get  time:" + number * 1000.0 / (etime - stime)
				+ " ops");

		stime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			MemcachedUtil.deleteObject("foo" + i);
		}
		etime = System.currentTimeMillis();
		System.out.println("del  time:" + number * 1000.0 / (etime - stime)
				+ " ops");

	}
}
