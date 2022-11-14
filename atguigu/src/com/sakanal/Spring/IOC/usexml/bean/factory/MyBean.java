//package com.sakanal.Spring.IOC.usexml.bean.factory;
//
//import com.sakanal.Spring.IOC.usexml.bean.collection.Course;
//import org.springframework.beans.factory.FactoryBean;
//
//public class MyBean implements FactoryBean<Course> {
//    @Override
//    //返回bean的实例
//    //定义返回bean
//    public Course getObject() throws Exception {
//        Course course = new Course();
//        course.setName("返回bean的实例测试");
//        return course;
//    }
//
//    @Override
//    //返回bean的类型
//    public Class<?> getObjectType() {
//        return null;
//    }
//
//    @Override
//    //是否是单例
//    public boolean isSingleton() {
//        return FactoryBean.super.isSingleton();
//    }
//}
