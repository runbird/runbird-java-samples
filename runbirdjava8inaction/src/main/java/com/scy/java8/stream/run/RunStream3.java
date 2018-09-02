package com.scy.java8.stream.run;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 类名： RunStream3 <br>
 * 描述：并行环境 <br>
 * 创建日期： 2018/9/2 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */

/**
 * 并行环境下，有状态的中间操作不一定能并行操作
 * parallel/sequetial这2个操作也是中间操作(返回stream)
 * 他们不创建流，只修改Head的并行标志
 */
public class RunStream3 {

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
                    print("filter:" + s);
                    return s > 100000;
                })
                //有状态操作  （一般两个参数）
                .sorted((i1, i2) -> {
                    print("排序：" + i1 + "," + i2);
                    return i1.compareTo(i2);
                })
                //再次无状态操作 （一般一个操作）
                .peek(i -> print("peek2:" + i))
                //并行处理
                .parallel();

        stream.count();

    }

    public static void print(String s){
        //带线程名（测试并行情况）
        System.out.println(Thread.currentThread().getName()+"--->"+s);
        try {
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
