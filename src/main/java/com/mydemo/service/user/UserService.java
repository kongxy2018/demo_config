package com.mydemo.service.user;


import com.mydemo.domain.User;

import java.util.List;

public interface UserService {

  public int saveOneUser();

  public List<User> findAllUser();

  public int saveOneUser(User user);

}
