package com.niudi.service;

/**
 * @Author Administrator
 */
public class MyRunable implements Runnable {
  public MyRunable(){
    System.out.println(123456789);
  }

  @Override
  public void run() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("执行");
  }
}
