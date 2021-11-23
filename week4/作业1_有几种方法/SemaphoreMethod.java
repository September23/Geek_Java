package java0.conc0303;

import java.util.concurrent.Semaphore;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 方法3 Semaphore信号量 方法
 * 设置同一时间并发控制的线程数N
 */
public class SemaphoreMethod {

    private volatile Integer value = null;
    // 设置同一时间控制并发线程数为1
    private final Semaphore semaphore = new Semaphore(1);

    SemaphoreMethod () throws InterruptedException {
        semaphore.acquire();
    }

    public void sum(int num) throws InterruptedException {
        // Thread.sleep(5000);
        value = fibo(num);
        System.out.println(Thread.currentThread().getName());
        // 方法调用后释放锁
        semaphore.release();
    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    public int getValue() throws InterruptedException {
        int result;
        semaphore.acquire();
        result = this.value;
        semaphore.release();
        return result;
    }

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        final SemaphoreMethod method = new SemaphoreMethod();
        Thread thread = new Thread(() -> {
            try {
                method.sum(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        int result = method.getValue(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
        System.out.println(Thread.currentThread().getName());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

}