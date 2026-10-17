package com.example.student_demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String, Object> hello(
            @RequestParam(defaultValue = "Worldwide") String name){
                return Map.of(
                        "message","Hello" + "," + name,
                        "success", true
            );
        }

}
