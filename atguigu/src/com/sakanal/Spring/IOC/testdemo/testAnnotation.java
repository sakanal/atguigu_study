package com.sakanal.Spring.IOC.testdemo;

import com.sakanal.Spring.IOC.useannotation.config.SpringConfig;
import com.sakanal.Spring.IOC.useannotation.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testAnnotation {

    /**
     *  1、什么是注解
     *      （1）注解是代码特殊标记，格式：@注解名称(属性名称=属性值, 属性名称=属性值..)
     *      （2）使用注解，注解作用在类上面，方法上面，属性上面
     *      （3）使用注解目的：简化 xml 配置
     *  2、Spring 针对 Bean 管理中创建对象提供注解
     *      （1）@Component       普通层组件
     *      （2）@Service         业务层组件
     *      （3）@Controller      控制层组件
     *      （4）@Repository      持久层组件
     * 上面四个注解功能是一样的，都可以用来创建 bean 实例
     *  3、基于注解方式实现对象创建
     *      第一步 引入依赖
     *      第二步 开启组件扫描
     *      第三步 创建类，在类上面添加创建对象注解
     *  4、开启组件扫描细节配置
     *      （1）示例一
     *           use-default-filters="false"     表示现在不使用默认 filter，自己配置 filter
     *           context:include-filter          设置扫描哪些内容
     *      （2）示例二
     *           context:exclude-filter          设置哪些内容不进行扫描
     *  5、基于注解方式实现属性注入
     *      （1）@Autowired：根据属性类型进行自动装配
     *          第一步 把 service 和 dao 对象创建，在 service 和 dao 类添加创建对象注解
     *          第二步 在 service 注入 dao 对象，在 service 类添加 dao 类型属性，在属性上面使用注解
     *      （2）@Qualifier：根据名称进行注入
     *          这个@Qualifier 注解的使用，和上面@Autowired 一起使用
     *      （3）@Resource：可以根据类型注入，可以根据名称注入
     *      （4）@Value：注入普通类型属性
     *  6、完全注解开发
     *      （1）创建配置类，替代 xml 配置文件
     *      （2）编写测试类
     */
    @Test
    public void test1(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/useannotation/userBean.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.add();
    }

    /**
     * 完全注解开发
     */
    @Test
    public void test2(){
        //加载配置类
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = annotationConfigApplicationContext.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.add();
    }
}
