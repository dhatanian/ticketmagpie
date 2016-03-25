package com.ticketmagpie.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "spring.datasource.platform", havingValue = "mysql")
public class MySQLLastIdInserted implements LastIdInserted {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int get() {
    return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", (rs, rowNum) -> rs.getInt(1));
  }
}
