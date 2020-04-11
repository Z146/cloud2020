package com.wuhua.springcloud.service.impl;

import com.wuhua.springcloud.dao.PaymentDao;
import com.wuhua.springcloud.entity.Payment;
import com.wuhua.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        int result = paymentDao.create(payment);

        return result;
    }

    @Override
    public Payment getPaymentById(Long id) {
        Payment payment = paymentDao.getPaymentById(id);
        return payment;
    }
}
