package com.taotao.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class TestJedis {
	
	@Test
	public void testJedis() throws Exception {
		// 创建一个jedis对象
		Jedis jedis = new Jedis("192.168.87.129",6379);
		// 操作数据库
		jedis.set("jedis_key", "1234");
		String result = jedis.get("jedis_key");
		System.out.println(result);
		// 关闭数据库
		jedis.close();
	}
	
	@Test
	public void testJedisPool() throws Exception{
		// 创建一个数据库连接池，需要制定服务的ip和端口号
		JedisPool jedisPool = new JedisPool("192.168.87.129", 7001);
		// 从连接池获得连接
		Jedis redis = jedisPool.getResource();
		// 使用Jedis操作数据库（方法级别使用）
		String result = redis.get("jedis_key");
		System.out.println(result);
		// 关闭Jedis连接
		redis.close();
		jedisPool.close();
	}
	
	@Test
	public void testJedisCluster() throws Exception{
		// 第一步：使用JedisCluster对象。需要一个Set<HostAndPort>参数。Redis节点的列表。
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.87.129", 7001));
		nodes.add(new HostAndPort("192.168.87.129", 7002));
		nodes.add(new HostAndPort("192.168.87.129", 7003));
		nodes.add(new HostAndPort("192.168.87.129", 7004));
		nodes.add(new HostAndPort("192.168.87.129", 7005));
		nodes.add(new HostAndPort("192.168.87.129", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		// 第二步：直接使用JedisCluster对象操作redis。在系统中单例存在。
		jedisCluster.set("hello", "100");
		String result = jedisCluster.get("hello");
		// 第三步：打印结果
		System.out.println(result);
		// 第四步：系统关闭前，关闭JedisCluster对象。
		jedisCluster.close();
	}
}
