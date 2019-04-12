package com.niudi.util.quartz;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务调度器，可调度定时任务和延时任务
 * 
 * @author gaigeshen
 * @since 08/04 2017
 */
public final class JobScheduler {
  
  private static final Logger LOG = LoggerFactory.getLogger(JobScheduler.class);
  
  private static final String DELAYED_JOB_GROUP_NAME = "delayed-job";
  
  private static final String DELAYED_JOB_NAME_TPL = "delayed-job-%s";
  
  private static final String DELAYED_TRIGGER_GROUP_NAME = "delayed-trigger";
  
  private static final String DELAYED_TRIGGER_NAME_TPL = "delayed-trigger-%s";
  
  private final Scheduler scheduler;

  /**
   * 创建任务调度器
   */
  public JobScheduler() {
    try {
      scheduler = StdSchedulerFactory.getDefaultScheduler();
      scheduler.startDelayed(30);
    } catch (SchedulerException e) {
      throw new IllegalStateException("Could not get default scheduler", e);
    }
  }
  
  /**
   * 判断任务是否存在
   * 
   * @param id 编号
   * @return 是否存在
   */
  public boolean jobExists(String id) {
    try {
      return scheduler.checkExists(buildJobKey(id));
    } catch (SchedulerException e) {
      throw new IllegalStateException("Error occurred while checking exists of job " + id, e);
    }
  }
  
  /**
   * 获取任务的状态
   * 
   * @param id 编号
   * @return 任务的状态，如果不存在该任务或者不能获取到该任务的状态则返回{@link JobStatus#NONE}
   */
  public JobStatus jobStatus(String id) {
    TriggerState state = null;
    try {
      state = scheduler.getTriggerState(buildTriggerKey(id));
    } catch (SchedulerException e) {
      LOG.warn("Could not get status of job " + id, e);
      return JobStatus.NONE;
    }
    
    switch (state) {
    case BLOCKED:
      return JobStatus.BLOCKED;
    case COMPLETE:
      return JobStatus.COMPLETE;
    case ERROR:
      return JobStatus.ERROR;
    case NONE:
      return JobStatus.NONE;
    case NORMAL:
      return JobStatus.NORMAL;
    case PAUSED:
      return JobStatus.PAUSED;
    default:
      return JobStatus.NONE;
    }
  }
  
  
  
  /**
   * 添加延迟任务
   * 
   * @param id 编号
   * @param job 任务类型
   * @param data 传递数据到任务
   * @param delaySeconds 延迟时间单位秒
   * @return 是否添加成功
   */
  public boolean newDelayJob(String id, Class<? extends DelayJob> job, Map<String, Object> data, long delaySeconds) {
    
    LOG.info("New delay job to be added, the id is {}", id);
    
    try {
      scheduler.scheduleJob(buildJobDetail(id, job, data), buildTrigger(id, delaySeconds));
      return true;
    } catch (SchedulerException e) {
      LOG.warn("Error occurred while adding new delay job", e);
      return false;
    }
  }
  
  /**
   * 添加定时任务
   * 
   * @param id 编号
   * @param job 任务类型
   * @param cron 定时表达式
   * @return 是否添加成功
   */
  public boolean newJob(String id, Class<? extends Job> job, String cron) {
    
    return newJob(id, job, null, cron);
    
  }
  
  /**
   * 添加定时任务
   * 
   * @param id 编号
   * @param job 任务类型
   * @param data 传递数据到任务
   * @param cron 定时表达式
   * @return 是否添加成功
   */
  public boolean newJob(String id, Class<? extends Job> job, Map<String, Object> data, String cron) {
    
    LOG.info("New job to be added, the id is {}", id);
    
    if (!CronExpression.isValidExpression(cron)) {
      LOG.warn("Invalid cron expression");
      return false;
    }
    
    try {
      scheduler.scheduleJob(buildJobDetail(id, job, data), buildTrigger(id, cron));
      return true;
    } catch (SchedulerException e) {
      LOG.warn("Error occurred while adding new job", e);
      return false;
    }
    
  } 
  
  /**
   * 更改任务的执行时间
   * 
   * @param id 编号
   * @param cron 定时表达式
   * @return 最近的执行时间如果更改成功的话
   */
  public Date updateJob(String id, String cron) {
    try {
      return scheduler.rescheduleJob(buildTriggerKey(id), buildTrigger(id, cron));
    } catch (SchedulerException e) {
      LOG.warn("Update job failed of job " + id, e);
      return null;
    }
  }
  
  /**
   * 取消任务
   * 
   * @param id 任务编号
   * @return 是否取消成功
   */
  public boolean cancelJob(String id) {
    try {
      return scheduler.deleteJob(buildJobKey(id));
    } catch (SchedulerException e) {
      LOG.warn("Cancel job failed", e);
      return false;
    }
  }
  
  /**
   * 关闭调度器
   */
  public void shutdown() {
    try {
      scheduler.shutdown();
    } catch (SchedulerException e) {
      LOG.warn("Shutdown job scheduler failed", e);
    }
  }
  
  // ------------------- Internal methods
  
  private JobDetail buildJobDetail(String id,
      Class<? extends org.quartz.Job> job, Map<String, Object> data) {
    
    JobBuilder builder = JobBuilder.newJob(job)
        // 设定标识符
        .withIdentity(buildJobKey(id));
    
    // 添加任务数据
    if (data != null) {
      builder.usingJobData(new JobDataMap(data));
    }
    
    return builder.build();
        
  }
  
  private Trigger buildTrigger(String jobId, long delaySeconds) {
    
    LocalDateTime now = LocalDateTime.now();
    
    // 任务的执行时间
    LocalDateTime delay = now.plusSeconds(delaySeconds);
    
    return buildTrigger(jobId, 
        // 指定的年月日和指定的时分秒
        String.format("%d %d %d %d %d ? %d",
            delay.getSecond(), delay.getMinute(), delay.getHour(),
            delay.getDayOfMonth(), delay.getMonthValue(), delay.getYear()));

  }
  
  private Trigger buildTrigger(String jobId, String cron) {
    
    return TriggerBuilder.newTrigger()
        // 开始时间
        .startNow()
        // 设定标识符
        .withIdentity(buildTriggerKey(jobId))
        // 设定调度策略在指定的时间点
        .withSchedule(
            CronScheduleBuilder.cronSchedule(cron)
            .withMisfireHandlingInstructionIgnoreMisfires()
            .inTimeZone(TimeZone.getDefault()))
        .build();
    
  }
  
  private JobKey buildJobKey(String id) {
    
    String name = String.format(DELAYED_JOB_NAME_TPL, id);
    
    return new JobKey(name, DELAYED_JOB_GROUP_NAME);
    
  }
  
  private TriggerKey buildTriggerKey(String jobId) {
    
    String name = String.format(DELAYED_TRIGGER_NAME_TPL, jobId);
    
    return new TriggerKey(name, DELAYED_TRIGGER_GROUP_NAME);
    
  }
}
