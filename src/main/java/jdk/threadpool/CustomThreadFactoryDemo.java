package jdk.threadpool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadFactoryDemo {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ": ThreadID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            MyTask task = new MyTask();
            ThreadPoolExecutor es = new ThreadPoolExecutor(
                5,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        // t.setDaemon(true);
                        System.out.println("create " + t);
                        return t;
                    }
                }
            );
            for (int i = 0; i < 5; i++) {
                es.submit(task);
            }
            Thread.sleep(2000);
        }
    }
}
