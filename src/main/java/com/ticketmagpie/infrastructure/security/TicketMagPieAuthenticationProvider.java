package com.ticketmagpie.infrastructure.security;

import static java.util.Collections.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ticketmagpie.User;
import com.ticketmagpie.infrastructure.persistence.UserRepository;

@Component
public class TicketMagPieAuthenticationProvider implements AuthenticationProvider {
  @Autowired
  private UserRepository userRepository;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = (String) authentication.getCredentials();
    User user;
    try {
      user = userRepository.checkUsernameAndPassword(username, password);
    } catch (Exception e) {
      throw new UsernameNotFoundException("Error when searching for user: " + e.getMessage(), e);
    }
    return toAuthentication(user);
  }

  private Authentication toAuthentication(User user) {return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), singleton(new SimpleGrantedAuthority(user.getRole())));}

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class
        .isAssignableFrom(authentication));
  }
}
