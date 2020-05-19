package com.scy.basicpoint.threadlocal.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * 类名： ThreadDemo3 <br>
 * 描述：TODO <br>
 * 创建日期： 2019/1/31 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
public class ThreadLocalDemo3 {
    private static final ThreadLocal<String> userId = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        userId.set("id in main thread");

        Thread t2 = new Thread(() -> {
            log.info("thread02 get userId {}", userId.get());
        });

        Thread t1 = new Thread(() -> {
            log.info("thread01 get userId {}", userId.get());
            t2.start();
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t1.join();
    }
}
