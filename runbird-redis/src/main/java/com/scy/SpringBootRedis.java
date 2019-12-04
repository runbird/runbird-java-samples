package com.scy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @desc:
 * @author: Suocaiyuan
 * @date: 2019-12-04 22:17
 **/
@SpringBootApplication
public class SpringBootRedis {

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(@Value("${spring.redis.host}") String host) {
        return new JedisPool(jedisPoolConfig(), host);
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedis.class, args);
    }
}
