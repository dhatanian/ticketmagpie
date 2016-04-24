package com.ticketmagpie.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ticketmagpie.infrastructure.persistence.UserRepository;

@Controller
@RequestMapping("/user/admin")
public class AdminController {
  @Autowired
  private UserRepository userRepository;

  @RequestMapping("")
  public String home() {
    return "admin/home";
  }

  @RequestMapping(value = "/users", method = GET)
  public String listUsers(Model model) {
    model.addAttribute("users", userRepository.getAllUsers());
    return "admin/users";
  }
}
