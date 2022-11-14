package com.sakanal.Spring.AOP.test;

import com.sakanal.Spring.AOP.annotation.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TextAnnotation {

    /**
     *  1、创建类，在类里面定义方法
     *  2、创建增强类（编写增强逻辑）
     *      （1）在增强类里面，创建方法，让不同方法代表不同通知类型
     *  3、进行通知的配置
     *      （1）在 spring 配置文件中，开启注解扫描
     *          xmlns:context
     *          xmlns:aop
     *          <context:component-scan base-package=""/>
     *      （2）使用注解创建 User 和 UserProxy 对象
     *          1、@Component
     *          2、@Service
     *          3、@Controller
     *          4、@Repository
     *      （3）在增强类上面添加注解 @Aspect
     *      （4）在 spring 配置文件中开启生成代理对象
     *          <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
     *  4、配置不同类型的通知
     *      （1）在增强类的里面，在作为通知方法上面添加通知类型注解，使用切入点表达式配置
     *          1、@Before(value = "execution(* com.atguigu.spring5.aopanno.User.add(..))")
     *          2、@AfterReturning(value = "execution(*com.atguigu.spring5.aopanno.User.add(..))")
     *          3、@After(value = "execution(* com.atguigu.spring5.aopanno.User.add(..))")
     *          4、@AfterThrowing(value = "execution(*com.atguigu.spring5.aopanno.User.add(..))")
     *          5、@Around(value = "execution(* com.atguigu.spring5.aopanno.User.add(..))")
     *  5、相同的切入点抽取
     *          1、@Pointcut(value = "execution(* com.atguigu.spring5.aopanno.User.add(..))")
     *          2、public void pointdemo() {}
     *  6、有多个增强类多同一个方法进行增强，设置增强类优先级
     *      （1）在增强类上面添加注解 @Order(数字类型值)，数字类型值越小优先级越高
     *  7、完全使用注解开发
     *      （1）创建配置类，不需要创建 xml 配置文件
     */
    @Test
    public void test1(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/AOP/annotation/AOP.xml");
        User user = classPathXmlApplicationContext.getBean("user", User.class);
        user.add();
    }
}
