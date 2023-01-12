package com.demo.receive.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/receive")
@RequiredArgsConstructor
public class TestController {

    private final RedisTemplate template;
    private final RestTemplate restTemplate;

    @GetMapping("/name")
    public String youname() {
        template.opsForValue().set("demo","hello world");
        System.out.println("Value of key loda: "+template.opsForValue().get("demo"));
        return "my name nguyen";
    }

    @GetMapping("/hello")
    public String helloWorld() {
        String response = restTemplate.getForObject("http://localhost:9085/sender/name", String.class);
        return "<h1>Hello from the " + response + "!</h1>";
    }
}
