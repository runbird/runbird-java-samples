package com.scy.basicpoint.threadlocal.demo;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

/**
 * 类名： ThreadLocalDemo2 <br>
 * 描述：TODO <br>
 * 创建日期： 2019/1/31 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
public class ThreadLocalDemo2 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String[] objects = {"a", "b", "c", "d", null};
        getSize(objects);

        Object[] entrys = getEntrys();
        log.info("初始化threadLocalMap的entrys数量为 {}", getSize(entrys));

        ThreadLocal<String> userId = ThreadLocal.withInitial(() -> "initId");
        userId.set("id in main thread");

        log.info("设置userId后threadLocalMap的entrys数量为 {}" + getSize(entrys));

        // 失去Threadlocal对象的强引用，并且尝试调用gc回收
        userId = null;
        System.gc();
        log.info("userId置null后threadLocalMap的entrys数量为 {}" + getSize(entrys));


    }

    // 获取数组中非null元素的个数
    private static long getSize(Object[] objects) {
        return Arrays.stream(objects).filter(Objects::nonNull).count();
    }

    //通过反射获得ThreadLocalMap中的底层数组
    private static Object[] getEntrys() throws NoSuchFieldException, IllegalAccessException {
        Thread mainThread = Thread.currentThread();
        //  PropertyDescriptor descriptor = new PropertyDescriptor("threadLocals", Thread.class);
        //  descriptor.getReadMethod().getAnnotatedReturnType();

        /* ThreadLocal values pertaining to this thread. This map is maintained by the ThreadLocal class. */
        //  ThreadLocal.ThreadLocalMap threadLocals = null;
        Field threadLocals = Thread.class.getDeclaredField("threadLocals");
        threadLocals.setAccessible(true);
        Object threadLocalMap = threadLocals.get(mainThread);
        Class<?>[] classes = ThreadLocal.class.getDeclaredClasses();
        Field table = classes[0].getDeclaredField("table");
        table.setAccessible(true);
        return (Object[]) table.get(threadLocalMap);
    }


}
