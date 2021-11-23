package java0.conc0303;

import java.util.concurrent.CountDownLatch;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 方法1 CountDownLatchMethod 方法
 * 阻塞主线程 当N个线程满足条件时主线程继续
 */


public class CountDownLatchMethod {

    private volatile Integer value = null;
    private CountDownLatch latch;

    public void sum(int num) {
        // 调用方法
        value = fibo(num);
        // 等待数减去1
        latch.countDown();
    }

    // 方法
    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    public int getValue() throws InterruptedException {
        latch.await();
        return value;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        // 设置子线程数量为1 这个唯一的子线程执行完毕后就会返回去执行主线程
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatchMethod method = new CountDownLatchMethod();
        method.setLatch(latch);

        // 主函数里面创建另外一个线程来异步运行这个方法
        // 这个线程里面的内容执行完后线程就结束了
        Thread thread = new Thread(() -> {
            method.sum(3);
            System.out.println(Thread.currentThread().getName());
        });

        // 当用start()去启动这个线程的时候 才会去执行上面{}内的内容
        thread.start();

        // 得到返回值
        int result = method.getValue();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println(Thread.currentThread().getName());

        System.out.println("使用时间："+ (System.currentTimeMillis() - start) + "ms");

        // main线程内容全部执行完后就会退出main线程
    }
}

