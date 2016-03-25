package com.ticketmagpie.infrastructure;

import java.util.List;

import com.ticketmagpie.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ticketmagpie.Concert;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/user/book")
    public String userBook(@RequestParam String id) {
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
             @RequestParam("concertid") String concertid)
     {
        new Ticket(concertid, firstName, lastName, address1, address2, address3, postCode, country );
        return "paymentform";

    }

}