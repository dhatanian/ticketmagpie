package com.ticketmagpie.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private TicketMagPieAuthenticationProvider ticketMagPieAuthenticationProvider;

  @Autowired
  private TicketMagPieRememberMeService ticketMagPieRememberMeService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        //Necessary for HTML forms to work
        .csrf().disable()
        //Required if we ever want to include the app in an iframe
        .headers().disable()
        .authorizeRequests()
        .antMatchers("/user/**").authenticated()
        .anyRequest().permitAll()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .and()
        .rememberMe()
        .rememberMeServices(ticketMagPieRememberMeService)
    ;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .authenticationProvider(ticketMagPieAuthenticationProvider)
        .eraseCredentials(false);
  }

  @Bean
  public SpringSecurityDialect securityDialect() {
    return new SpringSecurityDialect();
  }
}
