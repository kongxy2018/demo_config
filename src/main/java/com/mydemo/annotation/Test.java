package com.mydemo.annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.mydemo.annotation.MyClass");
        Annotation[] annotations = aClass.getAnnotations();

        Arrays.stream(annotations).forEach(ano -> {
            System.out.println(((MyAnnotation) ano).age());
            System.out.println(((MyAnnotation) ano).name());

        });
    }
}
