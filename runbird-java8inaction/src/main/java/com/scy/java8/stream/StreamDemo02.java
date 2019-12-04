package com.scy.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 类名： StreamDemo02 <br>
 * 描述：TODO <br>
 * 创建日期： 2018/7/18 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class StreamDemo02 {
    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();

        //从集合 collection 创建流
        arr.stream();
        arr.parallelStream();

        //数组  Arrays 创建流
        IntStream stream = Arrays.stream(new int[]{1, 2, 3});

        //创建数字流
        IntStream.of(1, 2, 3);

        //使用 random 创建一个无限流
        new Random().ints().limit(100);

        //自定义
        Random random = new Random();
        Stream.generate(() -> random.nextInt()).limit(100);
    }
}
