package com.sakanal.springcloud.service;

import com.sakanal.springcloud.entities.CommonResult;
import com.sakanal.springcloud.entities.Payment;

public interface PaymentService {
    public Payment getPaymentById(Integer id);
}
