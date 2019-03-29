package meite.example.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author gavin
 * @date 2019/1/10 19:40
 */
@SpringBootApplication
@ComponentScan(basePackages = "meite.example.redis")
@Configuration("classpath*:application.yaml")
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
