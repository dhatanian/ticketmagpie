package com.ticketmagpie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketmagpie.Ticket;
import com.ticketmagpie.User;
import com.ticketmagpie.infrastructure.persistence.ConcertRepository;
import com.ticketmagpie.infrastructure.persistence.TicketRepository;
import com.ticketmagpie.infrastructure.persistence.UserRepository;

@Controller
public class MainController {

  @Autowired
  private ConcertRepository concertRepository;

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private UserRepository userRepository;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("concerts", concertRepository.getAllConcerts());
    return "index";
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @RequestMapping("/ticket")
  public String ticket(@RequestParam Integer id, Model model) {
    Ticket ticket = ticketRepository.get(id);
    model.addAttribute("ticket", ticket);
    return "ticket";
  }

  @RequestMapping("/forgotpassword")
  public String forgotPassword(@RequestParam(required = false) String user) {
    return "forgotpassword";
  }

  @RequestMapping("/passwordemail")
  public String passwordEmail(@RequestParam String user, Model model) {
    User userFromDatabase = userRepository.get(user);
    System.out.println(userFromDatabase);
    model.addAttribute("userFromDatabase", userFromDatabase);
    return "passwordemail";
  }
}
