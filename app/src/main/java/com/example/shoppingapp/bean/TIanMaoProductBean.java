package com.example.shoppingapp.bean;

public class TIanMaoProductBean {
    private String pictureUrl;
    private String title;
    private String price;
    private String monthSell;

    public TIanMaoProductBean(String pictureUrl, String title, String price, String monthSell) {
        this.pictureUrl = pictureUrl;
        this.title = title;
        this.price = price;
        this.monthSell = monthSell;
    }

    @Override
    public String toString() {
        return "TIanMaoProductBean{" +
                "pictureUrl='" + pictureUrl + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", monthSell='" + monthSell + '\'' +
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

    public void setMonthSell(String monthSell) {
        this.monthSell = monthSell;
    }


    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getMonthSell() {
        return monthSell;
    }

}
