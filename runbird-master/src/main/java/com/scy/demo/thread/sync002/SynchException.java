package com.scy.demo.thread.sync002;

/**
 * 类名： SynchException <br>
 * 描述：TODO <br>
 * 创建日期： 2018/6/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class SynchException {
    public static void main(String[] args) {
        final SynchException se = new SynchException();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                se.opreation();
            }
        }, "t1");
        t1.start();
    }

    private int i = 0;

    public synchronized void opreation() {
        while (true) { //while 和 try 的相对位置要注意，while在外会一直执行，try在外会遇到异常停止
            try {
                i++;
                Thread.sleep(200);
                System.out.println("<====== :" + Thread.currentThread().getName() + ", i =" + i);
                if (i == 20) {
                    //发生异常处
                    Integer.parseInt("a");
                }
            } catch (Exception e) {//InterruptedException
                e.printStackTrace();
                System.out.println("log info: " + e);
                //throw new RuntimeException();
                //continue;
            }
        }
    }
}
