package com.example.mydubbo;

public class GreetingsServiceImpl implements DubboService {
    @Override
    public String sayHello(String name) {
        return "hi" + name;
    }
}
