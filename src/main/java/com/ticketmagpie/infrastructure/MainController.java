package com.ticketmagpie.infrastructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ticketmagpie.Concert;

@Controller
public class MainController {

  @Autowired
  private ConcertRepository concertRepository;

  @ModelAttribute("concerts")
  public List<Concert> injectConcerts() {
    return concertRepository.getAllConcerts();
  }

  @RequestMapping("/")
  public String index() {
    injectConcerts();
    return "index";
  }

  @RequestMapping("/user/home")
  public String userHome() {
    return "user";
  }

}
