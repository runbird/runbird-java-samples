package com.scy.frame.ThreadDemo.sync002;

/**
 * 类名： SynchDubb02 <br>
 * 描述：TODO <br>
 * 创建日期： 2018/6/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class SynchDubb02 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                Sub sub = new Sub();
                sub.opreationSub();
            }
        });
        t1.start();
    }

    static class Main {

        public int i = 10;

        public synchronized void opreationSup() {
            i--;
            try {
                System.out.println("<====== there is opreationSup i:" + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Sub extends Main {
        public synchronized void opreationSub() {

            try {
                while (i > 0) {
                    i--;
                    System.out.println("<====== there is opreationSub i:" + i);
                    Thread.sleep(1000);
                    this.opreationSup();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
