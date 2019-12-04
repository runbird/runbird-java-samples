package com.scy.spring.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 类名： RedisBoot <br>
 * 描述：TODO <br>
 * 创建日期： 2019/7/19 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
@SpringBootApplication
public class RedisBoot {

    public static void main(String[] args) {
        SpringApplication.run(RedisBoot.class, args);
    }

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(@Value("${redis.host}") String host) {
        return new JedisPool(jedisPoolConfig(), host);
    }
}
