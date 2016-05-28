package com.ticketmagpie.infrastructure.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ticketmagpie.Comment;

@Component
public class CommentRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void save(Comment comment) {
    jdbcTemplate.update("INSERT INTO comments (username, concert_id, comment_text) VALUES (?,?,?)",
        comment.getUser(),
        comment.getConcertId(),
        comment.getText()
    );
  }

  public List<Comment> getAllForConcert(Integer concertId) {
    return jdbcTemplate.query("SELECT * FROM comments WHERE concert_id=?", (rs, rowNum) -> toComment(rs), concertId);
  }

  private Comment toComment(ResultSet rs) throws SQLException {
    return new Comment(
        rs.getInt("concert_id"),
        rs.getString("username"),
        rs.getString("comment_text")
    );
  }
}
