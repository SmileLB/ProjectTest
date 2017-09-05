package com.example.administrator.projecttest.bean;

import java.io.Serializable;

/**
 * Created by LiBing
 * on 2017/8/10 0010
 * describe:
 */

public class Product implements Serializable{

    private String url;
    private String content;
    private String price;
    private String oldPrice;

    public Product() {
    }

    public Product(String url, String content, String price, String oldPrice) {
        this.url = url;
        this.content = content;
        this.price = price;
        this.oldPrice = oldPrice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }
}
