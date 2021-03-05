package com.niudi.config;

import com.niudi.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * @Author Administrator
 */
@Configuration
@PropertySource("classpath:user.properties")
public class UserServiceConfig {

  @Value("${user.userName}")
  private String userName;

  @Value("${user.age}")
  private int age;

  @Value("${user.birthday}")
  private String birthday;

  @Bean(initMethod = "init", destroyMethod = "close")
  public User user() {
    User user = new User();
    user.setUserName(userName);
    user.setAge(age);
    user.setBirthday(birthday);
    return user;
  }


}
