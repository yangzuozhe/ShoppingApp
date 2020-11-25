package com.example.shoppingapp.bean;

public class TianmaoSearchBean {
    private String intentUrl;
    private String imageUrl;
    private String price;
    private String title;
    private String shopName;
    private String shopUrl;
    private String status;

    public TianmaoSearchBean(String intentUrl, String imageUrl, String price, String title, String shopName, String shopUrl, String status) {
        this.intentUrl = intentUrl;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

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
              ", imageUrl='" + imageUrl + '\'' +
              ", price='" + price + '\'' +
              ", title='" + title + '\'' +
              ", shopName='" + shopName + '\'' +
              ", shopUrl='" + shopUrl + '\'' +
              ", status='" + status + '\'' +
              '}';
   }
}
