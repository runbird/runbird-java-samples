package com.scy.frame.ThreadDemo.sync001;

/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性。
 *
 * @author alienware
 */
public class DirtyRead {

    private String username = "suocaiyuan";
    private String password = "123456";

    public synchronized void setValue(String username, String password) {
        this.username = username;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.password = password;
        System.out.println("<===== setValue最终结果：username = " + username + " , password = " + password);
    }

    //防止脏读
    public /*synchronized*/ void getValue() {
        System.out.println("<===== getValue方法得到：username = " + this.username + " , password = " + this.password);
    }

    public static void main(String[] args) throws InterruptedException {
        //main一直往下执行 1s执行完毕， t1相当于开一个分支，其中有sleep 3s
        final DirtyRead dirtyRead = new DirtyRead();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                dirtyRead.setValue("runbird", "000000");
            }
        });
        t1.start();

        Thread.sleep(1000);
        dirtyRead.getValue();
    }
}
