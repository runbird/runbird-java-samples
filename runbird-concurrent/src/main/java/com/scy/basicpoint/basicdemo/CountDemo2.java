package com.scy.basicpoint.basicdemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 类名： CountDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2018/8/19 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
public class CountDemo2 {
    private static int threadNum = 100;

    private static int clientNum = 3000;

    private static long count = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newWorkStealingPool(3);
        final Semaphore semaphore = new Semaphore(threadNum);
        for (int i = 0; i < clientNum; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    addCount();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
        //log.info("the count:{}"+count);
        System.out.println("<===========the count : " + count);
    }

    private static void addCount() {
        count++;
    }
}
