package com.scy.aophelper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//EnableAspectJAutoProxy 开启自动代理，注解生效
@EnableAspectJAutoProxy
@Configuration
public class MainAOPConfig {

    //业务逻辑类加入容器中
    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    //切面类加载到容器当中，不仅要使用 @Bean,还要对该切面类加上 @Aspect
    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
