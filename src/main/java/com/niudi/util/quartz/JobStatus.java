package com.niudi.util.quartz;

/**
 * 任务的执行状态
 * 
 * @author gaigeshen
 * @since 08/28 2017
 */
public enum JobStatus {
  
  
  // See: org.quartz.Trigger.TriggerState
  
  NONE, NORMAL, PAUSED, COMPLETE, ERROR, BLOCKED
}
