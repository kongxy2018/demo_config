package com.niudi.service.user;

import com.niudi.dao.user.UserDao;
import com.niudi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private User user;

  @Override
  public int saveOneUser(User user) {
    int i = userDao.saveOneUser(user);
    return i;
  }

  @Override
  public int saveOneUser() {
    return 0;
  }

  @Override
  public List<User> findAllUser() {
    List<User> users = userDao.findUsers();
    for (User user : users) {
      System.out.println(user);
    }
    return users;
  }
}
