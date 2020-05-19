package com.scy.basicpoint.utils;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 类名： RunbirdRejectHandler <br>
 * 描述：TODO <br>
 * 创建日期： 2019/10/30 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class RunbirdRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
        System.out.println("rejected task name : " + executor.toString());
    }
}
