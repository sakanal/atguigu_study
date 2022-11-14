package com.sakanal.springcloud.service;

import com.sakanal.springcloud.entities.CommonResult;
import com.sakanal.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Integer id) {
        Long longId= Long.valueOf(id);
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(longId,"errorSerial"));
    }
}
