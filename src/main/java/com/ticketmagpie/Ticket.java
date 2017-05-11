package com.ticketmagpie;

public class Ticket {
    private Integer id;
    private Concert concert;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String address3;
    private String postcode;
    private String country;
    private String user;
    private String paymentmethod;
    private String cardnumber;
    private String cvv2;
    private String expirydate;

    public Ticket(Integer id, Concert concert, String firstName, String lastName, String address1, String address2, String address3, String postcode, String country, String user, String paymentmethod, String cardnumber, String ccv2, String expirydate) {
        this(concert, firstName, lastName, address1, address2, address3, postcode, country, paymentmethod, cardnumber, ccv2, expirydate, user);
        this.id = id;
    }

    public Ticket(Concert concert, String firstName, String lastName, String address1, String address2, String address3, String postcode, String paymentmethod, String cardnumber, String ccv2, String expirydate, String country, String user) {
        this.concert = concert;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.postcode = postcode;
        this.country = country;
        this.paymentmethod = paymentmethod;
        this.cardnumber = cardnumber;
        this.cvv2 = ccv2;
        this.expirydate = expirydate;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public Concert getConcert() {
        return concert;
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

    public String getUser() {
        return user;
    }

    public String getPaymentmethod() {
        return paymentmethod; }

    public String getCardnumber() {
        return cardnumber; }

    public String getCvv2() {
        return cvv2; }

    public String getExpirydate() {
        return expirydate; }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", concert=" + concert +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", postcode='" + postcode + '\'' +
                ", country='" + country + '\'' +
                ", paymentmethod='" + paymentmethod + '\'' +
                ", cardnumber='" + cardnumber + '\'' +
                ", cvv2='" + cvv2 + '\'' +
                ", expirydate='" + expirydate + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}

