package com.scy.basicpoint.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * 类名： RedisClient <br>
 * 描述：redis方法 <br>
 * 创建日期： 2018/11/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */

@Component
public class RedisClient {

    @Resource(name = "redisPool")
    private JedisPool jedisPool;

    public void set(String key, String value) {
        try (Jedis resource = jedisPool.getResource()) {
            resource.set(key, value);
        }
    }

    public String get(String key) {
        try (Jedis resource = jedisPool.getResource()) {
            return resource.get(key);
        }
    }
}
