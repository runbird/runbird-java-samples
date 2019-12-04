package com.scy.runbirdwebflux2.handlers;

import com.scy.runbirdwebflux2.domain.User;
import com.scy.runbirdwebflux2.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 类名： UserHandler <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/13 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */

@Component
public class UserHandler {

    @Resource(name = "userRepository")
    private final UserRepository repository;

    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * 查询所有用户
     * @param request
     * @return Mono<ServerResponse>
     */
    public Mono<ServerResponse>  findAll(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(this.repository.findAll(), User.class);
    }

    /**
     * 创建用户
     * @param request
     * @return Mono<ServerResponse>
     */
    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.repository.saveAll(user),User.class);
        //2.0.0模式
//        return user.flatMap(u->{
//            CheckUtil.checkName(u.getName());
//            return ok().contentType(MediaType.APPLICATION_JSON_UTF8)
//                .body(this.userRepository.saveAll(user),User.class);
//        });
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.repository.findById(id)
                .flatMap(user -> this.repository.delete(user)
                        //删除完以后直接返回  因为ServerResponse.ok() 没有body,需要build
                        .then(ServerResponse.ok().build()
                  //假设用户不存在
                .switchIfEmpty(ServerResponse.notFound().build())));
    }
}
