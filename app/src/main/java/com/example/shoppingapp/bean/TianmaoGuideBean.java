package com.example.shoppingapp.bean;

public class TianmaoGuideBean {
    private String title;
    private String url;

    public TianmaoGuideBean(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        String str;
        String[] titles = title.split(" ");
        if (title.contains("x")) {
            str = titles[1];
        } else if (title.contains(" ")) {
            str = titles[0];
        } else {
            str = title;
        }
        return str;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
