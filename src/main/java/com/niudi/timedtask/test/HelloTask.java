package com.niudi.timedtask.test;

import com.niudi.util.quartz.Job;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Administrator
 */
public class HelloTask extends Job {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  protected void execute() throws JobExecutionException {
    try {
    log.info("测试定时执行------Hello job ");
    System.out.println("hello job executing");
//    int a = 10/0;
    } catch (Exception e) {
      log.error("错误测试。。。");
      JobExecutionException e2 = new JobExecutionException(e);
      e2.setRefireImmediately(true);
      throw e2;
    }

    try {
      log.info("出错之后的业务执行");
    } catch (Exception e) {
      log.error("cuowu ");
    }
  }

  protected int getMaxRefireCount() { return 2; }
}
