package com.scy.basicpoint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 类名： IntDemo <br>
 * 描述：并发请求 <br>
 * 创建日期： 2018/8/18 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class IntDemo {
    public static int threadNum = 100;
    public static int clientNum = 3000;

    private static long count = 0;

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

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
        System.out.println("get count:{}" + count);
    }

    private static void addCount() {
        count++;
    }

}
