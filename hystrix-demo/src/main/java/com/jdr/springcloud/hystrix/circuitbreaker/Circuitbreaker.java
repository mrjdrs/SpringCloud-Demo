package com.jdr.springcloud.hystrix.circuitbreaker;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

import java.util.concurrent.TimeUnit;

/**
 * 断路器
 *
 * @author zhoude
 * @date 2019/10/11 14:57
 */
public class Circuitbreaker extends HystrixCommand<Integer> {

    private int number;

    public Circuitbreaker(int number) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(""))
                .andCommandKey(HystrixCommandKey.Factory.asKey(""))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(""))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(100))
                .andCommandPropertiesDefaults(
                        // 打开Hystrix断路器
                        HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true)
                                // 10秒至少有请求10次，且超过50%失败则打开断路器
                                .withMetricsRollingStatisticalWindowInMilliseconds(10000)
                                .withCircuitBreakerRequestVolumeThreshold(10)
                                .withCircuitBreakerErrorThresholdPercentage(50)
                                // 断路器休眠时间（断路器没执行5秒休息一次，知道再次满足上述配置）
                                .withCircuitBreakerSleepWindowInMilliseconds(5000)
                )
        );
        this.number = number;
    }

    @Override
    protected Integer run() throws Exception {
        System.out.println(number + " into run...");
        if (number % 2 == 0) {
            System.out.println("run normal" + number);
            return number;
        } else {
            // 死循环，模拟异常
            while (true) {
            }
        }
    }

    @Override
    protected Integer getFallback() {
        System.err.println(number + " into fallback...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return number;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Circuitbreaker circuitbreaker = new Circuitbreaker(i);
            circuitbreaker.execute();
        }
    }
}
