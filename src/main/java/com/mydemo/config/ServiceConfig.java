package com.mydemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Administrator
 */
@Configuration
@ComponentScan("**.service")
public class ServiceConfig {
}
