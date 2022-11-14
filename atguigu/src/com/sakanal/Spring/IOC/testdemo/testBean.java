package com.sakanal.Spring.IOC.testdemo;

import com.sakanal.Spring.IOC.usexml.bean.Employee;
import com.sakanal.Spring.IOC.usexml.bean.collection.Course;
import com.sakanal.Spring.IOC.usexml.bean.collection.Student;
import com.sakanal.Spring.IOC.usexml.bean.lifecycle.Orders;
import com.sakanal.Spring.IOC.usexml.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class testBean {
    /**
     * druid.properties--->JDBCUtils.java--->User.java--->UserDAO.java--->UserDAOImpl.java--->UserService.java
     * 外部bean
     * <property></property>
     *      name属性的设置需要在类中有对应的set方法
     *      value属性可以直接设置值
     *      ref属性可以用来引用其他bean
     */
    @Test
    public void testServiceAndDAO() throws SQLException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/usexml/Service&DAO.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        userService.getAll();
    }


    /**
     * 内部bean
     */
    @Test
    public void testBean1(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/usexml/Dept&Emp.xml");
        //内部bean
        Employee employee1 = classPathXmlApplicationContext.getBean("employee1", Employee.class);
        //级联赋值
        Employee employee2 = classPathXmlApplicationContext.getBean("employee2", Employee.class);
        //需要在Employee中生成Department属性的get方法
        //先生成外部bean中的department对象，后设置其属性
        //所以employee2和employee3中的department对象一致
        Employee employee3 = classPathXmlApplicationContext.getBean("employee3", Employee.class);

        //No bean named 'department' available
        //内部bean中的department无法独立获取
//        Department department = classPathXmlApplicationContext.getBean("department", Department.class);

        System.out.println(employee1);//Employee{name='lucy', sex='女', department=Department{name='研发部'}}
        System.out.println(employee2);//Employee{name='Tom', sex='男', department=Department{name='安保部'}}
        System.out.println(employee3);//Employee{name='Jark', sex='男', department=Department{name='安保部'}}
    }


    /**
     * 集合类型属性注入(array/List/map/set/List集合对象)
     * array      :   <array></array>    <list></list>
     * list       :   <list></list>
     * map        :   <map></map>
     * set        :   <set></set>
     * list集合对象 :   <list>  <ref></ref> 或   <bean></bean>   </list>
     */
    @Test
    public void testBean2(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/usexml/StudentCollection.xml");
        Student student = classPathXmlApplicationContext.getBean("student", Student.class);
        System.out.println(student);
    }


    /**
     * 抽取集合注入部分 util名称空间
     * （1）在spring配置文件中引入名称空间util
     *      xmlns:util="http://www.springframework.org/schema/util"
     *      http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd
     * （2）使用util标签完成list集合注入提取
     *      <util>   <bean></bean>   </util>
     */
    @Test
    public void testBean3(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/usexml/Student&Course.xml");
        Student student = classPathXmlApplicationContext.getBean("student", Student.class);
        System.out.println(student);
    }


    /**
     * 工厂bean
     * Spring有两种类型Bean，一种是普通bean，另外一种是工厂bean（FactoryBean）
     * 普遍bean：在配置文件中定义bean类型就是返回类型
     * 工厂bean：在配置文件中定义bean类型可以和返回类型不一样
     *      （1）创建类，让这个类作为工厂bean，实现接口FactoryBean
     *      （2）实现接口里面的方法，在实现的方法中定义返回的bean类型
     */
    @Test
    public void testBean4(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/usexml/FactoryBean.xml");
        Course course = classPathXmlApplicationContext.getBean("mybean", Course.class);
        System.out.println(course);
    }


    /**
     * bean 作用域
     * (1)在 Spring 里面，设置创建 bean 实例是单实例还是多实例
     * (2)在 Spring 里面，默认情况下，bean 是单实例对象
     *      com.sakanal.Spring.bean.collection.Course@52f759d7
     *      com.sakanal.Spring.bean.collection.Course@52f759d7
     * (3)如何设置单实例还是多实例
     *      在 spring 配置文件 bean 标签里面有属性（scope）用于设置单实例还是多实例
     *      scope 属性值
     *          第一个值 默认值，singleton，表示是单实例对象
     *          第二个值 prototype，表示是多实例对象
     *      com.sakanal.Spring.bean.collection.Course@69b0fd6f
     *      com.sakanal.Spring.bean.collection.Course@757942a1
     * (4)singleton 和 prototype 区别
     *      singleton 单实例，prototype 多实例
     *      设置 scope 值是 singleton 时候， 加载 spring 配置文件 时候就会创建单实例对象
     *      设置 scope 值是 prototype 时候，不是在加载 spring 配置文件时候创建 对象，在调用  getBean 方法时候创建多实例对象
     *
     * 在单实例对象中，对course1进行修改，course2也会改变
     */
    @Test
    public void testBean5(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/usexml/FactoryBean.xml");
        Course course1 = classPathXmlApplicationContext.getBean("mybean", Course.class);
        Course course2 = classPathXmlApplicationContext.getBean("mybean", Course.class);
        System.out.println(course1);
        System.out.println(course2);
    }


    /**
     * bean生命周期
     * 1、生命周期
     *  （1）从对象创建到对象销毁的过程
     * 2、bean 生命周期
     *  （1）通过构造器创建 bean 实例（无参数构造）
     *  （2）为 bean 的属性设置值和对其他 bean 引用（调用 set 方法）
     *  （3）调用 bean 的初始化的方法（需要进行配置初始化的方法）
     *  （4）bean 可以使用了（对象获取到了）
     *  （5）当容器关闭时候，调用 bean 的销毁的方法（需要进行配置销毁的方法）
     *
     *
     *  bean 的后置处理器，bean 生命周期有七步
     *  1、生命周期
     *  （1）通过构造器创建 bean 实例（无参数构造）
     *  （2）为 bean 的属性设置值和对其他 bean 引用（调用 set 方法）
     *  （3）把 bean 实例传递 bean 后置处理器的方法 postProcessBeforeInitialization
     *  （4）调用 bean 的初始化的方法（需要进行配置初始化的方法）
     *  （5）把 bean 实例传递 bean 后置处理器的方法 postProcessAfterInitialization
     *  （6）bean 可以使用了（对象获取到了）
     *  （7）当容器关闭时候，调用 bean 的销毁的方法（需要进行配置销毁的方法）
     */
    @Test
    public void testBean6(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/usexml/LifeCycle.xml");
        Orders orders = classPathXmlApplicationContext.getBean("orders", Orders.class);
        System.out.println("第四步 获取创建 bean 实例对象");
        System.out.println(orders);
        //手动让 bean 实例销毁
        classPathXmlApplicationContext.close();
    }


    /**
     * 自动装配
     */
    @Test
    public void testBean7(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("com/sakanal/Spring/IOC/xmlfile/usexml/AutoWire.xml");
        com.sakanal.Spring.IOC.usexml.bean.autowire.Employee employee = classPathXmlApplicationContext.getBean("employee", com.sakanal.Spring.IOC.usexml.bean.autowire.Employee.class);
        System.out.println(employee);
    }
}
