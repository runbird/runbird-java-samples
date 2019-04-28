package com.scy.demo.thread.sync002;

/**
 * synchronized的重入
 *
 * @author alienware
 */
public class SynchDubb {

    public static void main(String[] args) {

        final SynchDubb sd = new SynchDubb();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                sd.method1();
            }
        });
        t1.start();
    }

    public synchronized void method1() {
        System.out.println("<=========== there is method1");
        method2();
    }

    private synchronized void method2() {
        System.out.println("<=========== there is method2");
        method3();
    }

    private synchronized void method3() {
        System.out.println("<=========== there is method3");
    }


}
