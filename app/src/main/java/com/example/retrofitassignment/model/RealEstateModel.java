package com.example.retrofitassignment.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class RealEstateModel implements Serializable {
  private @Json(name = "price")
  String mPrice;
  private @Json(name = "id")
  String mId;
  private @Json(name = "type")
  String mType;
  private @Json(name = "img_src")
  String mImage;
  
  public String getType() {
    return mType;
  }
  
  public void setType(String mType) {
    this.mType = mType;
  }
  
  public String getImage() {
    return mImage;
  }
  
  public void setImage(String mImage) {
    this.mImage = mImage;
  }
  
  public String getPrice() {
    return mPrice;
  }
  
  public void setPrice(String mPrice) {
    this.mPrice = mPrice;
  }
  
  public String getId() {
    return mId;
  }
  
  public void setId(String mId) {
    this.mId = mId;
  }
}
