package com.scy.java8.inaction.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 类名： LambdaDemo1 <br>
 * 描述：TODO <br>
 * 创建日期： 2020/8/13 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class LambdaDemo1 {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"), new Apple(120, "red"));

        filter(inventory, apple -> "green".equals(apple.getColor()));

        ApplePredicate predicate = i -> i.getWeight() == 155;
        filter(inventory, predicate);

        Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        inventory.sort(c);
        System.out.println(inventory);
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

    interface ApplePredicate {
        boolean test(Apple apple);
    }
}

