package com.spring.boot.controller;

import com.spring.boot.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * Controller层
 *
 * @author 代码的路
 * @date 2022/11/14
 */


@RestController
@RequestMapping("/homepage")
public class MyController {

    @Autowired
    MyService myService;

    // 提供get接口
    @GetMapping("/provideGet")
    public Map<String, Object> provideGet() {
        return myService.provideGet();
    }

    // 提供map参数的get接口
    @GetMapping("/provideGetByMap")
    public Map<String, Object> provideGetByMap(@RequestParam Map<String, Object> map) {
        return myService.provideGetByMap(map);
    }

    // 提供post接口
    @PostMapping("/providePost")
    public Map<String, Object> providePost() {
        return myService.providePost();
    }

    // 提供map参数的post接口
    @PostMapping("/providePostByMap")
    public Map<String, Object> providePostByMap(@RequestParam Map<String, Object> map) {
        return myService.providePostByMap(map);
    }

    // 调用get接口
    @GetMapping("/useGet")
    public Map<String, Object> useGet(@RequestParam Map<String, Object> map) {
        return myService.useGet(map);
    }

    // 调用post接口
    @PostMapping("/usePost")
    public Map<String, Object> usePost(@RequestParam Map<String, Object> map) {
        return myService.usePost(map);
    }
}
