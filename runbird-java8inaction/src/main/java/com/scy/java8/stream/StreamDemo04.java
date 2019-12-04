package com.scy.java8.stream;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类名： StreamDemo04 <br>
 * 描述：非短路流和短路流 <br>
 * 创建日期： 2018/8/29 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class StreamDemo04 {

    public static void main(String[] args) {

        String str = "hello my world!";

        //IntStream chars = str.chars();
        //并行流  parallel()  forEach()
        str.chars().parallel().forEach(i -> System.out.println(i)); // 数字 乱序
        str.chars().parallel().forEach(i -> System.out.println((char) i));//字母 乱序
        System.out.println("<========");
        // 有序    parallel()  forEachOrdered()
        str.chars().parallel().forEachOrdered(i -> System.out.println((char) i));//字符顺序
        System.out.println("<========");

        //收集成list !!!!!  collect(Collectors.toList())
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println("<========" + list.toString());

        //reduce 拼接字符串
        Optional<String> reduce = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
        //Optional .get  || orElse==>if(null or "") return @@
        System.out.println("<========" + reduce.get() + "@@" + reduce.orElse("@@"));

        //reduce("",(a1,a2)->)
        String reduce2 = Stream.of(str.split(" ")).reduce("", (s1, s2) -> s1 + "|" + s2);
        System.out.println("<========" + reduce2);

        //reduce(0,(s1,s2)) map
        Integer reduce3 = Stream.of(str.split(" ")).map(s -> s.length()).reduce(0, (s1, s2) -> s1 + s2);
        System.out.println("<========map " + reduce3);

        //max 取得最长的值
        Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> s1.length() - s2.length());
        System.out.println("<========max " + max.orElse("@@"));

        //短路操作 any
        OptionalInt first = new Random().ints().findFirst();
        System.out.println("<========Short Circuit" + first.getAsInt());
    }
}
