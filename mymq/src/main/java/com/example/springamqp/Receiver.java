package com.example.springamqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;


@Component
@RabbitListener(queues = "hello")
public class Receiver {
    @Async
    @RabbitHandler
    public Future process(String hello) {
        System.out.println("Receiver : " + hello);
        return new AsyncResult("收到消息");
    }
}
