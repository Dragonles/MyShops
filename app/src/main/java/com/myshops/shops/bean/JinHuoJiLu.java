package com.myshops.shops.bean;

/**
 * Created by Administrator on 2015/12/18.
 */
public class JinHuoJiLu {
    private int img;
    private String title;
    private String color;
    private String size;
    private String data;
    private String price;
    private String count;
    private String allPrice;
    public void JinHuoJiLu(){

    }

    public JinHuoJiLu(int img, String title, String color, String size, String data, String price, String count, String allPrice) {
        this.img = img;
        this.title = title;
        this.color = color;
        this.size = size;
        this.data = data;
        this.price = price;
        this.count = count;
        this.allPrice = allPrice;
    }

    @Override
    public String toString() {
        return "JinHuoJiLu{" +
                "img=" + img +
                ", title='" + title + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", data='" + data + '\'' +
                ", price='" + price + '\'' +
                ", count='" + count + '\'' +
                ", allPrice='" + allPrice + '\'' +
                '}';
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }
}
