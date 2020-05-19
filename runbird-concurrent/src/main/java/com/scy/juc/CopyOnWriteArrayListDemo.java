package com.scy.juc;

import com.scy.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

/**
 * 类名： CopyOnWriteArrayListDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/28 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListDemo {
    private static int threadCilent = 5000;
    private static int localThread = 200;
    private static List<Integer> list = new CopyOnWriteArrayList<>();


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(localThread);
        final CountDownLatch downLatch = new CountDownLatch(threadCilent);
        for (int i = 0; i < threadCilent; i++) {
            final int count = i;
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                downLatch.countDown();
            });
        }
        downLatch.await();
        executor.shutdown();
        log.info("<=== size {}", list.size());
    }

    private static void update(int i) {
        list.add(i);
    }
}
