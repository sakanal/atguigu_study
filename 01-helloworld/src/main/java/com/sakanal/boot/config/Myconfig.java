package com.sakanal.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.sakanal.boot.bean.Car;
import com.sakanal.boot.bean.Pet;
import com.sakanal.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 *  1、配置类里面使用@Bean标注在方法上给容器注册组件，默认是单实例的
 *  2、配置类本身也是组件
 *  3、proxyBeanMethods：代理Bean的方法
 *      Full    @Configuration(proxyBeanMethods = true)     【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 *      Lite    @Configuration(proxyBeanMethods = false)    【每个@Bean方法被调用多少次返回的组件都是新创建的】
 *      组件依赖必须使用Full模式默认。其他默认是否Lite模式
 *
 *  4、@Import({User.class, DBHelper.class})
 *      给容器中自动创建出这两个类型的组件，默认组件名字是全类名
 *
 *  5、@ImportResource("classpath:beans.xml")
 *      导入Spring的配置文件
 */
@Import({User.class, DBHelper.class})
//@ConditionalOnBean(name = "user")   //在这个类中的组件进行注册之前，确认容器中是否存在name=tom的组件，存在才会对这个类下的组件进行注册
//在实体类中使用@Component注解，可以在容器中注册name=user的组件
@ConditionalOnBean(name = "user")
@ImportResource("classpath:beans.xml")
//@EnableConfigurationProperties(Car.class)
//引用第三方时可用
//1、开启Car配置绑定功能
//2、把这个组件自动注册到容器中   在实体类中可以不用加上@Component注解
@Configuration(proxyBeanMethods = true)  //告诉SpringBoot这是一个配置类==配置文件
public class Myconfig {

    /**
     *  外部无论对配置类中的这个组件注册方法调用多少次，获取的都是之前注册在容器中的单实例对象
     * @return
     */
//    @ConditionalOnBean(name = "tom")    //注意组件顺序，依赖的组件要在该组件之前，否则不会注册组件
    @Bean   //给容器中添加组件，以方法名作为组件的id，返回类型就是组件类型。返回值就是组件在容器中的实例
    public User user01(){
        User user = new User("zhangsan", 18);
        user.setPet(tomcatPet());
        return user;
    }

    @Bean("tom")
    public Pet tomcatPet(){
        return new Pet("tomcat");
    }
}
