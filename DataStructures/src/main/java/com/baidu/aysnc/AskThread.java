package com.baidu.aysnc;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName AskThread
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/19 21:59
 * @Version 1.0
 **/
@AllArgsConstructor
public class AskThread implements Runnable{
    private CompletableFuture<Integer> re = null;
    @Override
    public void run() {
        int result = 0;
        try {
            result = re.get() * re.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws InterruptedException {
        // final CompletableFuture<Integer> future = new CompletableFuture<>();
        // new Thread(new AskThread(future)).start();
        // Thread.sleep(100000);
        // future.complete(60);
        WebAsyncTask<String> stringWebAsyncTask = new WebAsyncTask<>(3000, new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "call";
            }
        });
        stringWebAsyncTask.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                throw new TimeoutException("调用超时") ;
            }
        });
        stringWebAsyncTask.onCompletion(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行完毕");
            }
        });

    }
}
