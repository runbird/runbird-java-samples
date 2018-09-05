package com.scy.runbirdwebflux.controller;

import com.scy.runbirdwebflux.domain.User;
import com.scy.runbirdwebflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * 类名： UserController <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/5 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository repository;

    public UserController(@Qualifier("userRepository") UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/")
    public Flux<User> getAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/stream/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll() {
        return repository.findAll();
}   }
