package com.example.spring.spring_aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedList;
import java.util.Stack;
import java.util.UUID;

/**
 * @author gavin
 * @date 2018/12/6 15:50
 */

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class AopTest {
    @Test
    public void testAop() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userService");
//        userService.add();
//        userService.delete();
        userService.edit();
    }

}
