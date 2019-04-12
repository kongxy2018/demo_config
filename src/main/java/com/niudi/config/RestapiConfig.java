package com.niudi.config;

import com.niudi.web.LoggingHandlerInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author Administrator
 */
@Configuration
@EnableWebMvc
@ComponentScan("**.controller")
public class RestapiConfig extends WebMvcConfigurerAdapter {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoggingHandlerInterceptor());
    super.addInterceptors(registry);
  }
}
