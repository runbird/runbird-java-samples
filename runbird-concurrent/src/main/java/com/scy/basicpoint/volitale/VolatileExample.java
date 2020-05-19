package com.scy.basicpoint.volitale;

/**
 * 类名： VolatileExample <br>
 * 描述：内存可见性 <br>
 * 创建日期： 2020/3/17 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class VolatileExample {

        // 可见性参数
        private static boolean flag = false;

       // private static volatile boolean flag = false;

        public static void main(String[] args) {
            new Thread(() -> {
                try {
                    // 暂停 0.5s 执行
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = true;
                System.out.println("flag 被修改成 true");
            }).start();

            // 一直循环检测 flag=true
            while (true) {
                if (flag) {
                    System.out.println("检测到 flag 变为 true");
                    break;
                }
            }
        }
}
