package com.scy.basicpoint.demo01;

import com.scy.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 类名： ConcurrencyDemo01 <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/18 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
@NotThreadSafe
public class ConcurrencyDemo01 {

    //同时并发数量
    private static final int threadTotal = 200;

    //客户端请求数
    private static final int clientTotal = 5000;

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executors.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.info("errorMsg:{}" , e);
                }
                //每执行完一次，计数器 减1
                countDownLatch.countDown();
            });

        }
        //确保执行完毕，使其打印
        countDownLatch.await();
        log.info("执行结果：{}" , count);
        executors.shutdown();
    }

    private static void add() {
        count++;
    }
}
