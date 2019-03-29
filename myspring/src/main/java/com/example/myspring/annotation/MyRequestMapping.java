package com.example.myspring.annotation;

import java.lang.annotation.*;

/**
 * @author gavin
 * @date 2019/3/27 12:17
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {
    String value() default "";
}
