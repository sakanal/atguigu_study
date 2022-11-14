package com.sakanal.Spring.AOP.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.sakanal.Spring.AOP.annotation"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigAOP {
}
