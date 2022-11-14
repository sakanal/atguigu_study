package com.sakanal.springcloud.service.impl;

import com.sakanal.springcloud.dao.PaymentMapper;
import com.sakanal.springcloud.entities.Payment;
import com.sakanal.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentMapper paymentMapper;
    @Override
    public Payment getPaymentById(Integer id) {
        if (id!=null){
         return paymentMapper.getPaymentById(id);
        }
        return null;
    }
}
