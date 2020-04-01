package com.scy.basicpoint.volitale;

/**
 * 类名： VolatileExample <br>
 * 描述：volatile 非同步方式 <br>
 * 创建日期： 2020/3/17 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class VolatileExample3 {

    public static volatile int count = 0; // 计数器
    public static final int size = 100000; // 循环测试次数

    public static void main(String[] args) {
        // ++ 方式
        Thread thread = new Thread(() -> {
            for (int i = 1; i <= size; i++) {
                count++;
            }
        });
        thread.start();

        // -- 方式
        for (int i = 1; i <= size; i++) {
            count--;
        }
        // 等所有线程执行完成
        while (thread.isAlive()) {}
        System.out.println(count); // 打印结果
    }
}
