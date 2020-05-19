package com.scy.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类名： NotThreadSafe <br>
 * 描述：仅用于描述该类是线程不安全的 <br>
 * 创建日期： 2018/9/17 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {

    String value() default "";
}
