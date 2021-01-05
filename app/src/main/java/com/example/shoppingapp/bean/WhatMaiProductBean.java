package com.example.shoppingapp.bean;

import java.io.Serializable;

public class WhatMaiProductBean implements Serializable {
    private String pictureUrl;
    private String title;
    private String price;
    private String mark;

    public WhatMaiProductBean(String pictureUrl, String title, String price, String mark) {
        this.pictureUrl = pictureUrl;
        this.title = title;
        this.price = price;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "TIanMaoProductBean{" +
                "pictureUrl='" + pictureUrl + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", mark='" + mark + '\'' +
                '}' + "\n";
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPictureUrl() {
        return "https:" + pictureUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getMark() {
        return mark;
    }
}
