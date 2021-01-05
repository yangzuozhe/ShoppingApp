package com.example.shoppingapp.Data;

import com.example.shoppingapp.bean.WhatMaiProductBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class WhatMaiData {

    public static ArrayList<WhatMaiProductBean> whatMaiProductList(String html) {
        ArrayList<WhatMaiProductBean> arrayList = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements elements = document
                .select("ul[id=feed-main-list]")
                .select("li[class=feed-row-wide ]");
        for (Element element : elements) {
            String picture = element.select("div[class=feed-block z-hor-feed]")
                    .select("div[class=z-feed-img]")
                    .select("img")
                    .attr("src");
            String title = element.select("div[class=z-feed-content]")
                    .select("h5[class=feed-block-title]")
                    .select("a[class=feed-nowrap]").text();
            String price = element.select("div[class=z-feed-content]")
                    .select("h5[class=feed-block-title]")
                    .select("div[class=z-highlight]")
                    .text();
            String mark = element.select("div[class=feed-block-descripe]")
                    .text();
            WhatMaiProductBean bean = new WhatMaiProductBean(picture, title, price, mark);
            arrayList.add(bean);
        }
        return arrayList;
    }


}
