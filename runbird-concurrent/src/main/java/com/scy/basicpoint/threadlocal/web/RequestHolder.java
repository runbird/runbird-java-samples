package com.scy.basicpoint.threadlocal.web;

/**
 * 类名： RequstHolder <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/24 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class RequestHolder   {
    //对 对象操作Long替换为Bean
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }
    //需要进行remove,防止内存溢出。
    public static void remove() {
        requestHolder.remove();
    }
}
