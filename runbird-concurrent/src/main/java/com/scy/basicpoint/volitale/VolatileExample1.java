package com.scy.basicpoint.volitale;

import java.util.concurrent.TimeUnit;

/**
 * 类名： VolatileExample1 <br>
 * 描述：TODO <br>
 * 创建日期： 2020/3/27 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class VolatileExample1 {
    private static boolean flag = false;
    private static int i = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                flag = true;
                System.out.println("flag 被修改成 true");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        while (!flag) {
            i++;
            System.out.println("flag:" + flag);
        }
        System.out.println("程序结束,i=" + i);
    }
}
