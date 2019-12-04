package com.scy.annoation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//指明应用于类
@Retention(RetentionPolicy.RUNTIME) //指明运行时需要用到
public @interface ApiServer {

    //传值 "http://localhost:8080/user"
    String value() default "";

}
