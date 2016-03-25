package com.ticketmagpie;

public class Ticket {
  private Integer id;
  private Integer concertId;
  private String firstName;
  private String lastName;
  private String address1;
  private String address2;
  private String address3;
  private String postcode;
  private String country;

  public Ticket(String concertId, String firstName, String lastName, String address1, String address2, String address3, String postcode, String country) {
    this.concertId = Integer.valueOf(concertId);
    this.firstName = firstName;
    this.lastName = lastName;
    this.address1 = address1;
    this.address2 = address2;
    this.address3 = address3;
    this.postcode = postcode;
    this.country = country;
  }

  public Integer getId() {
    return id;
  }

  public Integer getConcertId() {
    return concertId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress1() {
    return address1;
  }

  public String getAddress2() {
    return address2;
  }

  public String getAddress3() {
    return address3;
  }

  public String getPostcode() {
    return postcode;
  }

  public String getCountry() {
    return country;
  }

  @Override
  public String toString() {
    return "Ticket{" +
            "id=" + id +
            ", concertId=" + concertId +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address1='" + address1 + '\'' +
            ", address2='" + address2 + '\'' +
            ", address3='" + address3 + '\'' +
            ", postcode='" + postcode + '\'' +
            ", country='" + country + '\'' +
            '}';
  }
}

