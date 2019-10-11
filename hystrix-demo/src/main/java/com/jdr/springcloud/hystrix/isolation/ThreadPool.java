package com.jdr.springcloud.hystrix.isolation;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 线程池隔离
 *
 * @author zhoude
 * @date 2019/10/8 9:46
 */
public class ThreadPool extends HystrixCommand<String> {

    private String threadName;

    public ThreadPool(String threadName) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HystrixGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("HystrixCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HystrixThreadPoolKey"))
                // 设置线程超时时间
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000))
                // 设置核心线程
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(3)));
        this.threadName = threadName;
    }

    @Override
    protected String run() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println(this.threadName + " into run...");
        return this.threadName;
    }

    @Override
    protected String getFallback() {
        System.err.println(this.threadName + " into fallback...");
        return this.threadName;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        syncThreadPool();
        asyncThreadPool();
        observeThreadPool();
    }

    /**
     * 异步调用
     */
    private static void syncThreadPool() throws ExecutionException, InterruptedException {
        ThreadPool threadPool = new ThreadPool("线程sync");
        Future<String> queue = threadPool.queue();
        String result = queue.get();
        System.out.println(Thread.currentThread() + " result: " + result);
    }

    /**
     * 同步调用
     */
    private static void asyncThreadPool() {
        ThreadPool threadPool = new ThreadPool("线程async");
        String result = threadPool.execute();
        System.out.println(Thread.currentThread() + " result: " + result);
    }

    /**
     * 响应式调用
     */
    private static void observeThreadPool() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool("线程observe");
        Observable<String> observe = threadPool.observe();
        System.out.println("哈哈哈，怎么了，还没完成吗？");
        observe.subscribe(call -> System.out.println(Thread.currentThread() + " result: " + call));
        // 线程休眠，等待observe完成
        TimeUnit.SECONDS.sleep(3);
    }
}
