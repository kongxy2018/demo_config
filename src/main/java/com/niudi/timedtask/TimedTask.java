package com.niudi.timedtask;

import com.niudi.timedtask.test.HelloTask;
import com.niudi.util.quartz.JobScheduler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import com.niudi.timedtask.TimedConstant.*;
import java.io.IOException;
import java.util.Properties;

/**
 * 定时任务入口
 * 
 * @author kongxiaoyang
 * @since 09/21 2017
 */
@Component
public class TimedTask implements InitializingBean {

  @Autowired
  private JobScheduler scheduler;
  
  private static Properties props = null;
  static {
    props = new Properties();
    ClassPathResource resource = new ClassPathResource("cron.properties");
    try {
      props.load(resource.getInputStream());
    } catch (IOException e) {
      throw new IllegalStateException("Could not load cron");
    }
  }
  
  @Override
  public void afterPropertiesSet() throws Exception {
    
    // 测试
    String cron = (String) props.getOrDefault("user.star", "0 */1 * * * ?");
    scheduler.cancelJob(Timed.STAR_STATISTICS);// 删除任务
    scheduler.newJob(Timed.STAR_STATISTICS, HelloTask.class, cron);

  }
  
}