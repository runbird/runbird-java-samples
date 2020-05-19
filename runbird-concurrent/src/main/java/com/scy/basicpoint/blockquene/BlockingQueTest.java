package com.scy.basicpoint.blockquene;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 类名： BlockingQueTest <br>
 * 描述：TODO <br>
 * 创建日期： 2019/11/4 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class BlockingQueTest {

    public static void main(String[] args) throws InterruptedException {
        // 声明一个容量为10的缓存队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

        //new了三个生产者和一个消费者
        Producer producer1 = new Producer(queue,"producer1");
        Producer producer2 = new Producer(queue,"producer2");
        Producer producer3 = new Producer(queue,"producer3");
        Consumer consumer = new Consumer(queue);

        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();
        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer);

        // 执行10s
        Thread.sleep(10 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(2000);
        // 退出Executor
        service.shutdown();
    }
}
