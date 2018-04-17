package com.springcloud.feign.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    ITestFeign iTestFeign;

    @GetMapping("/consumer")
    public String consumer(){
        return iTestFeign.consumer();
    }
}
