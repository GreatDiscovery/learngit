package com.example.mydubbo;

import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MydubboApplication {
    private final Logger logger = LoggerFactory.getLogger(MydubboApplication.class);
    @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:8000")
    private DubboService dubboService;
    public static void main(String[] args) {
        SpringApplication.run(MydubboApplication.class, args).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            logger.info(dubboService.sayHello("gavin"));
        };
    }
}
