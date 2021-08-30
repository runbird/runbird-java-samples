package com.scy.java8.inaction.chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 类名： LambdaDemo2 <br>
 * 描述：TODO <br>
 * 创建日期： 2020/8/21 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class LambdaDemo2 {

    //需要多次读取或者读取首尾比较麻烦
    //若需要将读取文件行为参数化
    //Step 1
    public String processFile() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("file.txt"))) {
            return br.readLine();
        }
    }

    //Step 2 接口传递行为
    @FunctionalInterface
    public interface BufferedReaderProcessor{
        String process(BufferedReader br) throws IOException;
    }

    //Step 3 执行第一个行为
    public static String processFile2(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("file.txt"))){
            return p.process(br);
        }
    }

    //Step4 传递lambda
    public static void main(String[] args) {
        String oneLine;
        String twoLine;
        try {

            oneLine = processFile2(BufferedReader::readLine);

            twoLine = processFile2((BufferedReader br) -> br.readLine() + br.readLine());

            BufferedReaderProcessor bp = (BufferedReader br) -> br.readLine();
            String result = processFile2(bp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

