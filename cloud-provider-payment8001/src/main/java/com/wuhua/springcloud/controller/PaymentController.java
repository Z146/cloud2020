package com.wuhua.springcloud.controller;

import com.wuhua.springcloud.common.CommonResult;
import com.wuhua.springcloud.entity.Payment;
import com.wuhua.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("payment/create")
    public CommonResult creat(@RequestBody Payment payment) {
        int resultFlag = paymentService.create(payment);

        if (resultFlag > 0) {
            return new CommonResult(200, "插入数据库成功"+serverPort, resultFlag);
        } else {
            return new CommonResult(500, "插入数据库失败");
        }
    }

    @GetMapping("payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);

        if (payment != null) {
            return new CommonResult(200, "查询成功"+serverPort, payment);
        } else {
            return new CommonResult(500, "查询失败,没有本条记录");
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("***** element *****"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        log.info("开始执行方法^^^^");
        return "paymentZipkin come on!";
    }
}
