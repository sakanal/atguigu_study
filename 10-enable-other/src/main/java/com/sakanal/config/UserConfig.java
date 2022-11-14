package com.sakanal.config;

import com.sakanal.entity.Role;
import com.sakanal.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public User user(){
        return new User();
    }
    @Bean
    public Role role(){
        return new Role();
    }
}
