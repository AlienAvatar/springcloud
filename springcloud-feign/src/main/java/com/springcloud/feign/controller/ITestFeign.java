package com.springcloud.feign.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-client")
public interface ITestFeign {
    @GetMapping("/hello") //这是服务提供方的 GetMapping
    String consumer();
}
