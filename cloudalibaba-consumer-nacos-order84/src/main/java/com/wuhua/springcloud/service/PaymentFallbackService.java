package com.wuhua.springcloud.service;

import com.wuhua.springcloud.common.CommonResult;
import com.wuhua.springcloud.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> PaymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回，----PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
