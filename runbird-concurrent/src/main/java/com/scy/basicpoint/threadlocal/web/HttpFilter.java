package com.scy.basicpoint.threadlocal.web;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 类名： HttpFilter <br>
 * 描述：使用 RequestHolder中的 add()方法 <br>
 * 创建日期： 2018/9/24 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
@WebFilter(filterName = "threadLocalFilter",urlPatterns = "/threadLocal/*")
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final long id = Thread.currentThread().getId();
        //对对象操作 request.getSession().getAttribute("bean");
        log.info("<=== do filter,{},{}",id,request.getServletPath());
        RequestHolder.add(id);
        //如果filter不拦截请求，只是想进行数据处理，需要FilterChain.doFilter
        chain.doFilter(servletRequest,response);
    }

    @Override
    public void destroy() {

    }
}
