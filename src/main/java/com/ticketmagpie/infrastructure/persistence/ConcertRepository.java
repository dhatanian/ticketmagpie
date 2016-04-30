package com.ticketmagpie.infrastructure.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ticketmagpie.Concert;

@Component
public class ConcertRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Concert> getAllConcerts() {
    return jdbcTemplate.query("SELECT * FROM concerts", (rs, rowNum) -> toConcert(rs));
  }

  public Concert get(Integer concertId) {
    return jdbcTemplate.queryForObject("SELECT * FROM concerts WHERE id=?", (rs, rowNum) -> toConcert(rs), concertId);
  }

  private Concert toConcert(ResultSet rs) throws SQLException {
    return new Concert(
        rs.getInt("id"),
        rs.getString("band"),
        rs.getString("concert_date"),
        rs.getString("description"),
        rs.getString("image_url")
    );
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM concerts WHERE id=?", id);
  }
}
