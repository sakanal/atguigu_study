package com.sakanal.Spring.AOP.test;

import com.sakanal.Spring.AOP.configfile.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCofigFile {

    /**
     *  1、创建两个类，增强类和被增强类，创建方法
     *  2、在 spring 配置文件中创建两个类对象
     *  3、在 spring 配置文件中配置切入点
     *      配置 aop 增强
     *      <aop:config>
     *          切入点
     *          <aop:pointcut id="p" expression="execution(* com.sakanal.Spring.AOP.configfile.Book.buy(..))"/>
     *          配置切面
     *          <aop:aspect ref="bookProxy">
     *              增强作用在具体的方法上
     *              <aop:before method="before" pointcut-ref="p"/>
     *          </aop:aspect>
     *      </aop:config>
     */
    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/AOP/configfile/bean.xml");
        Book book = classPathXmlApplicationContext.getBean("book", Book.class);
        book.buy();
    }
}
