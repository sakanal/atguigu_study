package com.sakanal.MyBatis.A_HelloWorld.test;

import com.sakanal.MyBatis.A_HelloWorld.bean.User;
import com.sakanal.MyBatis.A_HelloWorld.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 *  1、接口式编程
 *      原生：       DAO       ==>     DAOImpl
 *      myBatis：   Mapper    ==>     xxMapper.xml
 *  2、sqlSession代表和数据库的一次会话；用完必须关闭；
 *  3、sqlSession和connection一样，都是非线程安全，不可以写成【private SqlSessin sqlSession】。每次使用都应该去获取新对象
 *  4、Mapper接口没有实现类对象，但是MaBatis会为这个接口生成一个代理对象
 *          （将接口和mybatis-config.xml进行绑定）
 *          UserMapper userMapper=sqlSession.getMapper(UserMapper.class)
 *  5、两个重要的配置文件：
 *          MyBatis的全局配置文件（mybatis-config.xml）：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 *          sql映射文件（UserMapper.xml）：保存了每一个sql语句的映射信息
 *                                      将sql抽取出来
 */

class UserTest {
    /**
     *  1、根据xml配置文件（全局配置文件）创建一个sqlSessionFactory对象
     *                  有数据源等一些运行环境信息
     *  2、sql映射文件：配置了每一个sql，以及sql的封装规则等
     *  3、将sql映射文件注册在全局配置文件中
     *  4、写代码
     *      1）根据全局配置文件得到sqlSessionFactory
     *      2）使用sqlSessionFactory（工厂），获取sqlsession对象使用它来执行增删改查操作
     *          一个sqlSession就是代表和数据库的一次会话，用完关闭
     *      3）使用sql的唯一标识来告诉MyBatis执行哪个sql，sql都是保存在sql映射文件中
     */
    @Test
    public void test1() throws IOException {
        String resource = "com/sakanal/MyBatis/A_HelloWorld/config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession实例，能直接执行已经映射的Sql语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //selectOne有两个参数
        //1、sql的唯一标识        namespace.id（最好这样，防止id重复冲突）
        //2、执行sql要用的参数
        User user;
        try {
            user = sqlSession.selectOne("com.sakanal.MyBatis.A_HelloWorld.dao.UserMapper.getUserById", 1);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }

    public SqlSessionFactory getSqlSessionFactory() throws IOException{
        InputStream inputStream = Resources.getResourceAsStream("com/sakanal/MyBatis/A_HelloWorld/config/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test2() throws IOException{
        //获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper;
        try {
            //获取接口的实现类对象
            //会为接口自动的创建一个代理（Proxy）对象，代理对象去执行增删改查方法
            mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserById(1);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }
}