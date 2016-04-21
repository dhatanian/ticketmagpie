package com.ticketmagpie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @RequestMapping("/user/home")
  public String userHome(Model model, Authentication authentication) {
    model.addAttribute("tickets", ticketRepository.getTicketsForUser(authentication.getName()));
    return "user";
  }

  @RequestMapping("/user/book")
  public String userBook(@RequestParam String id, Model model) {
    model.addAttribute("concertid", id);
    return "bookingform";
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @RequestMapping("/user/book/payment")
  public String userPayment
      (@RequestParam("firstname") String firstName,
          @RequestParam("lastname") String lastName,
          @RequestParam("address1") String address1,
          @RequestParam("address2") String address2,
          @RequestParam("address3") String address3,
          @RequestParam("postcode") String postCode,
          @RequestParam("country") String country,
          @RequestParam("concertid") Integer concertId,
          Authentication currentUser) {
    Ticket ticket =
        new Ticket(concertRepository.get(concertId), firstName, lastName, address1, address2, address3, postCode, country, currentUser.getName());
    int savedTicketId = ticketRepository.save(ticket);
    return "redirect:/ticket?id=" + savedTicketId;
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
