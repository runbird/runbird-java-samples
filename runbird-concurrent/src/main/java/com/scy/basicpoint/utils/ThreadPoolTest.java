package com.scy.basicpoint.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类名： ThreadPoolTest <br>
 * 描述：TODO <br>
 * 创建日期： 2019/10/23 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(2);
        RunbirdRejectHandler handler = new RunbirdRejectHandler();
        RunbirdThreadFactory threadFactory1 = new RunbirdThreadFactory("第一机房");
        RunbirdThreadFactory threadFactory2 = new RunbirdThreadFactory("第二机房");

        ThreadPoolExecutor threadPoolExecutor1 =
                new ThreadPoolExecutor(2, 4, 60L,
                        TimeUnit.SECONDS, queue, threadFactory1, handler);

        ThreadPoolExecutor threadPoolExecutor2 =
                new ThreadPoolExecutor(2, 4, 60L,
                        TimeUnit.SECONDS, queue, threadFactory1, handler);

        Task task = new Task();
        for (int i = 0; i < 100; i++) {
            threadFactory1.newThread(task);
            threadFactory2.newThread(task);
        }
    }

}
