package com.wuhua.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    //======= 服务降级 =======//

    public String paymentInfo_OK(Integer id){
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_OK"+"----"+id;
    }

    // 超时, 异常, 服务熔断导致服务降级 , 线程池满
    @HystrixCommand(fallbackMethod = "paymentInfo_errorHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "300")
    })
    public String paymentInfo_error(Integer id){

        try {
            TimeUnit.MILLISECONDS.sleep(400);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_error"+"----"+id+"出错了";
    }
    public String paymentInfo_errorHandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_errorHandler";
    }


    //======= 服务熔断 =======//
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallBreak" , commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" ,value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        String result = IdUtil.simpleUUID();
        return result;
    }

    public String paymentCircuitBreaker_fallBreak(Integer id){
        return "id 不能为负数,服务熔断测试";
    }
}
