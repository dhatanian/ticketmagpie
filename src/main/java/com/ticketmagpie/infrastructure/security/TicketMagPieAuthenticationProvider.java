package com.ticketmagpie.infrastructure.security;

import static java.lang.String.format;
import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TicketMagPieAuthenticationProvider implements AuthenticationProvider {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = (String) authentication.getCredentials();
    try {
      jdbcTemplate.queryForObject(buildQuery(username, password), (rs, rowNum) -> rs.getString("username"));
    } catch (Exception e) {
      throw new UsernameNotFoundException("Error when searching for user: " + e.getMessage(), e);
    }
    return new UsernamePasswordAuthenticationToken(username, password, emptyList());
  }

  private String buildQuery(String username, String password) {
    return format("SELECT * FROM users WHERE username='%s' AND password='%s'", username, password);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class
        .isAssignableFrom(authentication));
  }
}
