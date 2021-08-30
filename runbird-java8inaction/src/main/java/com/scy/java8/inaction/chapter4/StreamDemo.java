package com.scy.java8.inaction.chapter4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

//import static java.util.Comparator.comparing;

/**
 * 类名： StreamDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2020/8/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<Menu> menu = Arrays.asList(new Menu("soup", 30.00, 1000),
                new Menu("beef", 100.00, 5000), new Menu("chicken", 60.00, 2000));

        List<String> lowCaloric = menu.stream().filter(d->d.getCaloric() < 3000)
             //静态引入
                // .sorted(comparing(Menu::getCaloric))
                .sorted(Comparator.comparing(Menu::getCaloric))
                .map(Menu::getName)
                .collect(toList());

        lowCaloric.forEach(System.out::println);
    }
}
