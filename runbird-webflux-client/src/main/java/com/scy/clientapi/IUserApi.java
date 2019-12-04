package com.scy.clientapi;

import com.scy.annoation.ApiServer;
import com.scy.domain.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 类名： IUserApi <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */

@ApiServer(value = "http://localhost:8080/user")
public interface IUserApi {

    @GetMapping("/")
    Flux<User> getUser();

    @DeleteMapping("/{id}")
    Mono<Void> deleteUser(@PathVariable("id") String id);

    @PostMapping("/")
    Mono<User> createUser(@RequestBody Mono<User> user);

    @GetMapping("/{id}")
    Mono<User> getUserById(@PathVariable("id") String id);
}
