package com.scy.java8.inaction.chapter7;

import java.util.stream.LongStream;

/**
 * 类名： ParallelsStreamDemo2 <br>
 * 描述：TODO <br>
 * 创建日期： 2020/7/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class ParallelsStreamDemo2 {

    //错误的使用并行方法
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        //显而易见 这是顺行操作
//        for (long l = 1; l <= n; l++) {
//            accumulator.add(l);
//        }
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    //并行的使用
    //由于多个线程在同时访问累加器，执行total += value，而这一句虽然看似简单，却不是一个原子操作。
    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .parallel()
                .forEach(accumulator::add);
        return accumulator.total;
    }
}


class Accumulator {
    protected long total = 0L;

    public long add(long value) {
        return total += value;
    }
}