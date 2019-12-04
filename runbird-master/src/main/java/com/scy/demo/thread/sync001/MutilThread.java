package com.scy.demo.thread.sync001;

/**
 * 两个线程 两个对象
 * <p>
 * 关键字synchronized取得的锁都是对象锁，而不是把一段代码（方法）当做锁，
 * 所以代码中哪个线程先执行synchronized关键字的方法，哪个线程就持有该方法所属对象的锁（Lock），
 * <p>
 * 在静态方法上加synchronized关键字，表示锁定.class类，类一级别的锁（独占.class类）。
 *
 * @author alienware
 */
public class MutilThread {

    private String tag;

    private static int num;

    //synchronized  获取的都是对象锁
    //static  获取的是类级别锁
    private static synchronized void printNum(String tag) {
        try {
            if ("a".equals(tag)) {
                num = 100;
                System.out.println("=====> print tag a " + "num:" + num);
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("=====> print tag b " + "num:" + num);
            }
            System.out.println("print tag " + tag + ",num:" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //注意观察run方法输出顺序 tag a ,tag b ,num b,numa
    /*
    =====> print tag a num:100
    =====> print tag b num:200
    print tag b,num:200
    print tag a,num:100

    */
    public static void main(String[] args) {
        final MutilThread m1 = new MutilThread();
        final MutilThread m2 = new MutilThread();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                m1.printNum("a");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                m2.printNum("b");
                // 设置为m1 顺序 m1.printNum("b");
            }
        });

        t1.start();
        t2.start();

    }


}
