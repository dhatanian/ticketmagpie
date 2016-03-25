package com.ticketmagpie.infrastructure.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ticketmagpie.Ticket;

@Component
public class TicketRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private LastIdInserted lastIdInserted;

  public int save(Ticket ticket) {
    jdbcTemplate.update("INSERT INTO " +
            "tickets (concertid,  firstname,  lastname,  address1,  address2,  address3,  postcode,  country) " +
            "VALUES (?,?,?,?,?,?,?,?);",
        ticket.getConcertId(),
        ticket.getFirstName(),
        ticket.getLastName(),
        ticket.getAddress1(),
        ticket.getAddress2(),
        ticket.getAddress3(),
        ticket.getPostcode(),
        ticket.getCountry()
    );

    return lastIdInserted.get();
  }

  public Ticket get(Integer id) {
    return jdbcTemplate.queryForObject("SELECT * FROM tickets WHERE id=?", (rs, rowNum) -> toTicket(rs), id);
  }

  private Ticket toTicket(ResultSet rs) throws SQLException {
    return new Ticket(
        rs.getInt("concertid"),
        rs.getString("firstname"),
        rs.getString("lastname"),
        rs.getString("address1"),
        rs.getString("address2"),
        rs.getString("address3"),
        rs.getString("postcode"),
        rs.getString("country")
    );
  }
}
