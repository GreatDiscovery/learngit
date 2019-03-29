package com.example.myspring.annotation;

import java.lang.annotation.*;

/**
 * @author gavin
 * @date 2019/3/27 12:14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyController {
    String value() default "";
}
