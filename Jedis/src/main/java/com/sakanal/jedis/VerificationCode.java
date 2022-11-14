package com.sakanal.jedis;

import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class VerificationCode {
    public static void main(String[] args){

        //VerifyCode("18870493682");
        Verification("18870493682","947327");
    }

    static String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    //随机生产六位数字验证码
    public static String getCode(){
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i=0;i<6;i++){
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    //获取当天结束的秒数
    public static String getToTomorrowNeedSecond(){
        long now = System.currentTimeMillis();
        SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
        long overTime = 0;
        try {
            overTime = (now - (sdfOne.parse(sdfOne.format(now)).getTime()))/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(24*60*60-overTime);
    }

    //存储到redis
    public static void VerifyCode(String phone){
        Jedis jedis = new Jedis("192.168.38.129",6379);

        String countPhone=today+"Count"+phone;
        String count = jedis.get(countPhone);
        if (count == null){
            jedis.setex(countPhone,Long.parseLong(getToTomorrowNeedSecond()),"1");
        }else if (Integer.parseInt(count)<=2){
            jedis.incr(countPhone);
        }else {
            System.out.println("今天验证码发送次数已经超过三次");
            jedis.close();
            return;
        }

        String codePhone=today+"Code"+phone;
        jedis.setex(codePhone,120L,getCode());
        jedis.close();
    }

    //验证验证码
    public static void Verification(String phone,String code){
        Jedis jedis = new Jedis("192.168.38.129",6379);
        String codePhone=today+"Code"+phone;
        String realCode = jedis.get(codePhone);
        if (realCode!=null){
            if (realCode.equals(code)){
                System.out.println("成功");
            }else {
                System.out.println("失败");
            }
        }else {
            System.out.println("验证码已过期");
        }
    }
}
