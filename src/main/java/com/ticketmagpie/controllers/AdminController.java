package com.ticketmagpie.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/admin")
public class AdminController {

  @RequestMapping("")
  public String userHome(Model model, Authentication authentication) {
    return "admin/home";
  }

}
