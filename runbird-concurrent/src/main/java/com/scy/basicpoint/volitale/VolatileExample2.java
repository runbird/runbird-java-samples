package com.scy.basicpoint.volitale;

/**
 * 类名： VolatileExample2 <br>
 * 描述：指令重排 <br>
 * 创建日期： 2020/3/17 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class VolatileExample2 {
    // 指令重排参数
    private static int a = 0, b = 0;
    private static int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Thread t1 = new Thread(() -> {
                // 有可能发生指令重排，先 x=b 再 a=1
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                // 有可能发生指令重排，先 y=a 再 b=1
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("第 " + i + "次，x=" + x + " | y=" + y);
            if (x == 0 && y == 0) {
                // 发生了指令重排
                break;
            }
            // 初始化变量
            a = 0;
            b = 0;
            x = 0;
            y = 0;
        }
    }
}
