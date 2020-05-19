package com.scy.basicpoint.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * 类名： RedisConfig <br>
 * 描述：Redis配置 <br>
 * 创建日期： 2018/11/10 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Configuration
public class RedisConfig {

    @Bean(name = "redisPool")
    public JedisPool jedisPool(@Value("${redis-host}") String host, @Value("${redis-port}") int port) {
        return new JedisPool(host, port);
    }
}
