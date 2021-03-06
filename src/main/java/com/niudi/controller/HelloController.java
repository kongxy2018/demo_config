package com.niudi.controller;

import com.niudi.config.BeansRegistry;
import com.niudi.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Administrator
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

  /**
   * @Author kongxy
   * @Description //TODO
   * @Date
   * @Param
   * @return
   **/
  @ResponseBody
  @RequestMapping("/world")
  public String sayHello() {
    User user = BeansRegistry.getBean(User.class);
    System.out.println(user);
    return "Hello {} !";
  }
}
