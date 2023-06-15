package com.crecema.my.java.redis.jedis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPooled;

public class JedisTest {

    private static JedisPooled jedis;

    @BeforeAll
    public static void init() {
        jedis = new JedisPooled("localhost", 6379);
    }

    @Test
    void testString() {
        System.out.println(jedis.keys("*"));
        jedis.set("hello", "world");
        System.out.println(jedis.keys("*"));
        jedis.set("hello", "world2");
        System.out.println(jedis.keys("*"));
        jedis.expire("hello", 60);
    }

    @Test
    void testList() {
        jedis.lpush("list", "1");
        jedis.lpush("list", "2");
        jedis.lpush("list", "3");
        System.out.println(jedis.lrange("list", 0, -1));
    }

    @Test
    void testSet() {
        jedis.sadd("set", "1");
        jedis.sadd("set", "2");
        jedis.sadd("set", "3");
        System.out.println(jedis.smembers("set"));
    }

    @Test
    void testZSet() {
        jedis.zadd("zset", 1, "1");
        jedis.zadd("zset", 2, "2");
        jedis.zadd("zset", 3, "3");
        System.out.println(jedis.zrange("zset", 0, -1));
    }

    @Test
    void testHash() {
        jedis.hset("hash", "1", "1");
        jedis.hset("hash", "2", "2");
        jedis.hset("hash", "3", "3");
        System.out.println(jedis.hgetAll("hash"));
    }

}
