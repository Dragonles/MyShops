package com.myshops.shops.bean;

/**
 * Created by Administrator on 2015/12/18.
 */
public class Shouruyuzhichu {
    private int img;
    private String shangpin_title;
    private String shangpin_color;
    private String shagpin_size;
    private String shangpin_price_first;
    private Float price;
    private String shangpin_price_two;
    private String shangpin_count;
    private String type;

    public Shouruyuzhichu() {

    }

    public void Shourutuzhichu(){

    }

    public Shouruyuzhichu(int img, String shangpin_title, String shangpin_color, String shagpin_size, String shangpin_price_first, String shangpin_price_two, String shangpin_count, String type) {
        this.img = img;
        this.shangpin_title = shangpin_title;
        this.shangpin_color = shangpin_color;
        this.shagpin_size = shagpin_size;
        this.shangpin_price_first = shangpin_price_first;
        this.shangpin_price_two = shangpin_price_two;
        this.shangpin_count = shangpin_count;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Shouruyuzhichu{" +
                "img=" + img +
                ", shangpin_title='" + shangpin_title + '\'' +
                ", shangpin_color='" + shangpin_color + '\'' +
                ", shagpin_size='" + shagpin_size + '\'' +
                ", shangpin_price_first='" + shangpin_price_first + '\'' +
                ", shangpin_price_two='" + shangpin_price_two + '\'' +
                ", shangpin_count='" + shangpin_count + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getShangpin_title() {
        return shangpin_title;
    }

    public void setShangpin_title(String shangpin_title) {
        this.shangpin_title = shangpin_title;
    }

    public String getShangpin_color() {
        return shangpin_color;
    }

    public void setShangpin_color(String shangpin_color) {
        this.shangpin_color = shangpin_color;
    }

    public String getShagpin_size() {
        return shagpin_size;
    }

    public void setShagpin_size(String shagpin_size) {
        this.shagpin_size = shagpin_size;
    }

    public String getShangpin_price_first() {
        return shangpin_price_first;
    }

    public void setShangpin_price_first(String shangpin_price_first) {
        this.shangpin_price_first = shangpin_price_first;
    }

    public String getShangpin_price_two() {
        return shangpin_price_two;
    }

    public void setShangpin_price_two(String shangpin_price_two) {
        this.shangpin_price_two = shangpin_price_two;
    }

    public String getShangpin_count() {
        return shangpin_count;
    }

    public void setShangpin_count(String shangpin_count) {
        this.shangpin_count = shangpin_count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
