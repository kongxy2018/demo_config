package com.mydemo.service;

import com.mydemo.config.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeansRegistryConfig.class,
        UserServiceConfig.class,
        PresistenceConfig.class,
        ServiceConfig.class,
        RedisConfig.class})
public class UtilsTest {

  @Test
  public void test() {
    System.out.println(Time.valueOf("05:00:00"));
  }


  @Test
  public void timeTest() {
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_YEAR, -1);// 前天

    Date time = calendar.getTime();

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");


    String format = sdf.format(time);
    String format1 = sf.format(time);

    System.out.println(format);

    System.out.println(format1);
  }
}
