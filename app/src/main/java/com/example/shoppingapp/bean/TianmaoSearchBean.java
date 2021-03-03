package com.example.shoppingapp.bean;

import java.io.Serializable;

public class TianmaoSearchBean extends BaseBean implements Serializable {
    private String intentUrl;
    private String picture;
    private String price;
    private String title;
    private String shopName;
    private String shopUrl;
    private String status;

    public TianmaoSearchBean(String intentUrl, String imageUrl, String price, String title, String shopName, String shopUrl, String status) {
        this.intentUrl = intentUrl;
        this.picture = imageUrl;
        this.price = price;
        this.title = title;
        this.shopName = shopName;
        this.shopUrl = shopUrl;
        this.status = status;
    }

    public String getIntentUrl() {
        return intentUrl;
    }

    public void setIntentUrl(String intentUrl) {
        this.intentUrl = intentUrl;
    }

    @Override
    public String getPicture() {
        return "https:"+ picture;
    }

    @Override
    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String getPrice() {
        return price;
    }

    @Override
    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   @Override
   public String toString() {
      return "TaoBaoSearchBean{" +
              "intentUrl='" + intentUrl + '\'' +
              ", imageUrl='" + picture + '\'' +
              ", price='" + price + '\'' +
              ", title='" + title + '\'' +
              ", shopName='" + shopName + '\'' +
              ", shopUrl='" + shopUrl + '\'' +
              ", status='" + status + '\'' +
              '}';
   }
}
