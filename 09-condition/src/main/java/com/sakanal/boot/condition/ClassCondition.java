package com.sakanal.boot.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class ClassCondition implements Condition {
    /**
     * @param context   上下文对象。用于获取环境，IOC容器，ClassLoader对象
     * @param metadata  注解的元对象。可以用于获取注解定义的属性值
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //2需求：导入通过注解属性值value指定坐标后创建Bean
        Map<String, Object> metadataAnnotationAttributes = metadata.getAnnotationAttributes(ConditionOnClass.class.getName());
//        System.out.println("metadataAnnotationAttributes = " + metadataAnnotationAttributes);
        String[] values = (String[]) metadataAnnotationAttributes.get("value");
        boolean flag=true;
        try {
            for (String className : values) {
                Class.forName(className);
            }
        } catch (ClassNotFoundException e) {
            flag=false;
        }
        return flag;

        /*
        //1需求：导入Jedis坐标后创建Bean
        //思路：判断redis.clients.jedis.Jedis文件是否存在
        boolean flag=true;
        try {
            Class.forName("redis.clients.jedis.Jedis");
        } catch (ClassNotFoundException e) {
            flag=false;
        }
        return flag;
         */
    }
}
