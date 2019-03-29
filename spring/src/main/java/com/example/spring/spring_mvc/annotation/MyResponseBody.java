package com.example.spring.spring_mvc.annotation;

import java.lang.annotation.*;

/**
 * @author gavin
 * @date 2018/12/10 11:14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyResponseBody {
    String value() default "";
}
