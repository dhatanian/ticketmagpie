package com.ticketmagpie.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketmagpie.Comment;
import com.ticketmagpie.Ticket;
import com.ticketmagpie.infrastructure.persistence.CommentRepository;
import com.ticketmagpie.infrastructure.persistence.ConcertRepository;
import com.ticketmagpie.infrastructure.persistence.TicketRepository;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private ConcertRepository concertRepository;

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private MainController mainController;

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
          @RequestParam("paymentmethod") String paymentmethod,
          @RequestParam("cardnumber") String cardnumber,
          @RequestParam("ccv2") String ccv2,
          @RequestParam("expirydate") String expirydate,
          @RequestParam("concertid") Integer concertId,
          Authentication currentUser) {
    Ticket ticket =
        new Ticket(concertRepository.get(concertId), firstName, lastName, address1, address2, address3, postCode, country, paymentmethod, cardnumber, ccv2, expirydate, currentUser.getName());
    int savedTicketId = ticketRepository.save(ticket);
    return "redirect:/ticket?id=" + savedTicketId;
  }

  @RequestMapping("/comment")
  public String comment(@RequestParam("concertId") Integer concertId, @RequestParam("user") String user, @RequestParam("text") String text, Model model)
      throws IOException {
    Comment newComment = new Comment(concertId, user, text);
    commentRepository.save(newComment);
    return mainController.concert(concertId, model);
  }

}
