package com.sakanal.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class JedisDemo {
    //创建Jedis对象
    static Jedis jedis = new Jedis("192.168.38.129",6379);
    {
        jedis.auth("Redisqaz360782");
    }
    public static void main(String[] args) {

        //测试
        System.out.println(jedis.ping());

        jedis.set("name","张三");
        System.out.println(jedis.get("name"));
    }

    @Test
    public void testPing(){
        System.out.println(jedis.ping());
        System.out.println(jedis.keys("*"));
    }

    @Test
    public void testTransaction(){
        Transaction multi = jedis.multi();
        multi.set("name","李四");
        Response<String> name = multi.get("name");
        multi.exec();
        System.out.println(name.get());
    }
}
