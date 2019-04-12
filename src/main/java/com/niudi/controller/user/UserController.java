package com.niudi.controller.user;

import com.niudi.controller.Result;
import com.niudi.domain.User;
import com.niudi.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * @Author kongxy
 * @Date 2019/1/26 11:15
 * @DESC
 */
@Controller("/user")
public class UserController {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private UserService userService;

  public Result getAllUser() {
    List<User> userList = userService.findAllUser();
    return Result.success(userList);
  }
}
