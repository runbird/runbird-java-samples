package com.scy.java8.lambda.type;

/**
 * 类名： TypeDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2018/7/11 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */


@FunctionalInterface
interface IMathDao {
    int doMath(int x, int y);
}

@FunctionalInterface
interface IMathDao2 {
    int doMath2(int x, int y);
}


public class TypeDemo {

    public static void main(String[] args) {

        //变量类型定义
        IMathDao mathDao = (x, y) -> x + y;

        //数组里定义
        IMathDao[] mathDaos = {(x, y) -> x + y};

        //强转
        Object mathDao2 = (IMathDao) (x, y) -> x + y;

        //通过返回类型
        IMathDao createLambda = createLambda();

        //多数情况
        TypeDemo demo = new TypeDemo();
        //没有重载情况
        // demo.test((x, y) -> x + y);
        //有重载的时候，进行强转
        demo.test((IMathDao2) (x, y) -> x + y);

    }

    //多数情况
    public void test(IMathDao mathDao) {
    }

    //demo.test((x, y) -> x + y); 会报错，有两重性，不知道匹配哪个，需要强转换
    public void test(IMathDao2 mathDao2) {
    }

    public static IMathDao createLambda() {
        return (x, y) -> x + y;
    }
}
