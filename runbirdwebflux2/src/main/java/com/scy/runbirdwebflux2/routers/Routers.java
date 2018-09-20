package com.scy.runbirdwebflux2.routers;

import com.scy.runbirdwebflux2.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 类名： Routers <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/13 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Configuration
public class Routers {

    @Bean
    RouterFunction<ServerResponse> userHandlerRouter(UserHandler handler) {
        return RouterFunctions.nest(
                //相当于  类上的@RequestMapping("/user")
                RequestPredicates.path("/user"),
                //相当于  方法上的@RequestMapping
                RouterFunctions.route(RequestPredicates.GET("/"), handler::findAll)
                        //accept 需要接收的Json格式
                        .andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::createUser)
                        .andRoute(RequestPredicates.DELETE("/{id}"), handler::deleteUser)
        );
    }

}
