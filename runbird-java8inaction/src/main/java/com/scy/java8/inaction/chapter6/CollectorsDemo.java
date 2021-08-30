package com.scy.java8.inaction.chapter6;

import com.scy.chapter4.Menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 类名： CollectorsDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2020/8/26 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class CollectorsDemo {

    public static void main(String[] args) {

        List<Menu> menu = Arrays.asList(new Menu("soup", 30.00, 1000),
                new Menu("beef", 100.00, 5000), new Menu("chicken", 60.00, 2000));

        List<Menu> result = menu.stream().collect(Collectors.toList());

        Long menus = menu.stream().collect(Collectors.counting());

        String names = menu.stream().map(d -> d.getName()).collect(Collectors.joining());
        String names2 = menu.stream().map(d -> d.getName()).collect(Collectors.joining(","));

        Integer totalCaloric = menu.stream().collect(Collectors.reducing(0, Menu::getCaloric, (i, j) -> i + j));
        Optional<Menu> mostCaloric = menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getCaloric() > d2.getCaloric() ? d1 : d2));


    }
}
