package com.ticketmagpie;

public class User {
  private String username;
  private String password;
  private String role;

  public User(String username, String password, String role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public String getRole() {
    return role;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "User{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
