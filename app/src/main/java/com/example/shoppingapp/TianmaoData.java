package com.example.shoppingapp;

import android.util.Log;

import com.example.shoppingapp.bean.TIanMaoProductBean;
import com.example.shoppingapp.bean.TianmaoBannerBean;
import com.example.shoppingapp.bean.TianmaoGuideBean;
import com.example.shoppingapp.bean.TianmaoSearchBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * 获取淘宝网站的数据
 */
public class TianmaoData {

    public void demo(int... a) {
        System.out.println(a);
        System.out.println(a);
    }

    /**
     * 淘宝搜索的内容
     */
    public static ArrayList<TianmaoSearchBean> TianmaoSearch(String html) {
        ArrayList<TianmaoSearchBean> arrayList = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div[class=view  ]")
                .select("div[class=product  ]")
                .select("div[class=product-iWrap]");
        for (Element element : elements) {
            //商品详细网站
            String intentUrl = element
                    .select("div[class=productImg-wrap]")
                    .select("a")
                    .attr("href");
            //商品图片
            String imageUrl = element
                    .select("div[class=productImg-wrap]")
                    .select("a")
                    .select("img")
                    .attr("src");
            //商品价格
            String price = element
                    .select("p[class=productPrice]")
                    .select("em")
                    .text();
            //商品的介绍
            String title = element.select("p[class=productTitle]")
                    .select("a")
                    .text();
            //店铺名称
            String shopName = element
                    .select("div[class=productShop]")
                    .select("a[class=productShop-name]")
                    .text();
            //店铺链接
            String shopUrl = element
                    .select("div[class=productShop]")
                    .select("a[class=productShop-name]")
                    .attr("href");
            //成交信息
            String status = element.select("p[class=productStatus]")
                    .select("span")
                    .text();
            TianmaoSearchBean bean = new TianmaoSearchBean(intentUrl, imageUrl, price, title, shopName, shopUrl, status);
            arrayList.add(bean);
        }
        return arrayList;
    }

    /**
     * 天猫主页的主要种类
     *
     * @param html
     * @return
     */
    public static ArrayList<TianmaoGuideBean> TianMaoHome(String html) {
        ArrayList<TianmaoGuideBean> arrayList = new ArrayList<>();

        Document document = Jsoup.parse(html);
        Elements elements = document.select("ul[class=normal-nav clearfix]")
                .select("li");
        for (Element element : elements) {
            String title = element.select("a")
                    .text();
            String url = element.select("a")
                    .attr("href");
            TianmaoGuideBean bean = new TianmaoGuideBean(title, url);
            arrayList.add(bean);
        }
        return arrayList;
    }

    public static ArrayList<TianmaoBannerBean> TianMaoBannerBean() {
        ArrayList<TianmaoBannerBean> arrayList = new ArrayList<>();
        //鞋子
        String url = "https://detail.tmall.hk/hk/item.htm?id=628548055405&spm=875.7931836/B.2017041.8.43e94265q4fBBW&scm=1007.12144.81309.73133_0_0&pvid=ce5f9b74-cb16-4560-88a3-3069508873a8&utparam=%7B%22x_hestia_source%22:%2273133%22,%22x_object_type%22:%22item%22,%22x_hestia_subsource%22:%22default%22,%22x_mt%22:8,%22x_src%22:%2273133%22,%22x_pos%22:5,%22wh_pid%22:-1,%22x_pvid%22:%22ce5f9b74-cb16-4560-88a3-3069508873a8%22,%22scm%22:%221007.12144.81309.73133_0_0%22,%22x_object_id%22:628548055405,%22tpp_buckets%22:%222144#0#81309#0%22%7D";
        String imageUrl = "https://img.alicdn.com/imgextra/i2/2208715384557/O1CN01e7ARET1jX9UpnQ52x_!!2208715384557.jpg_430x430q90.jpg";
        TianmaoBannerBean bean = new TianmaoBannerBean(url, imageUrl);
        arrayList.add(bean);
        url = "https://detail.tmall.com/item.htm?spm=875.7931836/B.20161011.8.43e94265q4fBBW&pvid=5456cfd8-3089-426d-bff0-f4af05e09715&pos=8&acm=201509290.1003.1.1286473&id=588339707188&scm=1007.12710.81708.100200300000000";
        imageUrl = "https://img.alicdn.com/imgextra/i2/619123122/O1CN01Esg6yt1Yvv9MnQjze_!!619123122.jpg_430x430q90.jpg";
        TianmaoBannerBean bean2 = new TianmaoBannerBean(url, imageUrl);
        arrayList.add(bean2);
        url = "https://chaoshi.detail.tmall.com/item.htm?spm=875.7931836/B.20161011.9.43e94265q4fBBW&pvid=5456cfd8-3089-426d-bff0-f4af05e09715&pos=9&acm=201509290.1003.1.1286473&id=587234267079&scm=1007.12710.81708.100200300000000";
        imageUrl = "https://img.alicdn.com/imgextra/i2/725677994/O1CN01fUONbn28vImfWMTJY_!!0-item_pic.jpg_430x430q90.jpg";
        TianmaoBannerBean bean3 = new TianmaoBannerBean(url, imageUrl);
        arrayList.add(bean3);
        return arrayList;
    }

    /**
     * 这就是我们搜索后的页面的数据
     */
    public static ArrayList<TIanMaoProductBean> TianMaoProductList(String html) {
        String s = html;
        ArrayList<TIanMaoProductBean> arrayList = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div[class=view  ]")
                .select("div[class=product  ]");
        for (Element element : elements) {
            //图片地址
            String pictureUrl = element.select("div[class=productImg-wrap]")
                    .select("a")
                    .attr("href");
            //商品名称
            String title = element.select("p[class=productTitle]")
                    .select("a")
                    .attr("title");
            //商品价格
            String price = element.select("p[class=productPrice]")
                    .select("em")
                    .text();
            //月销量
            String monthSell = element.select("p[class=productStatus]")
                    .select("span")
                    .select("em")
                    .text();
//            //评论数量
//            String totalRecommendation = element.select("div[class=tb-property]")
//                    .select("div[class=tb-wrap]")
//                    .select("ul[class=tm-ind-panel]")
//                    .select("li[class=tm-ind-item tm-ind-reviewCount canClick tm-line3]")
//                    .select("div")
//                    .select("span")
//                    .text();
//            //发货地址
//            String location = element.select("div[class=tb-property]")
//                    .select("div[class=tb-wrap]")
//                    .select("div[class=tb-meta]")
//                    .select("div[class=tb-postAge]")
//                    .select("span[class=tb-deliveryAdd]")
//                    .text();
            TIanMaoProductBean bean = new TIanMaoProductBean(pictureUrl,title,price,monthSell);
            arrayList.add(bean);
        }
        Log.d("Demoxx",arrayList.toString()+"\n");
        return arrayList;
    }

    public void addData(String url, String imageUrl) {

    }
}
