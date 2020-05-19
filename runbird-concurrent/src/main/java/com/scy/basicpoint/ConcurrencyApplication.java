package com.scy.basicpoint;

import com.scy.threadlocal.web.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ServletComponentScan
public class ConcurrencyApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

//    @Bean
//    public FilterRegistrationBean<Filter> httpFilter() {
//        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new HttpFilter());
//        registrationBean.addUrlPatterns("/threadLocal/*");
//        return registrationBean;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor())
                .addPathPatterns("/**");
    }
}
