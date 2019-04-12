package com.niudi.service;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author Administrator
 */
public class Java8Test extends Thread{

  private ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
    private final AtomicLong count = new AtomicLong();
    @Override
    public Thread newThread(Runnable r) {
      // 给线程命名
      Thread thread = new Thread(r, "my-test" + count.incrementAndGet());

      return thread;
    }
  });

  private ExecutorService executor = Executors.newFixedThreadPool(64);
  @Test
  public void j8Test() {
    List<String> list = new ArrayList<>();
    list.add("str1");
    list.add("str2");
    list.add("str3");

    Map<String, Object> map = new HashMap<>();

    list.forEach(str -> map.put(str, str));

    for (int i = 0; i < 10; i++) {

    new Thread(() -> System.out.println(this.getName())).start();
    }

    System.out.println(map);
    executorService.execute(new MyRunable());
    executorService.execute(new MyRunable());
    executorService.submit(new MyRunable());
    executorService.shutdown();
    if (executorService.isShutdown()) {
      System.out.println("线程池关闭");
    }else {
      System.out.println("kaiqi");
    }

    executor.execute(new MyRunable());
    if (executor.isShutdown()) {
      System.out.println(111);
    } else {
      System.out.println(222);
    }

  }

  @Test
  public void java8StreamTest() {
    Map<String, Integer> map = new HashMap<>();
    List<Integer> list = Arrays.asList(8,5,25,1,12,6,3);
    List<Integer> collect = list.stream().filter(i -> i > 5).sorted(Integer::compareTo).collect(Collectors.toList());
    collect.forEach(n -> map.put(n.toString(), n));

    System.out.println(map);

    System.out.println(collect);
  }

  @Test
  public void java8StreamTest1() {
    List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
    // 获取空字符串的数量
    Stream<String> stringStream = strings.stream().filter(string -> string.isEmpty());
    System.out.println(stringStream.toArray().toString());
  }
}
