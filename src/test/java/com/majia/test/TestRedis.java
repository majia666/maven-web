package com.majia.test;

import com.majia.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestRedis {


    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testRedis() {
        System.out.println(redisUtil);
        String name = redisUtil.get("name");
        System.out.println(name);
    }
}
