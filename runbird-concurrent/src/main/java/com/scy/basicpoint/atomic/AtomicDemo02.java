package com.scy.basicpoint.atomic;

import com.scy.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * 类名： ConcurrencyDemo02 <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/18 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
@ThreadSafe
public class AtomicDemo02 {
    //客户端访问数
    private static final int clientTotal = 5000;

    private static final int threadTotal = 200;

    private static LongAdder count = new LongAdder();//默认为0

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch downLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executors.execute(() -> {
                try {
                    semaphore.acquire();
                    count();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("errMsg: {}", e);
                }
                downLatch.countDown();
            });
        }
        downLatch.await();
        log.info("success:{}",count);
        executors.shutdown();

    }

    private static void count() {
        count.increment();//默认+1
        //需要加其他数字，可用 count.add(num L);
    }
}
