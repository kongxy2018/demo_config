package com.niudi.controller;

import com.niudi.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author Administrator
 */
public class MainApp {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext();
    User user = (User) context.getBean("usre");
    System.out.println(user);

  }
}
