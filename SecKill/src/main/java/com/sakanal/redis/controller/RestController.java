package com.sakanal.redis.controller;

import com.sakanal.redis.entities.CommonResult;
import com.sakanal.redis.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.Random;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Resource
    private RedisService redisService;

    @RequestMapping("/getUserId")
    public String getUserId(){
        return String.valueOf(new Random().nextInt(10000));
    }
    @RequestMapping("/secKill")
    public CommonResult<String> secKill(@RequestParam(value = "userId",required = false)String userId,
                                        @RequestParam("goodsId")String goodsId){

        CommonResult<String> result = null;
        if (userId!=null && !userId.equals("")){
            result = redisService.doSecKill(userId, goodsId);
        }else {
            userId=String.valueOf(new Random().nextInt(10000));
            result = redisService.doSecKill(userId, goodsId);
        }
        System.out.println("userId:"+result.getData()+"  message"+result.getMessage());
        return result;
    }
    @RequestMapping("/addStock")
    public CommonResult<Boolean> addStock(@RequestParam("goodsId")String goodsId,
                                          @RequestParam("goodsStock")Integer goodsStock){
        if (goodsId!=null && !goodsId.equals("")){
            if (goodsStock!=null){
                redisService.addStock(goodsId, goodsStock);
                return new CommonResult<>(200,"添加库存成功");
            }else {
                return new CommonResult<>(444,"商品库存未输入");
            }
        }else {
            return new CommonResult<>(444,"商品未选择");
        }
    }
}
