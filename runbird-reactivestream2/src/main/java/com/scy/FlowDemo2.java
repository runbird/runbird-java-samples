package com.scy;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * 类名： FlowDemo2 <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/13 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */


public class FlowDemo2 {

    public static void main(String[] args) throws InterruptedException {
        //1.定义发布者，发布的数据为Integer
        //使用jdk自带的SubmissionPublisher
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        //2.定义处理器，对数据进行过滤，并转换为String类型
        MyProcessor processor = new MyProcessor();

        //3.发布者 和 处理器 建立订阅关系
        publisher.subscribe(processor);

        //4.定义最终订阅者，消费String类型数据
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                //!!!! 这一步不写的话，就不能继续onNext()方法。。。
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("<===Subscriber获取到数据:"+item);
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("<===Subscriber数据处理完毕");
            }
        };

        //5.处理器和最终订阅者建立订阅关系
        processor.subscribe(subscriber);

        //6.产生数据并发布
        publisher.submit(-111);
        publisher.submit(222);

        //7.close
       publisher.close();

        //主线程延迟停止，为了打印数据
        Thread.currentThread().join(2000);

    }
}

/**
 * Processor,需要继承 SubmissionPublisher并实现Processor接口
 * 功能：输入数据源Integer，过滤掉小于0的，然后转换成字符串发出去
 */
class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        //保持订阅关系，需要用它来给发布者相应
        this.subscription = subscription;

        //请求一个数据
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        //接收到一个数据，然后处理
        System.out.println("<===处理器MyProcessor接收到的数据:" + item);
        //数据过滤，然后发出
        if (item > 0) {
            this.submit("<===转换后的数据:" + item);
        }
        //处理完调用request再次请求
        this.subscription.request(1);
        //或者达到目标，cancel，告诉发布者
        //this.subscription.cancel();
    }

    @Override
    public void onError(Throwable throwable) {
        //出现异常
        throwable.printStackTrace();
        //可以告诉发布者  后面就不再接收数据
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        //全部数据处理完了（发布者关闭）
        System.out.println("<===MyProcesoor处理器处理完了！");
        //关闭发布者
        this.close();
    }
}
