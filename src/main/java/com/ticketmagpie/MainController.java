package com.ticketmagpie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketmagpie.infrastructure.persistence.ConcertRepository;
import com.ticketmagpie.infrastructure.persistence.TicketRepository;

@Controller
public class MainController {

  @Autowired
  private ConcertRepository concertRepository;

  @Autowired
  private TicketRepository ticketRepository;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("concerts", concertRepository.getAllConcerts());
    return "index";
  }

  @RequestMapping("/user/home")
  public String userHome() {
    return "user";
  }

  @RequestMapping("/user/book")
  public String userBook(@RequestParam String id, Model model) {
    model.addAttribute("concertid", id);
    return "bookingform";
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
          @RequestParam("concertid") Integer concertId) {
    Ticket ticket =
        new Ticket(concertId, firstName, lastName, address1, address2, address3, postCode, country);
    int savedTicketId = ticketRepository.save(ticket);
    return "redirect:/ticket?id=" + savedTicketId;
  }

  @RequestMapping("/ticket")
  public String ticket(@RequestParam Integer id, Model model) {
    Ticket ticket = ticketRepository.get(id);
    model.addAttribute("ticket", ticket);
    model.addAttribute("concert", concertRepository.get(ticket.getConcertId()));
    return "ticket";
  }
}
