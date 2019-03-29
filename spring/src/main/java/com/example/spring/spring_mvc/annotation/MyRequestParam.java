package com.example.spring.spring_mvc.annotation;

import java.lang.annotation.*;

/**
 * @author gavin
 * @date 2018/12/10 11:13
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {
    String value() default "";

    boolean required() default true;
}
