package com.example.shoppingapp.bean;

public class TianmaoBannerBean  {
    private String url;
    private String imageUrl;

    public TianmaoBannerBean(String url, String imageUrl) {
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
