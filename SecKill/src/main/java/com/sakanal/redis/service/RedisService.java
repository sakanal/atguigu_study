package com.sakanal.redis.service;

import com.sakanal.redis.entities.CommonResult;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class RedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public CommonResult<String> doSecKill(String userId, String goodsId){
        //1、userId和goodsId的非空判断
        if (userId!=null && goodsId!=null){
            //2、拼接key   库存key，秒杀成功用户key
            String stockGoodsKey="stock:goodsId:"+goodsId;
            String secKillSuccessUserIdKey="secKill:goodsId:"+goodsId+":success:userId";
            //3、获取库存，如果库存本身等于null==》秒杀未开始
            //监视库存
            redisTemplate.setEnableTransactionSupport(true);
            redisTemplate.watch(stockGoodsKey);
            Integer stockValue =(Integer) redisTemplate.opsForValue().get(stockGoodsKey);
            if (stockValue!=null){
                //4、判断用户是否重复进行秒杀
                Boolean isMember = redisTemplate.opsForSet().isMember(secKillSuccessUserIdKey, userId);
                if (Boolean.FALSE.equals(isMember)){
                    //5、判断商品数量，库存数量<1，秒杀结束
                    if (stockValue>=1){
                        //6、秒杀过程    库存-1，记录秒杀成功用户
                        //防止超卖问题
                        redisTemplate.multi();
                        redisTemplate.opsForValue().decrement(stockGoodsKey);
                        redisTemplate.opsForSet().add(secKillSuccessUserIdKey,userId);
                        redisTemplate.exec();
                        return new CommonResult<>(200,"秒杀成功",userId);
                    }else {
                        return new CommonResult<>(444,"库存清空，秒杀结束",userId);
                    }
                }else {
                    return new CommonResult<>(444, "该用户已经完成秒杀",userId);
                }
            }else {
                return new CommonResult<>(444,"秒杀未开始",userId);
            }
        }else {
            return new CommonResult<>(444,"空值",userId);
        }
    }

    public void addStock(String goodsId,Integer goodsStock){
        String stockGoodsKey="stock:goodsId:"+goodsId;
        Integer realStock =(Integer) redisTemplate.opsForValue().get(stockGoodsKey);
        if (realStock==null){
            redisTemplate.opsForValue().set(stockGoodsKey,goodsStock);
        }else {
            redisTemplate.opsForValue().increment(stockGoodsKey,goodsStock);
        }
    }
}
