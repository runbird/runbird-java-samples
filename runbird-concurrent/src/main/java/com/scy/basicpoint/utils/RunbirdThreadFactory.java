package com.scy.basicpoint.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 类名： RunbirdThreadFactory <br>
 * 描述：TODO <br>
 * 创建日期： 2019/10/29 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class RunbirdThreadFactory implements ThreadFactory {

    private final String prefix;
    private final AtomicInteger threadNum = new AtomicInteger(0);
    private final LinkedBlockingQueue<Runnable> queue;
    private final RunbirdRejectHandler handler;
    private final int coreSize;
    private final int maxnumSize;

    public RunbirdThreadFactory(String prefix) {
        this.prefix = "runbird threadfactory's " + prefix + "-worker-";
        this.queue = new LinkedBlockingQueue<>(2);
        this.handler = new RunbirdRejectHandler();
        //获取CPU数量
        this.coreSize = Runtime.getRuntime().availableProcessors();
        this.maxnumSize = coreSize * 2;
    }

    @Override
    public Thread newThread(Runnable task) {
        String name = prefix + threadNum.incrementAndGet();
        Thread thread = new Thread(null, task, name, 0L);
        System.out.println(thread.getName());
        return thread;
    }

    public static void main(String[] args) {
        RunbirdThreadFactory threadFactory = new RunbirdThreadFactory("runbird");
        Task task = new Task();
        for (int i = 0; i < 100000; i++) {
            threadFactory.newThread(task).start();
        }
    }
}

class Task implements Runnable {
    private final AtomicLong num = new AtomicLong(1L);

    @Override
    public void run() {
        System.out.println("----> running: " + num.getAndIncrement());
    }
}