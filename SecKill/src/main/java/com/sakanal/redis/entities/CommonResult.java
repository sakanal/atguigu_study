package com.sakanal.redis.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private int code;
    private String message;
    private T Data;

    public CommonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
