package com.sakanal.Spring.AOP.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//增强的类
@Component
@Aspect     //生成代理对象
@Order(3)   //在增强类上面添加注解 @Order(数字类型值)，数字类型值越小优先级越高
public class UserProxy {

    //相同切入点抽取
    @Pointcut(value = "execution(* com.sakanal.Spring.AOP.annotation.User.add(..))")
    public void pointdemo(){}



    //前置通知
    //切入点表达式作用：知道对哪个类里面的哪个方法进行增强
    //语法结构：execution([权限修饰符] [返回类型] [类全路径] [方法名称]([参数列表]) )
    //举例 1：对 com.atguigu.dao.BookDao 类里面的 add 进行增强
    //      execution(* com.atguigu.dao.BookDao.add(..))
    //举例 2：对 com.atguigu.dao.BookDao 类里面的所有的方法进行增强
    //      execution(* com.atguigu.dao.BookDao.* (..))
    //举例 3：对 com.atguigu.dao 包里面所有类，类里面所有方法进行增强
    //      execution(* com.atguigu.dao.*.* (..))

    //@Before注解表示作为前置通知
    @Before("pointdemo()")
    public void before(){
        System.out.println("before...");
    }

    //最终通知（在原方法执行之后执行，不管有没有异常都执行）
    @After("execution(* com.sakanal.Spring.AOP.annotation.User.add())")
    public void after(){
        System.out.println("after...");
    }

    //后置通知（在方法返回值之后执行，有异常时不执行，也叫放回通知）
    @AfterReturning("execution(* com.sakanal.Spring.AOP.annotation.User.add())")
    public void afterReturning(){
        System.out.println("afterReturning...");
    }

    //异常通知（在原方法出现异常时执行，在around（after）之前执行，在after之后执行，执行后结束）
    @AfterThrowing("execution(* com.sakanal.Spring.AOP.annotation.User.add())")
    public void afterThrowing(){
        System.out.println("afterThrowing...");
    }

    //环绕通知
    @Around("execution(* com.sakanal.Spring.AOP.annotation.User.add())")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕之前.........");//被增强的方法执行
        proceedingJoinPoint.proceed();
        System.out.println("环绕之后.........");
    }
}
