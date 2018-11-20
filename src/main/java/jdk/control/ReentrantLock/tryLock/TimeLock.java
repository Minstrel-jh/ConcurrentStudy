package jdk.control.ReentrantLock.tryLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getId() + ": get lock failed");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getId() + ": 收到中断");
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                System.out.println(Thread.currentThread().getId() + ": 释放锁");
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ": 线程退出");
        }
    }

    public static void main(String[] args) {
        TimeLock tl = new TimeLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start();
        t2.start();
    }
}
