package com.scy.java8.inaction.chapter7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 类名： ParallelsStreamDemo1 <br>
 * 描述：TODO <br>
 * 创建日期： 2020/7/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class ParallelsStreamDemo1 {
    //传统方法求 0->n 的和
    public static long iterativeSum(long n) {
        long sum = 0L;
        for (long i = 1L; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    //并行流内部使用了默认的ForkJoinPool它默认的线程数量就是你的处理器数量，这个值是由 Runtime.getRuntime().availableProcessors()得到的。
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    //进一步改进并行流，减少不必要的拆箱装箱
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }
    //1、使用了数值流LongStream，2、替换了不可并行的iterator
    //大大的优化了效率 比顺行sequentialSum 更快
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    //测量
    public static long measureSumPerf(Function<Long, Long> addr, long n) {
        long faster = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long star = System.nanoTime();
            Long sum = addr.apply(n);
            long duration = (System.nanoTime() - star) / 1_1000_1000;
            System.out.println("Result:" + sum);
            if (duration < faster) faster = duration;
        }
        return faster;
    }


    public static void main(String[] args) {
        //手动设置ForkJoin线程池的线程数量
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
//        System.out.println(iterativeSum(100));
//        System.out.println(sequentialSum(100));
//        System.out.println(parallelSum(100));

        System.out.println(measureSumPerf(ParallelsStreamDemo1::iterativeSum, 1_1000_1000));
        System.out.println(measureSumPerf(ParallelsStreamDemo1::sequentialSum, 1_1000_1000));
        //    System.out.println(measureSumPerf(ParallelsStreamDemo1::parallelSum, 1_1000_1000));
        System.out.println(measureSumPerf(ParallelsStreamDemo1::parallelRangedSum, 1_1000_1000));
    }
}
