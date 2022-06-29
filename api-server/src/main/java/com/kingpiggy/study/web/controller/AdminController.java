package com.kingpiggy.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminController {

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello Admin!";
    }

}
