package com.jdr.springcloud.hystrix.isolation;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

import java.util.concurrent.TimeUnit;

/**
 * 信号量隔离
 *
 * @author zhoude
 * @date 2019/10/11 14:41
 */
public class Semaphore extends HystrixCommand<String> {

    private String threadName;

    public Semaphore(String threadName) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HystrixGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("HystrixCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HystrixThreadPoolKey"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        // 设置策略为信号量
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        // 设置线程超时时间
                        .withExecutionTimeoutInMilliseconds(1000)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(3)));
        this.threadName = threadName;
    }

    @Override
    protected String run() throws Exception {
        // 加上这行代码使之超时，走到com.jdr.springcloud.hystrix.isolation.ThreadPool.getFallback
        TimeUnit.SECONDS.sleep(2);
        System.out.println(this.threadName + " into run...");
        return this.threadName;
    }

    @Override
    protected String getFallback() {
        System.err.println(this.threadName + " into fallback...");
        return this.threadName;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            asyncThreadPool();
        }
    }

    /**
     * 同步调用
     */
    private static void asyncThreadPool() {
        Semaphore threadPool = new Semaphore("线程async");
        String result = threadPool.execute();
        System.out.println(Thread.currentThread() + " result: " + result);
    }
}
