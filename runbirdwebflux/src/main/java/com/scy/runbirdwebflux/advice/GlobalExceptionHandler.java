package com.scy.runbirdwebflux.advice;

import com.scy.runbirdwebflux.exception.CheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * 类名： GlobalExceptionHandler <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/9 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handlerBindException(WebExchangeBindException exception){
        return new ResponseEntity<>(toStr(exception),HttpStatus.BAD_REQUEST);
    }
    //捕获check的异常
    @ExceptionHandler(CheckException.class)
    public ResponseEntity<String> handlerCheckException(CheckException exception){
        return new ResponseEntity<>(toStr(exception),HttpStatus.BAD_REQUEST);
    }

    private String toStr(CheckException exception) {
        return exception.getFiledName()+"error message: "+exception.getFiledVlaue();
    }

    //将对象-->数组-->多行-->一行
    private String toStr(WebExchangeBindException exception) {
        return exception.getFieldErrors().stream()
                //将异常转化为字符串   对象-->字符串数组
                .map(e->e.getField()+":"+e.getDefaultMessage())
                //数组转字符串会调用reduce  多行-->一行
                .reduce("",(s1,s2)->s1+"\n"+s2);
    }
}
