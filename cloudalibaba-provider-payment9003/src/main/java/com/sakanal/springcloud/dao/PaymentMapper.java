package com.sakanal.springcloud.dao;

import com.sakanal.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {
    public Payment getPaymentById(@Param("id") Integer id);
}
