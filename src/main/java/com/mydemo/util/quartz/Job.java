package com.mydemo.util.quartz;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务
 * 
 * @author gaigeshen
 * @since 08/25 2017
 */
public abstract class Job implements org.quartz.Job {

  private static final Logger LOG = LoggerFactory.getLogger(Job.class);
  
  @Override
  public final void execute(JobExecutionContext context) throws JobExecutionException {
    
    JobDetail jobDetail = context.getJobDetail();
    
    int refireCount = context.getRefireCount();
    
    try {
      
      execute(); // 执行具体的逻辑
      
    } catch (JobExecutionException ex) {
      
      // 如果允许立即重试并且重试次数没有超出限制
      if (ex.refireImmediately() && refireCount < getMaxRefireCount()) {
        LOG.info("Job {} execute failed, refire count {}, the job data {}",
            getClass().getName(), refireCount,
            formatJobData(jobDetail.getJobDataMap()));
        throw ex;
      }
    }
  }
  
  /**
   * 格式化任务数据
   * 
   * @param data 任务数据
   * @return 字符串
   */
  private String formatJobData(JobDataMap data) {
    
    StringBuilder builder = new StringBuilder("{ ");
    
    if (data != null && data.size() > 0) {
      data.forEach((k, v) -> {
        builder.append(k)
               .append(": ")
               .append(v)
               .append(", ");
      });
      
      return builder.substring(0, builder.length() - 2) + " }";
    }
    
    return builder.append("}").toString();
  }
  
  /**
   * 具体的任务执行逻辑方法
   * 
   * @throws JobExecutionException 如果执行任务过程中发生异常
   */
  protected abstract void execute() throws JobExecutionException;
  
  /**
   * 返回最大的重试次数
   * 
   * @return 最大的重试次数默认一次
   */
  protected int getMaxRefireCount() { return 1; }
  

}
