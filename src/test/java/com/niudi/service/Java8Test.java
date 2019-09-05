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

  @Test
  public void test() {

    int[] a = {1,2,3,4,5,2,3,4,2};
    Set set = new HashSet();
    Set set2 = new HashSet();
    Set set3 = new HashSet();
    for (int i = 0; i < a.length; i++) {
      int oldSize = set.size();
      set.add(a[i]);
      int newSize = set.size();
      if (oldSize == newSize) {
        int oldSize2 = set2.size();
        set2.add(a[i]);
        int newSize2 = set2.size();
        if (oldSize2 == newSize2) {
          set3.add(a[i]);
        }
      }
    }
    set2.removeAll(set3);
    for (int i = 0; i < a.length; i++) {
      int size = set2.size();
      set2.add(a[i]);
      int size1 = set2.size();
      if (size == size1) {
        System.out.println(a[i]);
        break;
      }
    }
    System.out.println(123);
  }

  @Test
  public void randomTest() {
    double[] arr = {1, 1.2, 2.5, 3, 5};
    Random r = new Random();
    int m = 1;
    int j = 0;
    List<Double> list = new ArrayList<>();
    for (int n = 0; n < 10000; n++) {
      int i = r.nextInt(15);
      if (i == 0) {
        j = 0;
      } else if (i > 0 && i <= 2) {
        j = 1;
      } else if (i > 2 && i <= 5) {
        j = 2;
      } else if (i > 5 && i <= 9) {
        j = 3;
      } else if (i > 9 && i <= 14) {
        j = 4;
      }
      list.add(arr[j]);
      if (m == 3) {
        m = 0;
        System.out.println(list);
        list.clear();
      }
      m++;
    }
  }

}
