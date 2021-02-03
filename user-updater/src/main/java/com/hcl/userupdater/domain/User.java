package com.hcl.userupdater.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@Entity
@Table(name= "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username", unique = true)
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "birthday")
  private Date birthday;

  @Column(name = "date_created")
  private Date dateCreated;

  public User () {}

  public User(String username, String password, String firstName, String lastName, String email, Date birthday) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.birthday = birthday;
  }

  public User(Long id, String username, String password, String firstName, String lastName, String email, Date birthday, Date dateCreated) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.birthday = birthday;
    this.dateCreated = dateCreated;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.username = password;
  }

  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public Date getBirthday() {
    return birthday;
  }
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Date getDateCreated() {
    return dateCreated;
  }
  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT-6"));
    return "{" + id + ", " +
      username + ", " +
      password + ", " +
      firstName + ", " +
      lastName + ", " +
      email + ", " +
      sdf.format(birthday) + ", " +
      sdf.format(dateCreated) + "}";
  }
}
