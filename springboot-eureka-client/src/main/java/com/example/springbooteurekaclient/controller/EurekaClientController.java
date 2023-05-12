package com.example.springbooteurekaclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qingchen
 * @date 12/5/2023 上午 10:50
 */

@RestController
public class EurekaClientController {

    @RequestMapping("/client")
    public String get(){
        return "qingchen client";

    }
}
