package com.ticketmagpie;

public class Comment {
  private final Integer concertId;
  private final String user;
  private final String text;

  public Comment(Integer concertId, String user, String text) {
    this.concertId = concertId;
    this.user = user;
    this.text = text;
  }

  public Integer getConcertId() {
    return concertId;
  }

  public String getUser() {
    return user;
  }

  public String getText() {
    return text;
  }
}
