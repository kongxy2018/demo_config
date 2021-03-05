package com.mydemo.dao.user;

import com.mydemo.domain.User;

import java.util.List;

/**
 * @Author Administrator
 */
public interface UserDao {

  public int saveOneUser(User user);

  public List<User> findUsers();

  /**
   * 从数据库获取
   * @return
   */
  List<User> queryAllUser();
}
