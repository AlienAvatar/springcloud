package com.springcloud.ribbon.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer")
    @HystrixCommand(fallbackMethod = "fallback")//延迟这调用这个方法名
    public String consumer() throws Exception{
        Thread.sleep(5000L);//为了触发降级延迟
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return restTemplate.getForObject("http://eureka-client/hello", String.class);
    }

    public String fallback() {
        return "fallback";
    }
}


