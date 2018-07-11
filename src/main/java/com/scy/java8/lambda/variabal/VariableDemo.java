package com.scy.java8.lambda.variabal;

import java.util.function.Consumer;

/**
 * 类名： VariableDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2018/7/12 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class VariableDemo {

    public static void main(String[] args) {

        /*
        * java 参数内部类和外部类参数的问题：
        *   传值 不传 参
        *   不然会产生二异性
        *  因此 外部类参数需要用final修饰
        */


        String str = "hello world!";
        // str = ""; 则 18行报错，JDK1.8已经默认了final
        Consumer<String> consumer = s -> System.out.println(s + str);
        consumer.accept("suocaiyuan ");
    }
}
