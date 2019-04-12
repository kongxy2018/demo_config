package com.niudi.service;

import com.niudi.config.*;
import com.niudi.domain.User;
import com.niudi.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

/**
 * @Author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeansRegistryConfig.class,
        UserServiceConfig.class,
        PresistenceConfig.class,
        ServiceConfig.class})
public class BeanTest {

  @Autowired
  private UserService userService;
  @Test
  public void testBean() {
    User user = BeansRegistry.getBean(User.class);
    System.out.println(user);
  }

  @Test
  public void getDateBean() {
    DataSource dataSource = BeansRegistry.getBean(DataSource.class);
    System.out.println(dataSource);
  }

  @Test
  public void userTest() {
    userService.saveOneUser();
  }
}
