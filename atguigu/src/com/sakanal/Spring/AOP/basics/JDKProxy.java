package com.sakanal.Spring.AOP.basics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JDKProxy {
    /**
     *  1、使用 JDK 动态代理，使用 Proxy 类里面的方法创建代理对象
     *  调用 newProxyInstance 方法
     *      方法有三个参数：
     *      第一参数，类加载器
     *      第二参数，增强方法所在的类，这个类实现的接口，支持多个接口
     *      第三参数，实现这个接口 InvocationHandler，创建代理对象，写增强的部分
     *  2、编写 JDK 动态代理代码
     *      （1）创建接口，定义方法
     *      （2）创建接口实现类，实现方法
     *      （3）使用 Proxy 类创建接口代理对象
     */
    public static void main(String[] args) {
        //创建接口实现类代理对象
        Class[] interfaces={UserDAO.class};
        //InvocationHandler可以通过匿名内部类的方式实现
//        Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces , new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return null;
//            }
//        });
        UserDAOImpl userDAO = new UserDAOImpl();
        UserDAO dao = (UserDAO) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new UserDAOProxy(userDAO));
        int add = dao.add(1, 2);
        System.out.println("add = " + add);
    }
}

//创建代理对象代码
class UserDAOProxy implements InvocationHandler{

    //1、把创建的是谁的代理对象，把谁传递过来
    //有参构造传递
    private Object object;
    public UserDAOProxy(Object object){
        this.object=object;
    }

    //增强的逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //方法之前
        System.out.println("方法之前执行..."+method.getName()+":传递的参数..."+ Arrays.toString(args));

        //被增强的方法执行
        Object invoke = method.invoke(object, args);

        //方法之后
        System.out.println("方法之后执行..."+object);

        return invoke;
    }
}