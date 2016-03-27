package com.ticketmagpie.infrastructure.security;

import static java.util.Collections.emptyList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class TicketMagPieRememberMeService implements RememberMeServices, LogoutHandler {
  private static final String COOKIE_NAME = "ticketmagpie-rememberme";
  private static final String CHECKBOX_PARAMETER = "ticketmagpie-rememberme";
  private static final int ONE_DAY_IN_SECONDS = 24 * 3600;

  @Override
  public Authentication autoLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    if (httpServletRequest.getCookies() != null) {
      for (Cookie cookie : httpServletRequest.getCookies()) {
        if (COOKIE_NAME.equalsIgnoreCase(cookie.getName())) {
          return decrypt(cookie.getValue());
        }
      }
    }

    return null;
  }

  private Authentication decrypt(String value) {
    String decrypted = new String(Base64.decodeBase64(value));
    String[] values = decrypted.split("\\|");
    return new UsernamePasswordAuthenticationToken(values[0], values[1], emptyList());
  }

  @Override
  public void loginFail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    //Do nothing
  }

  @Override
  public void loginSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
    if (httpServletRequest.getParameter(CHECKBOX_PARAMETER) != null) {
      httpServletResponse.addCookie(cookieForUser((UsernamePasswordAuthenticationToken) authentication));
    }
  }

  private Cookie cookieForUser(UsernamePasswordAuthenticationToken authentication) {
    Cookie cookie =
        new Cookie(COOKIE_NAME, encrypt(authentication));
    cookie.setMaxAge(ONE_DAY_IN_SECONDS);
    return cookie;
  }

  private String encrypt(UsernamePasswordAuthenticationToken authentication) {return Base64.encodeBase64String((authentication.getName() + "|" + authentication.getCredentials()).getBytes());}

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    Cookie cookie = new Cookie(COOKIE_NAME, "");
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }
}
