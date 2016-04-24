package com.ticketmagpie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketmagpie.infrastructure.persistence.ConcertRepository;
import com.ticketmagpie.infrastructure.persistence.TicketRepository;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private ConcertRepository concertRepository;

  @Autowired
  private TicketRepository ticketRepository;

  @RequestMapping("/home")
  public String userHome(Model model, Authentication authentication) {
    model.addAttribute("tickets", ticketRepository.getTicketsForUser(authentication.getName()));
    return "user";
  }

  @RequestMapping("/book")
  public String userBook(@RequestParam String id, Model model) {
    model.addAttribute("concertid", id);
    return "bookingform";
  }

  @RequestMapping("/book/payment")
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

}
