package com.niudi.service.xujiahuanxing;

public class ThreadTest {

  public static void main(String[] args) {
    Clerk clerk = new Clerk();
    Producer producer = new Producer(clerk);
    Consumer consumer = new Consumer(clerk);

    new Thread(producer, "生产者A").start();
    new Thread(consumer, "消费者").start();
  }
}
