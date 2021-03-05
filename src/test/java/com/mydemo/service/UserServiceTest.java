package com.mydemo.service;

import com.mydemo.config.*;
import com.mydemo.domain.User;
import com.mydemo.service.user.UserService;
import com.mydemo.util.redis.FastjsonRedisSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeansRegistryConfig.class,
        UserServiceConfig.class,
        PresistenceConfig.class,
        ServiceConfig.class,
        RedisConfig.class})
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Resource(name = "redisTemplate")
  private ValueOperations valueOperations;

  @Test
  public void userTest() {
    List<User> allUser = userService.findAllUser();
  }

  @Test
  public void saveUser() {
    User user = new User();
    user.setUserName("lishi");
    user.setAge(28);
    user.setBirthday("1990-02-02");
    int i = userService.saveOneUser(user);
    Long id = user.getId();
    System.out.println(id);
    System.out.println(i);
  }

  @Test
  public void redisTest() {
    String key = "ali";
    String value = "www.baidu.com";
    valueOperations.set(key, value, 30, TimeUnit.SECONDS);

    Object ali = valueOperations.get("ali");
    System.out.println(ali);
  }

  @Test
  public void testJson() {
    RedisSerializer<Object> jsonSerializer = new FastjsonRedisSerializer();

    System.out.println(jsonSerializer);
  }
}
