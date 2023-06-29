package com.ncu.edu.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/error")
    public String error(){
        return "error";
    }

}
