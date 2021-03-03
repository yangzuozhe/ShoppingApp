package com.example.shoppingapp.bean;

/**
 * 基本的ben类
 *
 * @author HB.yangzuozhe
 * @date 2021-03-03
 */
abstract public class BaseBean {
    private String picture;
    private String title;
    private String price;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
