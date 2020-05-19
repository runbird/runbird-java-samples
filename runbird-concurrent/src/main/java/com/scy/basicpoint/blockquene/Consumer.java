package com.scy.basicpoint.blockquene;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 类名： Consumer <br>
 * 描述：TODO <br>
 * 创建日期： 2019/11/4 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class Consumer implements Runnable {
    private BlockingQueue<String> queue;
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        Random random = new Random();
        System.out.println("生产者线程开始启动。。。。");
        boolean isRunning = true;
        try {
            while (isRunning) {
                System.out.println("正从队列获取数据...");
                //有数据时直接从队列的队首取走，无数据时阻塞，在2s内有数据，取走，超过2s还没数据，返回失败
                String data = queue.poll(2, TimeUnit.SECONDS);
                if (data != null) {
                    System.out.println("正在消费数据。。。");
                    System.out.println("消费数据为： " + data);
                    Thread.sleep(random.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                } else {
                    // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                    isRunning = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.interrupted();
        }finally {
            System.out.println("退出消费者线程");
        }
    }
}
