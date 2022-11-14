package com.sakanal.boot;

import ch.qos.logback.core.db.DBHelper;
import com.sakanal.boot.bean.Pet;
import com.sakanal.boot.bean.User;
import com.sakanal.boot.config.Myconfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *  主程序类
 *  @SpringBootApplication：表示为一个springboot应用
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        //1、返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2、查看容器里的组件
//        String[] names = run.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }

        /*
        //3、从容器中获取组件
        User user01 = run.getBean("user01", User.class);
        User user02 = run.getBean("user01", User.class);
        System.out.println("组件："+(user01==user02));//组件：true

        Myconfig bean = run.getBean(Myconfig.class);
        System.out.println(bean);//com.sakanal.boot.config.Myconfig$$EnhancerBySpringCGLIB$$a66483f2@7c0da600

        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法。springBoot总会检查这个组件是否在容器中有
        //保持组件单实例
        User user03 = bean.user01();
        User user04 = bean.user01();
        //true:@Configuration(proxyBeanMethods = true)
        //false:@Configuration(proxyBeanMethods = false)
        System.out.println(user03==user04);

        User user05 = run.getBean("user01", User.class);
        Pet pet = run.getBean("tom", Pet.class);
        //true:@Configuration(proxyBeanMethods = true)
        //false:@Configuration(proxyBeanMethods = false)
        System.out.println(user05.getPet()==pet);

        System.out.println("================================");
        //@Import({User.class, DBHelper.class})
        String[] names = run.getBeanNamesForType(User.class);
        for (String name : names) {
            //com.sakanal.boot.bean.User        @Import({User.class, DBHelper.class})
            //user01                            public User user01()
            System.out.println(name);
        }
        DBHelper dbHelper = run.getBean(DBHelper.class);
        System.out.println(dbHelper);//ch.qos.logback.core.db.DBHelper@11a82d0f
        */

        /*
        //@ConditionalOnBean(name="user")  //存在name=user的组件才会进行注册
        boolean user01 = run.containsBean("user01");
        System.out.println("容器中User组件 = " + user01);
        boolean tom = run.containsBean("tom");
        System.out.println("容器中Tomcat组件 = " + tom);
        boolean user = run.containsBean("user");
        System.out.println("容器中user组件 = " + user);
        */

        //@ImportResource("classpath:beans.xml")
        boolean haha = run.containsBean("haha");
        boolean hehe = run.containsBean("hehe");
        System.out.println("容器中haha组件 = " + haha);
        System.out.println("容器中hehe组件 = " + hehe);
    }
}
