package com.scy.basicpoint.synchcontainer;

import java.util.Iterator;
import java.util.Vector;

/**
 * 类名： VectorDemo <br>
 * 描述：同步容器：Vector类三种遍历情况下的操作 <br>
 * 创建日期： 2018/9/28 <br>
 * 同步容器：ArrayList-->Vector Stack
 * HashMap-->HashTable(key,value不能为null)
 * Collections.sychronizedXXX(List,Set,Map)
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class VectorDemo {

    public static void main(String[] args) {
        final Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        func1(vector);
    }

    //java.util.ConcurrentModificationException
    private static void func1(Vector<Integer> vector) {
        for (Integer i : vector) {
            if (i.equals(3)) {
                vector.remove(i);
            }
        }
    }

    //java.util.ConcurrentModificationException
    private static void func2(Vector<Integer> vector) {
        final Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            final Integer i = iterator.next();
            if (i.equals(3)) {
                vector.remove(i);
            }
        }
    }

    private static void func3(Vector<Integer> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(i).equals(3)) {
                vector.remove(i);
            }
        }
    }

    //java.util.ConcurrentModificationException
    private static void func4(Vector<Integer> vector) {
        vector.stream().forEach(x -> {
            if (x.equals(3)) {
                vector.remove(x);
            }
        });
    }
}
