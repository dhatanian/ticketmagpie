package com.ticketmagpie.infrastructure.persistence;

import static java.lang.String.format;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ticketmagpie.User;

@Component
public class UserRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public User get(String username) {
    try {
      return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=?", (rs, rowNum) -> toUser(rs), username);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public User checkUsernameAndPassword(String username, String password) {
    return jdbcTemplate.queryForObject(passwordCheckQuery(username, password), (rs, rowNum) -> toUser(rs));
  }

  private String passwordCheckQuery(String username, String password) {
    return format("SELECT * FROM users WHERE username='%s' AND password='%s'", username, password);
  }

  private User toUser(ResultSet rs) throws SQLException {
    return new User(rs.getString("username"), rs.getString("password"));
  }
}