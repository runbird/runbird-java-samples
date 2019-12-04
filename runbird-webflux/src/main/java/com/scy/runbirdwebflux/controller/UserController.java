package com.scy.runbirdwebflux.controller;

import com.scy.runbirdwebflux.domain.User;
import com.scy.runbirdwebflux.repository.UserRepository;
import com.scy.runbirdwebflux.util.CheckUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

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

    //以数据形式返回数据
    @GetMapping(value = "/")
    public Flux<User> getAll() {
        return repository.findAll();
    }

    //以SSE形式一条一条返回数据
    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll() {
        return repository.findAll();
    }

    @PostMapping(value = "/addUser")
    public Mono<User> addUser(@Valid @RequestBody User user) {
        //spring data JPA,新增和修改都是save,如果带有id是新增
        //如果有的不允许修改id可根据实际情况
        user.setId(null);
        CheckUtil.checkName(user.getName());
        return this.repository.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable(value = "id") String id) {
        // return this.repository.deleteById(id);
        return this.repository.findById(id)
                //如果要操纵数据并返回一个mono使用flatMap.只是转换数据用map
                //.flatMap(user -> this.repository.delete(user))
                .flatMap(this.repository::delete)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable(value = "id") String id, @Valid @RequestBody User user) {
        return this.repository.findById(id)
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return this.repository.save(u);
                })
                .map(u -> new ResponseEntity<User>(HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}")
    public Mono<ResponseEntity<User>> findUserById(@PathVariable(value = "id") String id) {
        return this.repository.findById(id)
                .map(u -> new ResponseEntity<User>(HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
                
    }
}
