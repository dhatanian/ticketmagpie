package com.ticketmagpie.infrastructure.security;

import static java.util.Collections.emptyList;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class TicketMagPieCookieAuthenticationToken implements Authentication {
  private boolean authenticated = true;
  private final String username;

  public TicketMagPieCookieAuthenticationToken(String username) {this.username = username;}

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return emptyList();
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getDetails() {
    return username;
  }

  @Override
  public Object getPrincipal() {
    return username;
  }

  @Override
  public boolean isAuthenticated() {
    return authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    authenticated = isAuthenticated;
  }

  @Override
  public String getName() {
    return username;
  }
}
