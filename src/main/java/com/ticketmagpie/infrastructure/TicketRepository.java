package com.ticketmagpie.infrastructure;

import com.ticketmagpie.Concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Component
public class TicketRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Concert> getAllConcerts() {
    return jdbcTemplate.queryForList("SELECT * FROM concerts")
        .stream()
        .map(this::toConcert)
        .collect(toList());
  }

  private Concert toConcert(Map<String, Object> row) {
    return new Concert(
        (Integer) row.get("id"),
        (String) row.get("band"),
        (String) row.get("description"),
        (String) row.get("image_url")
    );
  }
}
