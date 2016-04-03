package com.ticketmagpie.infrastructure.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TicketMagPieCookieAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    return authentication;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (TicketMagPieCookieAuthenticationToken.class
        .isAssignableFrom(authentication));
  }
}
