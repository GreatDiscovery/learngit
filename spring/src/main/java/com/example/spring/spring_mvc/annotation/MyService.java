package com.example.spring.spring_mvc.annotation;

import java.lang.annotation.*;

/**
 * @author gavin
 * @date 2018/12/10 11:12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {
    String value() default "";
}
