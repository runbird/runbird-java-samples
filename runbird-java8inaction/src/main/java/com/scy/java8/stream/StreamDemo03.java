package com.scy.java8.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * 类名： StreamDemo03 <br>
 * 描述：TODO <br>
 * 创建日期： 2018/8/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class StreamDemo03 {

    public static void main(String[] args) {
        String str = "hello world!";

        //输出结果 5 6
        Stream.of(str.split(" ")).map(s -> s.length())
                .forEach(System.out::println);

        //输出结果 6
        Stream.of(str.split(" ")).filter(s -> s.length() > 5).map(s -> s.length())
                .forEach(System.out::println);

        //flatMap A->B属性（是个集合）最终得到所有的A元素里面的所有B属性
        //intStream/longStream并不是stream的子类，所以需要装箱Boxed
        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed())
                .forEach(i -> System.out.println((char) i.intValue()));

        //peek用于debug，是个中甲你操作和forEach是终止操作
        System.out.println("<======== peek");
        Stream.of(str.split(" ")).peek(System.out::println)
                .forEach(System.out::println);

        //limit使用，主要使用于无限流
        new Random().ints().filter(i -> i > 100 && i < 1000).limit(10)
                .forEach(System.out::println);

    }
}
