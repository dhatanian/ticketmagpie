package com.ticketmagpie;

public class Concert {
  private Integer id;
  private String band;
  private String description;
  private String imageUrl;

  public Concert(Integer id, String band, String description, String imageUrl) {
    this.id = id;
    this.band = band;
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

  public Integer getId() {    return id;  }

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
