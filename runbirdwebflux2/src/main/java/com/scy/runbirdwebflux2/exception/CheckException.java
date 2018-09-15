package com.scy.runbirdwebflux2.exception;

import lombok.Data;

/**
 * 类名： CheckException <br>
 * 描述：校验逻辑 <br>
 * 创建日期： 2018/9/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Data
public class CheckException extends RuntimeException {

    //出错字段的名字
    private String filedName;
    //出错字段的值
    private String filedVlaue;

    public CheckException() {
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CheckException(String filedName, String filedVlaue) {
        super();
        this.filedName = filedName;
        this.filedVlaue = filedVlaue;
    }
}
