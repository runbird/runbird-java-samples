package com.scy.java8.stream.run;

import java.util.Random;
import java.util.stream.Stream;

/**
 * 类名： RunStream <br>
 * 描述：Stream运行机制 <br>
 * 创建日期： 2018/9/2 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */

/**
 * peek和filter
 * 1.所有操作都是链式操作，一个元素只迭代一次\
 * 2.每一个中间操作都返回一个新的stream,并且sourceStage指向新的HEAD,HEAD->nextStage->nextStage->....->null
 */
public class RunStream {

    public static void main(String[] args) {
        Random random = new Random();

        //随机产生数据
        Stream<Integer> stream = Stream.generate(random::nextInt)
                //产生500个（无限流不需要短路操作）
                .limit(500)
                //第1个无状态操作
                .peek(s -> System.out.println("peek:" + s))
                //第2个无状态操作
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return s > 100000;
                });

        //终止操作
        //ctrl+alt+v 抽取出变量stream
        long count = stream.count();
        System.out.println("the result of count :" + count);
    }
}
