package com.example.tdd_demo.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.tdd_demo.models.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController

public class GreetingController {

    private static final String template ="Hello, %s!";

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(String.format(template, name));
    }

}
