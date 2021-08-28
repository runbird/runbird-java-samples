package com.scy.java8.lambda.basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JoingDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("foo", "foo1", "foo2");
        String result = list.stream().collect(Collectors.joining("|"));
        String result2 = String.join("|", list);
        System.out.println(result + " :: " + result2);

        String ans = list.stream().collect(Collectors.joining("|", "[", "]"));
        System.out.println(ans);

        String str = Stream.of("aaa", "bbb", "ccc").collect(Collectors.joining(",", "{", "}"));
        System.out.println(str);
    }
}

