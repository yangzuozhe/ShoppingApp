package com.example.shoppingapp.bean;

import java.io.Serializable;

public class WhatMaiProductBean extends BaseBean implements Serializable {
    private String picture;
    private String title;
    private String price;
    private String mark;

    public WhatMaiProductBean(String pictureUrl, String title, String price, String mark) {
        this.picture = pictureUrl;
        this.title = title;
        this.price = price;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "TIanMaoProductBean{" +
                "pictureUrl='" + picture + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", mark='" + mark + '\'' +
                '}' + "\n";
    }

    @Override
    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setPrice(String price) {
        this.price = price;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String getPicture() {
        return "https:" + picture;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPrice() {
        return price;
    }

    public String getMark() {
        return mark;
    }
}
