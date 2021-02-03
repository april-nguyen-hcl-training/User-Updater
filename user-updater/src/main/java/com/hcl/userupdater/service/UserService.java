package com.hcl.userupdater.service;

import com.hcl.userupdater.domain.User;
import com.hcl.userupdater.repository.UserDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
  private UserDAO userDAO;

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }
  @Transactional
  public List<User> listUsers() {
    return this.userDAO.getAll();
  }

  @Transactional
  public User getUserById(long id) {
    return this.userDAO.get(id);
  }

  @Transactional
  public User addUser(User user) {
    return this.userDAO.add(user);
  }

  @Transactional
  public void updateUser(User user) {
    this.userDAO.update(user);
  }

  @Transactional
  public void removeUser(long id) {
    this.userDAO.delete(id);
  }

}
