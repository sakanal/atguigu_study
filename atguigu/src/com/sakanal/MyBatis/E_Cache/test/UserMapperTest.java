package com.sakanal.MyBatis.E_Cache.test;

import com.sakanal.MyBatis.E_Cache.mapper.UserMapper;
import com.sakanal.MyBatis.E_Cache.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class UserMapperTest {
    /**
     *  两级缓存：
     *      一级缓存（本地缓存）：sqlSession级别的缓存。一级缓存是一直开启的；sqlSession级别的一个map
     *          与数据库同一次会话期间查询到的数据会放在本地缓存中。
     *          以后如果需要获取相同的数据，直接从缓存中拿取，没必要再去查询数据库
     *          一级缓存失效的情况（没有使用到当前一级缓存的情况，效果就是：还需要再向数据库发出查询）
     *          1、sqlSession不同
     *          2、sqlSession相同，条件不同（当前一级缓存中还没有这个数据）
     *          3、sqlSession相同，两次查询期间执行了增删改操作（这次增删改可能对当前数据有影响）
     *          4、sqlSession相同，手动清除一级缓存（缓存清空）sqlSession.clearCache()
     *
     *      二级缓存（全局缓存）：基于namespace级别的缓存：一个namespace对应一个二级缓存
     *          二级缓存存在于 SqlSessionFactory 生命周期中。必须是同一个SqlSessionFactory创建的SqlSession才可以使用
     *          查出的数据会被默认先放在一级缓存中
     *          只有会话提交或者关闭以后，一级缓存中的数据才会转移到二级缓存中
     *          SqlSession中存在一级缓存，需要先将SqlSession关闭，才会将一级缓存中的数据保存在二级缓存中；否则二级缓存不会存在数据
     *          工作机制：
     *              1、一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中
     *              2、如果会话关闭，一级缓存中的数据就会保存在二级缓存中；新的查询信息，就可以参照二级缓存中的内容
     *              3、sqlSession=====UserMapper====》User
     *                                DepartmentMapper===》Department
     *                  不同的namespace查出的数据会放在自己对应的缓存中（map）
     *          使用：
     *              1、开启全局二级缓存配置：<setting name="cacheEnabled" value="true"/>
     *              2、去每个mapper.xml中配置使用二级缓存：<cache></cache>
     *              3、我们的POJO需要实现序列化接口：implements Serializable
     *
     *
     *      和缓存有关的设置/属性：
     * 			    1）、cacheEnabled=true：false：关闭缓存（二级缓存关闭）(一级缓存一直可用的)
     * 			    2）、每个select标签都有useCache="true"：
     * 		    			false：不使用缓存（一级缓存依然使用，二级缓存不使用）
     * 		    	3）、【每个增删改标签的：flushCache="true"：（一级二级都会清除）】
     * 				    	增删改执行完成后就会清楚缓存；
     * 			    		测试：flushCache="true"：一级缓存就清空了；二级也会被清除；
     * 	    				查询标签：flushCache="false"：
     * 	    					如果flushCache=true;每次查询之后都会清空缓存；缓存是没有被使用的；
     * 	    		4）、sqlSession.clearCache();只是清除当前session的一级缓存；
     * 	    		5）、localCacheScope：本地缓存作用域：（一级缓存SESSION）；当前会话的所有数据保存在会话缓存中；
     * 	    							STATEMENT：可以禁用一级缓存；
     *
     *      第三方缓存整合：
     *	        	1）、导入第三方缓存包即可；
     *	        	2）、导入与第三方缓存整合的适配包；官方有；
     *	        	3）、mapper.xml中使用自定义缓存
     *	            	<cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
     *
     */


    public SqlSessionFactory getSqlSessionFactory() throws IOException{
        InputStream inputStream = Resources.getResourceAsStream("com/sakanal/MyBatis/E_Cache/config/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFirstLevelCache() throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            User user1 = mapper.getUserById(5);
            System.out.println(user1);

            sqlSession.clearCache();

            User user2 = mapper.getUserById(5);
            System.out.println(user2);

            System.out.println(user1==user2);
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testSecondLevelCache()throws IOException{
        InputStream inputStream = Resources.getResourceAsStream("com/sakanal/MyBatis/E_Cache/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        try {
            UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
            UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);

            User user1 = mapper1.getUserById(5);
            sqlSession1.close();
            User user2 = mapper2.getUserById(5);

//            List<User> user1 = mapper1.getUser();
//            sqlSession1.close();
//            List<User> user2 = mapper2.getUser();

            System.out.println(user1);
            System.out.println(user2);
            System.out.println(user1==user2);
        }finally {
            if (sqlSession1 != null) {
                sqlSession1.close();
            }
            if (sqlSession2 != null) {
                sqlSession2.close();
            }
        }
    }

















    @Test
    public void testGetUser()throws IOException{
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> list = mapper.getUser();
            list.forEach(System.out::println);
        }finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}