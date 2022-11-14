package com.sakanal.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA()
    {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        log.info(Thread.currentThread().getName()+"\t"+"...testB");
        return "------testB";
    }
    @GetMapping("/testC")
    public String testC()
    {
        try {
            log.info("sleep start");
            TimeUnit.MILLISECONDS.sleep(1500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        log.info("sleep over");
        return "------testC";
    }
    @GetMapping("/testD")
    public String testD()
    {
        int random = (int)(Math.random()*10+1);
        if (random>2) {
            int a = 10 / 0;
        }
//        String message="error";
//        if (random>2)
//            message="success";
//        log.info(String.valueOf(Math.round(Math.random())));
        return "------testD ";
    }
    @GetMapping("/testE")
    public String testE()
    {
        int random = (int)(Math.random()*10+1);
        log.info(String.valueOf(random));
        if (random<=2) {
            int a = 10 / 0;
        }
        return "------testE ";
    }
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler/*兜底方法*/ = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        //int age = 10/0;
        return "------testHotKey";
    }

    /*兜底方法*/
    public String deal_testHotKey (String p1, String p2, BlockException exception) {
        return "------deal_testHotKey,o(╥﹏╥)o";  //sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
    }
}