package com.mydemo.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mydemo.util.quartz.JobScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;


/**
 * 调度配置
 * 
 * @author gaigeshen
 * @since 05/18 2017
 */
@Configuration
@EnableAsync
@EnableScheduling
//@ComponentScan("**.scheduling.task")
@PropertySource("classpath:cron.properties")
public class SchedulingConfig implements SchedulingConfigurer, Config {
  
  @Override
  public void configureTasks(ScheduledTaskRegistrar registrar) {
    
    registrar.setScheduler(this.executor());
    
  }
  
  @Bean(destroyMethod = "shutdown")
  public ExecutorService executor() {
    return Executors.newScheduledThreadPool(32);
  }
  
  @Bean(destroyMethod = "shutdown")
  public JobScheduler delayJobScheduler() {
    
    return new JobScheduler();
    
  }
  
}
