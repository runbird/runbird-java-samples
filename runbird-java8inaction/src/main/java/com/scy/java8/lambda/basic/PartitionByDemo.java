package com.scy.java8.lambda.basic;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartitionByDemo {

    public static void main(String[] args) {
        Map<Boolean, List<String>> map =
                Stream.of("foo", "foo1", "fooo1").collect(Collectors.partitioningBy(x -> x.length() > 4));

        Set<Map.Entry<Boolean, List<String>>> entries = map.entrySet();
        for (Map.Entry<Boolean, List<String>> entry : entries) {
            System.out.println(entry.getKey() + " :" + entry.getValue());
        }

    }
}
