package com.wjy.practice.controller;

import com.wjy.practice.domain.Test;
import com.wjy.practice.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/hello")
    public String hello () {
        return "hello world!";
    }

    @GetMapping("/test/list")
    public List<Test> list() {
        return testService.list();
    }
}
