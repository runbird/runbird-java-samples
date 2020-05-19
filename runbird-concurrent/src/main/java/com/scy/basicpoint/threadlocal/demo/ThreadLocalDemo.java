package com.scy.basicpoint.threadlocal.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 类名： ThreadLocalDemo <br>
 * 描述：ThreadLocal demo <br>
 * 创建日期： 2019/1/30 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
public class ThreadLocalDemo {
    private static ThreadLocal<String> userId = ThreadLocal.withInitial(() -> "initId");

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                log.info("<------- current thread01 name",Thread.currentThread().getName());
                // 线程1两秒之后获得userId，并且设置userId为id1
                TimeUnit.SECONDS.sleep(2);
                log.info("initial userId in thread1 {}", userId.get());
                userId.set("id_01");
                log.info("thread1 set userId 01  {}", userId.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                log.info("<------- current thread02 name",Thread.currentThread().getName());
                // 线程2获取初始的userId，然后一秒之后设置为id2，再过两秒之后再次读取userId
                log.info("get userId in thread02 {}", userId.get());
                TimeUnit.SECONDS.sleep(1);
                userId.set("id_02");
                TimeUnit.SECONDS.sleep(2);
                log.info("thread1 set userId 02  {}", userId.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        // 在main线程等待两个线程执行结束
        t1.join();
        t2.join();

        String name = Thread.currentThread().getName();
        System.out.println(name);

    }

}
