package com.wuhua.springcloud.controller;

import com.wuhua.springcloud.common.CommonResult;
import com.wuhua.springcloud.entity.Payment;
import com.wuhua.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){
        CommonResult<Payment> result = paymentService.getPaymentById(id);
        return result;
    }
}
