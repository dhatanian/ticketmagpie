package com.ticketmagpie.infrastructure.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ticketmagpie.Ticket;

@Component
public class TicketRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private ConcertRepository concertRepository;

  @Autowired
  private LastIdInserted lastIdInserted;

  public int save(Ticket ticket) {
    jdbcTemplate.update("INSERT INTO " +
            "tickets (concertid,  firstname,  lastname,  address1,  address2,  address3,  postcode,  country, paymentmethod, cardnumber, cvv2, expirydate, user) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);",
        ticket.getConcert().getId(),
        ticket.getFirstName(),
        ticket.getLastName(),
        ticket.getAddress1(),
        ticket.getAddress2(),
        ticket.getAddress3(),
        ticket.getPostcode(),
        ticket.getCountry(),
        ticket.getPaymentmethod(),
        ticket.getCardnumber(),
        ticket.getCvv2(),
        ticket.getExpirydate(),
        ticket.getUser()
    );

    return lastIdInserted.get();
  }

  public Ticket get(Integer id) {
    return jdbcTemplate.queryForObject("SELECT * FROM tickets WHERE id=?", (rs, rowNum) -> toTicket(rs), id);
  }

  public List<Ticket> getTicketsForUser(String user) {
    return jdbcTemplate.query("SELECT * FROM tickets WHERE user=?", (rs, rowNum) -> toTicket(rs), user);
  }

  private Ticket toTicket(ResultSet rs) throws SQLException {
    int concertId = rs.getInt("concertid");
    return new Ticket(
        rs.getInt("id"),
        concertRepository.get(concertId),
        rs.getString("firstname"),
        rs.getString("lastname"),
        rs.getString("address1"),
        rs.getString("address2"),
        rs.getString("address3"),
        rs.getString("postcode"),
        rs.getString("country"),
        rs.getString("paymentmethod"),
        rs.getString("cardnumber"),
        rs.getString("cvv2"),
        rs.getString("expirydate"),
        rs.getString("user"));
  }
}
