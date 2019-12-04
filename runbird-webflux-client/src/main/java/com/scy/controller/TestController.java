package com.scy.controller;

import com.scy.clientapi.IUserApi;
import com.scy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * 类名： TestController <br>
 * 描述：测试Controller <br>
 * 创建日期： 2018/9/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@RestController
public class TestController {

    @Autowired
    IUserApi userApi;

    @GetMapping("/")
    public void testGetUsers() {
        Flux<User> users = userApi.getUser();
        users.subscribe(System.out::println);
    }

}
