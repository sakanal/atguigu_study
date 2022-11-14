package com.sakanal.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @description 创建自己的负载均衡接口
 */
public interface MyLoadBalancer {
    /**
     * 收集服务器总共有多少台能够提供服务的机器，并放到list里面
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);

}
