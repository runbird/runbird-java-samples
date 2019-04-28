package com.scy.demo.thread.sync003;

/**
 * 锁对象的改变问题
 *
 * @author alienware
 */
public class ChangeLock {

    private String lock = "lock";

    private void method() {
        //lock string filed
        synchronized (lock) {
            try {
                System.out.println("<======= 当前线程：" + Thread.currentThread().getName() + "开始");
                lock = "change lock"; //注释的情况下 t1开始->t1结束->t2开始->t2结束 锁住了
                Thread.sleep(2000);
                System.out.println("<======= 当前线程：" + Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

// 未注释的情况    <======= 当前线程：t1开始
//                <======= 当前线程：t2开始
//                <======= 当前线程：t1结束
//                <======= 当前线程：t2结束
    }


    public static void main(String[] args) {
        final ChangeLock changeLock = new ChangeLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t2");
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
