package com.niudi.service.xujiahuanxing;

public class Clerk {
  private final static int TOTAL = 1;
  private int num = 0;

  public synchronized void get() throws InterruptedException {
    while (num >= TOTAL) {
      System.out.println("已满。。。。");
      this.wait();

    }
      System.out.println(Thread.currentThread().getName() + ":" + num++);
      this.notifyAll();

  }


  public synchronized void sale() throws InterruptedException {
    while (num <= 0) {
      System.out.println("库存已空，无法售卖。。。。");
      this.wait();
    }
      System.out.println(Thread.currentThread().getName() + ":" + num++);
      this.notifyAll();


  }

}

class Producer implements Runnable {
  private Clerk clerk;
  public Producer(Clerk clerk) {
    this.clerk = clerk;
}
  @Override
  public void run() {
    for (int i = 0; i <= 20; i++) {
      try {
        Thread.sleep(200);
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        clerk.get();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class Consumer implements Runnable {
  private Clerk clerk;

  public Consumer(Clerk clerk) {
    this.clerk = clerk;
  }

  @Override
  public void run() {
    for (int i = 0; i < 20; i++) {
      try {
        clerk.sale();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}