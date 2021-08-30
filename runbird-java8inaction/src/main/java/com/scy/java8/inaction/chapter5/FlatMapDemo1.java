package com.scy.java8.inaction.chapter5;

import com.scy.chapter4.Menu;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * 类名： FlatMapDemo1 <br>
 * 描述：TODO <br>
 * 创建日期： 2020/8/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class FlatMapDemo1 {
    public static void main(String[] args) {
        List<Menu> menu = Arrays.asList(new Menu("soup", 30.00, 1000),
                new Menu("beef", 100.00, 5000), new Menu("chicken", 60.00, 2000));

        //错误示例 返回的是Stream<T>
//        int calories = menu.stream()
//                            .map(Menu::getCaloric)
//                            .sum();

        int calories = menu.stream().mapToInt(Menu::getCaloric).sum();

        OptionalInt max = menu.stream().mapToInt(Menu::getCaloric).max();
        int maxResult = max.orElse(1);

        //range 不包含100
        IntStream rangeStream2 = IntStream.range(1, 100).filter(n -> n % 2 == 0);
        //rangeClosed 包含100
        IntStream rangeStream = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(rangeStream.count());
    }
}
