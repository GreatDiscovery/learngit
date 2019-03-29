package com.example.myspring.annotation;


import java.lang.annotation.*;

/**
 * @author gavin
 * @date 2019/3/27 12:16
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {
    String value() default "";
}
