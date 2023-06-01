package com.crecema.my.java.redis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

public class LockTest {

    private static RedissonClient redissonClient;

    @BeforeAll
    public static void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        config.setCodec(new StringCodec());
        redissonClient = Redisson.create(config);
    }

    @Test
    public void testLock() {
        // TODO
    }

}
