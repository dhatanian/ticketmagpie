package com.ticketmagpie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
