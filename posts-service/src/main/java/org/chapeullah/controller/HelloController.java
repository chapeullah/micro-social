package org.chapeullah.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class HelloController {
    @GetMapping("/")
    public String noPath() {
        return "PostsService";
    }

    @GetMapping("/hello")
    public String hello() {
        return "PostsService: Hello!";
    }
}
