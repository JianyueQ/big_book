package com.big_event.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return " update 1123123";
    }
}
