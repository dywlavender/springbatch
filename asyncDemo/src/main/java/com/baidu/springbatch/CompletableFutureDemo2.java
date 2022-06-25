package com.baidu.springbatch;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName CompletableFutureDemo2
 * @Description TODO
 * @Author dlavender
 * @Date 2022/6/26 0:02
 * @Version 1.0
 **/
public class CompletableFutureDemo2 {
    public static void main(String[] args) {
        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> {
            System.out.println("厨师做菜中");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "番茄";
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("煮饭");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "米饭";
        }), (dish, rice) -> {
            System.out.println("打饭");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return dish + "-" + rice;
        });
        System.out.println(f.join());

        CompletableFuture.supplyAsync(()->{
          return null;
        }).thenCombineAsync();

        CompletableFuture.supplyAsync().thenCombineAsync(
                CompletableFuture.supplyAsync().thenCombineAsync(CompletableFuture.supplyAsync()))
                .thenComponse().thenCombine()

    }
}
