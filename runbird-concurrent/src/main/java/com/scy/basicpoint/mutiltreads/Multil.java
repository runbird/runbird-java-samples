package com.scy.basicpoint.mutiltreads;

/**
 * 类名： Multil <br>
 * 描述：run() 和  start() 区别 <br>
 * 创建日期： 2019/1/28 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class Multil {

    public static void main(String[] args) {
        Runner1 r1 = new Runner1();
        Runner2 r2 = new Runner2();
        //Thread(Runnable target) 分配新的 Thread 对象
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

//        t1.start();
//        t2.start();

        t1.run();
        t2.run();
    }
}

class Runner1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("《=========进入Runner 1 运行状态@@ " + i);
        }
    }
}

class Runner2 implements Runnable {

    @Override
    public void run() {
        {
            for (int i = 0; i < 100; i++) {
                System.out.println("《=========进入Runner 2 运行状态@@ " + i);
            }
        }
    }
}