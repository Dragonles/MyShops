package com.myshops.shops.bean;

/**
 * Created by Administrator on 2015/12/17.
 */
public class Gouwuche {
    private int img;
    private String dianppu_name;
    private String shangping_name;
    private String price;
    private String count;

    public void Gouwuche(){

    }

    public Gouwuche(String dianppu_name, String shangping_name, String price, String count) {
        this.dianppu_name = dianppu_name;
        this.shangping_name = shangping_name;
        this.price = price;
        this.count = count;
    }
    public Gouwuche(int img, String dianppu_name, String shangping_name, String price, String count) {
        this.img = img;
        this.dianppu_name = dianppu_name;
        this.shangping_name = shangping_name;
        this.price = price;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Gouwuche{" +
                "img=" + img +
                ", dianppu_name='" + dianppu_name + '\'' +
                ", shangping_name='" + shangping_name + '\'' +
                ", price='" + price + '\'' +
                ", count='" + count + '\'' +
                '}';
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDianppu_name() {
        return dianppu_name;
    }

    public void setDianppu_name(String dianppu_name) {
        this.dianppu_name = dianppu_name;
    }

    public String getShangping_name() {
        return shangping_name;
    }

    public void setShangping_name(String shangping_name) {
        this.shangping_name = shangping_name;
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
}
