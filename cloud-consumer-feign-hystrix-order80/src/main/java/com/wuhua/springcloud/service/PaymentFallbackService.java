package com.wuhua.springcloud.service;

public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService paymentInfo_OK";
    }

    @Override
    public String paymentInfo_error(Integer id) {
        return "PaymentFallbackService paymentInfo_error";
    }
}
