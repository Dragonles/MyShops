package com.myshops.shops.bean;

/**
 * Created by Administrator on 2015/12/18.
 */
public class Xiaoshoujilu {
    private String img;
    private String shangming_name;
    private String price;
    private String data;
    private String count;
    public void Xiaoshoujilu(){

    }

    public Xiaoshoujilu(String img, String shangming_name, String price, String data, String count) {
        this.img = img;
        this.shangming_name = shangming_name;
        this.price = price;
        this.data = data;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Xiaoshoujilu{" +
                "img=" + img +
                ", shangming_name='" + shangming_name + '\'' +
                ", price='" + price + '\'' +
                ", data='" + data + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getShangming_name() {
        return shangming_name;
    }

    public void setShangming_name(String shangming_name) {
        this.shangming_name = shangming_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
