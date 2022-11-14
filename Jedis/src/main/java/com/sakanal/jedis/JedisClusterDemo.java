package com.sakanal.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.LinkedHashSet;
import java.util.Set;

public class JedisClusterDemo {
    public static void main(String[] args) {
        HostAndPort hostAndPort = new HostAndPort("192.168.38.169", 6380);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);

        String set = jedisCluster.set("test", "test");
        System.out.println(set);
        String test = jedisCluster.get("test");
        System.out.println(test);
        jedisCluster.close();

//        JedisCluster jedisCluster ;
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        // 最大连接数
//        poolConfig.setMaxTotal(10000);
//        // 最大空闲数
//        poolConfig.setMaxIdle(1000);
//        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
//        // Could not get a resource from the pool
//        poolConfig.setMaxWaitMillis(3000);
//        poolConfig.setTestOnBorrow(true);
//        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
//        nodes.add(new HostAndPort("192.168.38.169" ,6379 ));//换自己的内网IP
//        nodes.add(new HostAndPort("192.168.38.169" ,6380 ));
//        nodes.add(new HostAndPort("192.168.38.169" ,6381 ));
//        nodes.add(new HostAndPort("192.168.38.169" ,6382 ));
//        nodes.add(new HostAndPort("192.168.38.169" ,6383 ));
//        nodes.add(new HostAndPort("192.168.38.169" ,6384 ));
////		cluster = new JedisCluster(nodes, poolConfig  );//		cluster = new JedisCluster(nodes, 5000 , 1000);  //		cluster = new JedisCluster( nodes, 2000, 5, 8, "redis@123", new GenericObjectPoolConfig() );//		cluster.auth("redis@123") ;
////		cluster = app.getBean(JedisCluster.class) ;
//        jedisCluster = new JedisCluster( nodes, 3000, 3000, 8, "Redisqaz360782", poolConfig );
//        String a = jedisCluster.set("test","test");
//        System.out.println(a);

    }
}
