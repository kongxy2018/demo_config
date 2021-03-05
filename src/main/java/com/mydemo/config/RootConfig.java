package com.mydemo.config;

/**
 * @Author Administrator
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author kongxy
 * @Description //TODO spring容器配置
 * @Date
 * @Param
 * @return
 **/
@Configuration
@ComponentScan("**.mydemo")
public class RootConfig {

}
