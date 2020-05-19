package com.scy.basicpoint.deadlock;

import lombok.extern.slf4j.Slf4j;

/**
 * 类名： DeadLockDemo <br>
 * 描述：死锁Demo <br>
 * 创建日期： 2018/11/5 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
public class DeadLockDemo implements Runnable {
    public int flag = 1;

    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag{}", flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("获取了O2 ", 2);
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("获取了O1 ", 1);
                }
            }
        }
    }


    public static void main(String[] args) {
        DeadLockDemo demo1 = new DeadLockDemo();
        DeadLockDemo demo2 = new DeadLockDemo();
        demo1.flag = 1;
        demo2.flag = 0;
        new Thread(demo1).start();
        new Thread(demo2).start();
    }
}
