package com.niudi.service.user;


import com.niudi.domain.User;

import java.util.List;

public interface UserService {

  public int saveOneUser();

  public List<User> findAllUser();

  public int saveOneUser(User user);

}
