package com.niudi.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

/**
 * 日志拦截器，针对控制器方法打印日志
 * 
 * @author
 * @since 06/03 2017
 */
public final class LoggingHandlerInterceptor extends HandlerInterceptorAdapter {
  private static Logger logger = LoggerFactory.getLogger(LoggingHandlerInterceptor.class);
  private static String dateFormat = "yyyy-MM-dd HH:mm:ss";// 时间格式

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
    Object handler) throws Exception {
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    Long startTime = System.currentTimeMillis();// 开始时间
    request.setAttribute("startTime", startTime);
    logger.info("请求地址：" + request.getRequestURI() + "，客户端请求IP："+ request.getHeader("X-Real-IP") + "，调用方法名：" + 
      handlerMethod.getMethod().getName() + "，开始时间：" + DateFormatUtils.format(new Date(), dateFormat));
    
    logger.info("入参sign：X-Sign = " + request.getHeader("X-Sign"));
    logger.info("入参head：X-Auth-Token = " + request.getHeader("X-Auth-Token"));
    
    Map<String, String[]> map = request.getParameterMap();
    String json = JSON.toJSONString(map).replace("[", "").replace("]", "");
    if (StringUtils.isNotBlank(json)) {
      if (json.length() > 20280) {
        json = json.substring(0, 20280);
      }
    }
    logger.info("入参body：" + json);

    return super.preHandle(request, response, handler);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
    Object handler, Exception ex) throws Exception {
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    Long endTime = System.currentTimeMillis();// 结束时间
    Long startTime = Long.parseLong(request.getAttribute("startTime").toString());
    logger.info("响应地址：" + request.getRequestURI() + "，调用方法名：" + 
      handlerMethod.getMethod().getName() + "，结束时间：" + DateFormatUtils.format(new Date(), dateFormat) + 
      "，执行时间：" + (endTime - startTime) + "ms");
    super.afterCompletion(request, response, handler, ex);
  }
  
}