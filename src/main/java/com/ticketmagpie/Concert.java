package com.ticketmagpie;

public class Concert {
  private Integer id;
  private String band;
  private String date;
  private String description;
  private String imageUrl;

  public Concert(Integer id, String band, String date, String description, String imageUrl) {
    this.id = id;
    this.band = band;
    this.date = date;
    this.description = description;
    this.imageUrl = imageUrl;
  }

  public String getBand() {
    return band;
  }

  public String getDescription() {
    return description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getDate() {
    return date;
  }

  public Integer getId() { return id; }

  @Override
  public String toString() {
    return "Concert{" +
        "id='" + id + '\'' +
        ", band='" + band + '\'' +
        ", description='" + description + '\'' +
        ", imageUrl='" + imageUrl + '\'' +
        '}';
  }
}
