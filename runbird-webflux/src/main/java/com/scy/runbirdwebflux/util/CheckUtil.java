package com.scy.runbirdwebflux.util;

import com.scy.runbirdwebflux.exception.CheckException;

import java.util.stream.Stream;

/**
 * 类名： CheckUtil <br>
 * 描述：校验类工具 <br>
 * 创建日期： 2018/9/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class CheckUtil {

    //定义不符合规则的名字
    private static final String[] INVALID_NAMES={"admin","guanliyuan","freedom"};


    /**
     * 检验名字，不符合规则抛出运行时异常
     * @param value
     */
    public static void checkName(String value) {
        Stream.of(INVALID_NAMES).filter(name->name.equalsIgnoreCase(value))
                //找到任何一个错误就退出
                .findAny()
                //抛出运行时异常
                .ifPresent(name->{
                    throw new CheckException("name", value);
                });
    }
}
