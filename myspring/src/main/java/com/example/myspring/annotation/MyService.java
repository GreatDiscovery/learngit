package com.example.myspring.annotation;

import java.lang.annotation.*;

/**
 * @author gavin
 * @date 2019/3/27 12:23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {
    String value() default "";
}
