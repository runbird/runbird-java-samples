package com.scy.basicpoint.threadlocal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名： ThreadLocalController <br>
 * 描述：执行 RequestHolder中的 get()方法  <br>
 * 创建日期： 2018/9/24 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping("/test")
    @ResponseBody //缺少会报错 @RestController代替
    public Long test() {
        return RequestHolder.getId();
    }
}
