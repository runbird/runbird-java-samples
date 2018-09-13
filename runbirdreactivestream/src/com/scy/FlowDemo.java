package com.scy;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * 类名： FlowDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/11 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class FlowDemo {

    public static void main(String[] args) throws InterruptedException {
        //1.发布者
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>() ;

        //2.订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                //保存订阅关系，用来给发布者响应
                this.subscription = subscription;
                //请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                //接收到了数据进行处理
                System.out.println("<=== 接收到了数据 "+item);

                //--->测试阻塞
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //处理完了数据，再次请求一次
                this.subscription.request(1);
                //处理完了数据，不再请求
                //this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();

                //告诉发布者 出现异常 不再接收数据
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
               //全部数据处理完，发布者关闭
                System.out.println("<=== 数据处理完毕");
            }
        };

        //3.建立订阅关系
        publisher.subscribe(subscriber);

//        //4.产生数据
//        int data = 111;
//        //submit是block方法。当订阅满了，则会阻塞
//        publisher.submit(data);
//        publisher.submit(222);
//        publisher.submit(333);

        //---> 测试阻塞情况
        for (int i = 0; i < 1000; i++) {
            //生成数据  <===生成数据257 缓冲池的大小（默认256）。
            // 然后开始阻塞，知道subs处理完了再开始submit  （因为3秒处理一条，故3S内填充满，开始阻塞submit）
            System.out.println("<===生成数据"+i);
            publisher.submit(i);
        }

        //5.结束  一般应放在finally 或者try-resource里确保关闭
        publisher.close();

        Thread.currentThread().join(2000);
    }
}
