package com.scy.runbirdwebflux2.exception;

import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * 类名： ExceptionHandler <br>
 * 描述：自定义异常处理 <br>
 * 创建日期： 2018/9/15 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Component
//调整ExceptionHandler优先级 数值越小优先级越高
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        //设置400
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        //设置返回类型
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);

        String errorMsg = toStr(ex);
        //writeWith需要DataBuffer
        DataBuffer db = response.bufferFactory().wrap(errorMsg.getBytes());

        return response.writeWith(Mono.just(db));
    }

    private String toStr(Throwable ex) {
        //已知异常
        if (ex instanceof CheckException) {
            CheckException e = (CheckException) ex;
            return e.getFiledName() + " invalid value :" + e.getFiledVlaue();
        //未知异常
        } else {
            ex.printStackTrace();
            return ex.toString();
        }
    }
}
