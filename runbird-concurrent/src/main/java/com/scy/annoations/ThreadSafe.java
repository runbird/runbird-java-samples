package com.scy.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类名： ThreadSafe <br>
 * 描述：仅用于描述该类是线程安全的 <br>
 *     RetentionPolicy.SOURCE 不进行编译，runtime会进行编译
 * 创建日期： 2018/9/17 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {

    String value() default "";//方便进行扩展不同命名
}
