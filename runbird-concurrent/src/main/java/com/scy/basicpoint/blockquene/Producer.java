package com.scy.basicpoint.blockquene;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类名： Producer <br>
 * 描述：生产者 <br>
 * 创建日期： 2019/11/4 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class Producer implements Runnable {

    private volatile boolean isRuning = true; //是否运行标志
    private BlockingQueue blockingQueue;
    private static AtomicInteger count = new AtomicInteger(1);
   // private AtomicInteger count = new AtomicInteger(1);
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;
    private final String name;

    public Producer(BlockingQueue blockingQueue,String name) {
        this.blockingQueue = blockingQueue;
        this.name = name;
    }

    @Override
    public void run() {
        Random random = new Random();
        String data = null;

        System.out.println("生产者线程启动。。。。");
        try {
            while (isRuning) {
                data = "data: " + count.incrementAndGet();
                Thread.sleep(random.nextInt(DEFAULT_RANGE_FOR_SLEEP));//取0~DEFAULT_RANGE_FOR_SLEEP值的一个随机数
                System.out.println(name+" 将数据：" + data + "放入队列...");
                if (!blockingQueue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("放入数据失败：" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者线程！");
        }
    }

    public void stop() {
        isRuning = false;
    }
}
