package com.ticketmagpie;

public class Concert {
  private Integer id;
  private String band;
  private String date;
  private String description;
  private String imageUrl;
  private byte[] imageBlob;

  public Concert(Integer id, String band, String date, String description, String imageUrl, byte[] imageBlob) {
    this.id = id;
    this.band = band;
    this.date = date;
    this.description = description;
    this.imageUrl = imageUrl;
    this.imageBlob = imageBlob;
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

  public byte[] getImageBlob() {
    return imageBlob;
  }

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
