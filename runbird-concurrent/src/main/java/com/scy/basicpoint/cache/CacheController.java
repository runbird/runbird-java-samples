package com.scy.basicpoint.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名： CacheController <br>
 * 描述：cache测试 <br>
 * 创建日期： 2018/11/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@RestController
public class CacheController {

    private final RedisClient redisClient;
    @Autowired
    public CacheController(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @GetMapping("/cache/set")
    public String setKeyVal(@RequestParam("k") String key, @RequestParam("y") String val) {
        redisClient.set(key, val);
        return "success!";
    }

    @GetMapping("/get")
    public String getVal(@RequestParam("k") String key) {
        return redisClient.get(key);
    }
}
