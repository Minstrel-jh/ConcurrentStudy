package jdk.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));

        ExecutorService executor = Executors.newFixedThreadPool(1);

        executor.submit(future);

        System.out.println("请求完毕");

        System.out.println("数据 = " + future.get());

    }
}
