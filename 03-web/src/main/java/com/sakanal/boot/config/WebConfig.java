package com.sakanal.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration(proxyBeanMethods = false)
public class WebConfig {
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_m");
        //修改后name=_method需要改成name=_m
        //<input type="hidden" name="_method" value="put">无效
        //<input type="hidden" name="_m" value="put">有效
        return hiddenHttpMethodFilter;
    }
}
