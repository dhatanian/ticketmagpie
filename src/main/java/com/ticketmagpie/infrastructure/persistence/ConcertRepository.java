package com.ticketmagpie.infrastructure.persistence;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
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
        rs.getString("image_url"),
        getBytesFromBlob(rs.getBlob("image_blob")));
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM concerts WHERE id=?", id);
  }

  public void save(Concert concert) {
    jdbcTemplate.update("INSERT INTO concerts (band, concert_date, description, image_blob) VALUES (?,?,?,?)",
        new Object[] {
            concert.getBand(),
            concert.getDate(),
            concert.getDescription(),
            new SqlLobValue(concert.getImageBlob())
        },
        new int[] {
            Types.VARCHAR,
            Types.VARCHAR,
            Types.VARCHAR,
            Types.BLOB
        }
    );
  }

  private byte[] getBytesFromBlob(Blob blob) throws SQLException {
    if (blob == null) {
      return null;
    } else {
      return blob.getBytes(1, (int) blob.length());
    }
  }
}
