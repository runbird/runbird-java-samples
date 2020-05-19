package com.scy.basicpoint.threadlocal.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类名： HttpInterceptor <br>
 * 描述：执行 RequestHolder中的remove()方法 <br>
 * 创建日期： 2018/9/24 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       log.info("<=== preHandle");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestHolder.remove();
        log.info("<=== afterCompletion");
        return;
    }
}
