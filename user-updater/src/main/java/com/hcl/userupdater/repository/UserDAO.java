package com.hcl.userupdater.repository;

import com.hcl.userupdater.domain.DAO;
import com.hcl.userupdater.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.log4j.Logger;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class UserDAO implements DAO<User> {

  private static final Logger logger = Logger.getLogger(UserDAO.class);

  @Autowired
  private SessionFactory sessionFactory;

  public void setSessionFactory(SessionFactory sf){
    this.sessionFactory = sf;
  }

  public List<User> getAll() {
    List<User> users = this.sessionFactory.getCurrentSession().createQuery("FROM User").list();
    users.forEach(u -> logger.info("Person List::" + u));
    return users;
  }

  public User get(Long id) {
    String query = "FROM User U WHERE U.id =" + id;
    List<User> users = this.sessionFactory.getCurrentSession().createQuery(query).list();
    if (!users.isEmpty()) {
      User user = users.get(0);
      logger.info("User loaded successfully, User details: " + user);
      return user;
    } else {
      logger.info("Could not find user with id: " + id);
      return null;
    }
  }

  public User add(User user) {
    Session session = this.sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    Long id = (Long)session.save(user);
    transaction.commit();
    session.close();
    User addedUser = get(id);
    logger.info("User added successfully, User Details: " + addedUser);
    return addedUser;
  }

  public void update(User user) {
    Session session = this.sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.update(user);
    transaction.commit();
    session.close();
    logger.info("User updated successfully, User Details: " + user);
  }

  public void delete(Long id) {
    Session session = this.sessionFactory.openSession();
    session.beginTransaction();
    User user = get(id);
    session.delete(user);
    session.getTransaction().commit();
    session.close();
    logger.info("User deleted successfully, user details: " + user);
  }


}
