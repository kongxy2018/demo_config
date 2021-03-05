package com.mydemo.annotation;

import java.lang.annotation.*;

@Inherited
@Target(value = {ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String name() default "默认值";
    String age() default "100";
}
