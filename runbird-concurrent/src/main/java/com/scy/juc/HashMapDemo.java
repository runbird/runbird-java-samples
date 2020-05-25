package com.scy.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 类名： HashMapDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2018/8/18 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class HashMapDemo {
    private static Map<Integer, Integer> map = new HashMap<>();
    public static int threadNum = 100;
    public static int clientNum = 3000;


    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadNum);

        for (int i = 0; i < clientNum; i++) {
            final int threadNum = i;
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    addMap(threadNum);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        executor.shutdown();
        System.out.println("map size:{}" + map.size());
    }

    private static void addMap(int threadNum) {
        map.put(threadNum, threadNum);
    }
}
