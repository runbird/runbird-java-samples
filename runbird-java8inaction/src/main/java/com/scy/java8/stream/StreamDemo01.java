package com.scy.java8.stream;

import java.util.stream.IntStream;

/**
 * 类名： StreamDemo01 <br>
 * 描述：TODO <br>
 * 创建日期： 2018/7/18 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class StreamDemo01 {

    public static void main(String[] args) {
        //外部迭代
        int[] nums = {1, 2, 3};
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        System.out.println("外部迭代结果是：" + sum);

        //内部迭代
        int sum2 = IntStream.of(nums).sum();
        System.out.println("内部迭代的结果是：" + sum2);

        //流操作，Map是中间操作，sum是终止操作
        // int sum3 = IntStream.of(nums).map(i -> i * 2).sum();
        int sum3 = IntStream.of(nums).map(StreamDemo01::lowEn).sum();
        System.out.println("有终止操作，sum:" + sum3);

        //没有终止操作时，则不进行中间操作
        IntStream.of(nums).map(StreamDemo01::lowEn);
    }

    public static int lowEn(int i) {
        System.out.println("《--------------判断是否进行了中间操作" + i);
        return i * 2;
    }

}
