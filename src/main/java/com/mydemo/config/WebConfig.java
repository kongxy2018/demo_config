package com.mydemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author kongxy
 * @Description //TODO spring配置 开启mvc扫描
 * @Date
 * @Param
 * @return
 **/
@Configuration
@EnableWebMvc
@ComponentScan("**.controller")
public class WebConfig {

}
