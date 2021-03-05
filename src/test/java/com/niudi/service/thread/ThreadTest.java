package com.niudi.service.thread;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName ThreadTest
 * @Description:
 * @Author kongxiaoyang
 * @Date 2021/2/23
 **/
public class ThreadTest {

    @Test
    public void testOne() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 10, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.submit(new SubThread());
        }
        int poolSize = threadPoolExecutor.getPoolSize();
        int corePoolSize = threadPoolExecutor.getCorePoolSize();
        System.out.println(poolSize);
        System.out.println(corePoolSize);
        Thread.sleep(50000);
        System.out.println("===========");
    }

    class SubThread implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            int n = 0;
            n += new Random().nextInt();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "=========" + n);
            return;
        }
    }

    @Getter
    @Setter
    @ToString
    class User {
        String name;
        int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }



    @Test
    public void testTwo() {

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("zz", 1));
        users.add(new User("xx", 2));
        users.add(new User("cc", 3));
        users.add(new User("vv", 4));
        users.add(new User("bb", 5));
        users.add(new User("nn", 6));

        List<User> collect = users.parallelStream().filter((user) -> this.isGT3(user)).collect(Collectors.toList());

        System.out.println(collect);

    }

    private boolean isGT3(User user) {
        return user.getAge() > 3 ? true : false;
    }
}
