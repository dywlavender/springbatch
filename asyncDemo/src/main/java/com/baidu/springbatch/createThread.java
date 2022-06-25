package com.baidu.springbatch;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName createThread
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/25 20:56
 * @Version 1.0
 **/
public class createThread {
    public static void main(String[] args) {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        };
        FutureTask futureTask = new FutureTask(callable);

    }
}
