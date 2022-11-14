package com.sakanal.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  只有在容器中的组件，才会拥有SpringBoot提供的强大功能
 */
@Data   //lombok插件简化开发
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "mycar")
//用了后@ConfigurationProperties(prefix = "mycar")，其他文件就会绑定不上
public class Car {
    private String brand;
    private Integer price;

}
