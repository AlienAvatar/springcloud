package com.springcloud.client.controller;

import jdk.internal.dynalink.support.AutoDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/hello")
    public String hello() {
        String services = "Services: " + discoveryClient.getServices() +"\n Hello，I'm client";
        System.out.println(services);
        return services;
    }

    @GetMapping("/info")
    public String info(){
        return "hello client";
    }


}
