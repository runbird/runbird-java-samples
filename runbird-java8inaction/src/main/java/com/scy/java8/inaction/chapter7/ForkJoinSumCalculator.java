package com.scy.java8.inaction.chapter7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 类名： ForkJoinSumCalculator <br>
 * 描述：需要实现接口RecursiveTask <br>
 * 创建日期： 2020/7/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }


    /**
     * 核心将一个任务拆分成子任务
     * 分治算法
     *
     * @return
     */
    @Override
    protected Long compute() {
        int length = end - start;
        //拆分最小单元 顺行执行
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
      //  int mid = start + (end - start) / 2;
        int mid = start + ((end - start) >> 1);
        //创建一个子任务来为数组的前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, mid);
        //利用另一个 ForkJoinPool 线程异步执行新 创建的子任务
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, mid, end);
        //同步执行第二个子任务，有可能允许进一步递归划分
        Long rightResult = rightTask.compute();
        //读取第一个子任务的结果，如果尚未完成就等待
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    /** 在阈值之内，采用普通方法*/
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbsers = LongStream.rangeClosed(1,n).toArray();
        //ForkJoinTask 是 RecursiveTask 父类
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbsers);
        return new ForkJoinPool().invoke(task);
    }

    public static void main(String[] args) {
        System.out.println(forkJoinSum(1_1000_1000));
    }
}
