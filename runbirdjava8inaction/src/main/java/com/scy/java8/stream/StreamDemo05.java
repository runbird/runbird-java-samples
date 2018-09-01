package com.scy.java8.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 类名： StreamDemo05 <br>
 * 描述：parallel并行流 和 forkJoin线程池<br>
 * 创建日期： 2018/8/29 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class StreamDemo05 {
    public static void main(String[] args) {
        //非并行流
        IntStream.range(1, 1000).peek(StreamDemo05::debug).count();
        //并行流
        IntStream.range(1, 1000).parallel().peek(StreamDemo05::debug).count();

        //先并行 然后串行 ---》并没有实现想要的结果
        //多次 parallel 和 sequential 以最后一次为准
        IntStream.range(1, 1000)
                //产生并行流
                .parallel().peek(StreamDemo05::debug)
                //产生串行流
                .sequential().peek(StreamDemo05::debug2)
                .count();

        //并行流使用的 线程池名字：ForkJoinPool.commonPool线程池
        //默认是当前CPU个数
        //设置默认线程数
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        IntStream.range(1, 1000).parallel().peek(StreamDemo05::debug3).count();

        //使用自己的线程池，防止其他任务阻塞默认的线程池
        //自己创建线程池名字：ForkJoinPool-1
        try {
            ForkJoinPool pool = new ForkJoinPool(20);
            pool.submit(() -> IntStream.range(1, 1000).parallel().peek(StreamDemo05::debug3).count());
            //关闭
            pool.shutdown();
            //防止main线程退出
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        synchronized (pool){
//            pool.wait();
//        }catch(Exception e){
//
//        }
    }

    private static void debug3(int i) {
        System.out.println(Thread.currentThread().getName() + "====>" + i);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void debug2(int i) {
        //为便于区分
        System.err.println("debug2" + i);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void debug(int i) {
        System.out.println("debug" + i);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
