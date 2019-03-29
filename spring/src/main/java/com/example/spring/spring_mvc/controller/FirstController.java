package com.example.spring.spring_mvc.controller;

import com.example.spring.spring_mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gavin
 * @date 2018/12/10 14:23
 */
@MyController
@MyRequestMapping("/web")
public class FirstController {
    @MyAutowired
    private MyService service;

    @MyRequestMapping("/query.json")
    @MyResponseBody
    public void query(HttpServletRequest req, HttpServletResponse resp, @MyRequestParam String name) {

    }
}
