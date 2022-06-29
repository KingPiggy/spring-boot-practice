package com.kingpiggy.study.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {return "index";}

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/authenticated")
    public String authenticatedHello () {
        return "Authenticated Hello!";
    }

}
