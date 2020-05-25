package com.scy.juc;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名： TempUtil <br>
 * 描述：TODO <br>
 * 创建日期： 2019/1/31 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class TempUtil {
    /**
     * 使用泛型和反射 按照属性对集合进行分组
     *
     * @param list
     * @return 分组后的list集合
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws Exception
     * @author ssq
     */
    public static List<Object> groupByFiled(List<?> list, String filed)
            throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Map<String, List<Object>> resultMap = new HashMap<>();
        // 组装map集合
        for (Object bean : list) {
            PropertyDescriptor descriptor = new PropertyDescriptor(filed, bean.getClass());
            Method readMethod = descriptor.getReadMethod();
            // 获取当前对象属性里面的值用来分组
            String invoke = readMethod.invoke(bean).toString();
            // map中 invoke已存在，将该数据存放到同一个key的map中
            if (resultMap.containsKey(invoke)) {
                resultMap.get(invoke).add(bean);
            } else {// map中不存在，新建key，用来存放数据
                List<Object> tmpList = new ArrayList<>();
                tmpList.add(bean);
                resultMap.put(invoke, tmpList);
            }
        }

        // 遍历map集合组装返回的list集合
        // 获取key的set集合
        List<Object> arrayList = new ArrayList<>();
        resultMap.forEach((key,value)->{
            Map<String, Object> map = new HashMap<>();
            map.put("groupid", key);
            map.put("groupvalue", value);
            // 根据key获取value 组装map
            arrayList.add(map);
        });
        return arrayList;
    }
}
