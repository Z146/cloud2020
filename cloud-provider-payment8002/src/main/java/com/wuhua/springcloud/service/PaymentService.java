package com.wuhua.springcloud.service;

import com.wuhua.springcloud.entity.Payment;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}
