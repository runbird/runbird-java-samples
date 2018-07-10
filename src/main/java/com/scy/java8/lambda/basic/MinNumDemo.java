package com.scy.java8.lambda;

import java.util.stream.IntStream;

/**
 * 类名： MinNumDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2018/7/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class MinNumDemo {

    public static void main(String[] args) {
        //命令式编程
        int[] numbers = {123, 35, -5, 0, 3, 19};

        int min = Integer.MAX_VALUE;
        for (int i : numbers) {
            if (i < min) {
                min = i;
            }
        }
        System.out.println(min);

        //java8
        int minNum = IntStream.of(numbers).min().getAsInt();
        System.out.println(minNum);

    }
}
