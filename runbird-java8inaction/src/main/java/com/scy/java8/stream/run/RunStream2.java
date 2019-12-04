package com.scy.java8.stream.run;

import java.util.Random;
import java.util.stream.Stream;

/**
 * 类名： RunStream2 <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/2 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */

/**
 * 无状态操作中间加入有状态操作，有状态操作会截断无状态操作，然后进行单独处理
 */
public class RunStream2 {

    public static void main(String[] args) {
        Random random = new Random();

        //随机产生数据
        Stream<Integer> stream = Stream.generate(() -> random.nextInt())
                //产生500个（无限流不需要短路操作）
                .limit(500)
                //第1个无状态操作
                .peek(s -> System.out.println("peek:" + s))
                //第2个无状态操作
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return s > 100000;
                })
                //有状态操作  （一般两个参数）
                .sorted((i1, i2) -> {
                    System.out.println("排序：" + i1 + "," + i2);
                    return i1.compareTo(i2);
                })
                //再次无状态操作 （一般一个操作）
                .peek(i -> System.out.println("peek2:" + i));

        stream.count();

    }
}
