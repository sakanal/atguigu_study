package com.sakanal.Spring.IOC.useannotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//作为替代类，替代配置文件
@Configuration
@ComponentScan(basePackages = {"com.sakanal.Spring.IOC.useannotation"})
//<context:component-scan base-package="com.sakanal.Spring.IOC.useannotation"/>
public class SpringConfig {
}
