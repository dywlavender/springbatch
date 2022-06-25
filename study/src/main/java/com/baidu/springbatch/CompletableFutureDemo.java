package com.baidu.springbatch;

import java.util.concurrent.*;

/**
 * @ClassName CompletableFuture
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/21 22:02
 * @Version 1.0
 **/
public class CompletableFutureDemo {
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(5,50,10, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(100), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("任务1 子线程执行..." + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务1 子线程执行 end..." + Thread.currentThread().getName());
        },executor);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2 子线程执行..." + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务2 子线程执行 end..." + Thread.currentThread().getName());
            return 12;
        }, executor).handleAsync((res,exec)->{
            System.out.println("res=" + res);
            System.out.println("exec = " + exec);
            return 55;
        });
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务3 子线程执行..." + Thread.currentThread().getName());
            return 12;
        }, executor).thenApplyAsync((res)->{
            System.out.println("任务3 子线程执行..." + Thread.currentThread().getName() +":" + res);
            return res*100;
        },executor);
        System.out.println("主线程 end ...");
        System.out.println(future3.get());
        future1.runAfterBothAsync(future2,()->{
            System.out.println("任务7...");
        },executor);
        future3.thenCombineAsync(future2, (f1,f2)->{
            System.out.println("f1 = " + f1);
            System.out.println("f1:" + f1);
            System.out.println("f2:"+f2);return f1+f2;},executor);
        System.out.println("f3 : " + future3.get());
        //任意一个执行完成
        CompletableFuture<Integer> future = future3.applyToEitherAsync(future2, (res) -> {
            System.out.println(res);
            return 1000;
        }, executor);
        CompletableFuture<Void> f = CompletableFuture.allOf(future1, future2, future3, future);
        f.join();
        System.out.println("future:"+future.get());
        System.out.println("f = " + f);
    }
}
