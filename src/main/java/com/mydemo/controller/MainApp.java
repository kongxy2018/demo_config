package com.mydemo.controller;

import com.mydemo.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author Administrator
 */
public class MainApp {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext();
    User user = (User) context.getBean("user");
    System.out.println(user);

  }
}
