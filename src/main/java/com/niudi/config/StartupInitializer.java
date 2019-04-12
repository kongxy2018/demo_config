package com.niudi.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * @Author kongxy
 * @Description //TODO 项目启动初始化类 相当于xml配置了listener-context 启动时会自动加载此类
 * @Date
 * @Param
 * @return
 **/
public class StartupInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] {
      RootConfig.class,
      PresistenceConfig.class,
      ServiceConfig.class,
      RedisConfig.class
    };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] {
      WebConfig.class,
      RestapiConfig.class
    };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {
      "/*"
    };
  }
}
