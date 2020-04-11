package com.wuhua.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wuhua.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_Global_handler")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")

    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String infoOk = paymentHystrixService.paymentInfo_OK(id);
        return infoOk;
    }

    @GetMapping("/consumer/payment/hystrix/error/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_errorHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "300")
    })
    public String paymentInfo_error(@PathVariable("id") Integer id){
        String infoError = paymentHystrixService.paymentInfo_error(id);
        return infoError;
    }
    public String paymentInfo_errorHandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"消费者 80 出错解决,paymentInfo_errorHandler";
    }

    // hystrix  的全局异常处理方法
    // 配合 @DefaultProperties(defaultFallback = "paymentInfo_Global_handler")
    // 实现 添加了 @HystrixCommand 但没有 fallbackMethod 的注解的解决方法
    // 有 fallbackMethod 的按自己定义的方法
    public String paymentInfo_Global_handler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"消费者 80 出错解决,paymentInfo_errorHandler";
    }
}
