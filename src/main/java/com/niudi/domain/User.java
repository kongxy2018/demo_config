package com.niudi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @Author Administrator
 */
@Getter
@Setter
@ToString
public class User {

  private Long id;
  private String userName;
  private int age;
  private String birthday;

  private void init() {
    System.out.println("--------------------------------------------123456789-----------------------------------------------");
  }

  private void close() {
    System.out.println("=======================================close==================================================");
  }
}
