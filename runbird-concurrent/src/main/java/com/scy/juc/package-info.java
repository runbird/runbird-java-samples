
package com.scy.juc;

/*
 * J.U.C  线程安全-并发容器
 * ArrayList-->CopyOnWriteArrayList ：
 *       1.读写分离；2.最终一致性；3.使用时另外开辟空间 解决并发冲突
 *        读：原数组不加锁，写：加锁
 *        优点：缺点：
 * HashSet、TreeSet --> CopyOnWriteArraySet   ConcurrentSkipListSet(removeAll addAll 需要手动加锁，能够保证单词原子性，但是不能保证批量)
 * HashMap、TreeMap --> ConcurrentHashMap   ConcurrentSkipListMap(key有序，支持更高的并发，线程越多并发越强)
 * */