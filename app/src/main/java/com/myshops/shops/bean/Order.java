package com.myshops.shops.bean;

/**
 * Created by Administrator on 2015/12/21.
 */
public class Order  {
    private String uname; //买家名称
    private String flag; //发货状态
    private String goodname;//商品名称
    private String numbers;//购买数量
    private String mmoney;//交易金额
    private String flag_satd;//交易状态
    private int goodimg;//商品图片

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getFlag_satd() {
        return flag_satd;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getMmoney() {
        return mmoney;
    }

    public void setMmoney(String mmoney) {
        this.mmoney = mmoney;
    }

    public String isFlag_satd() {
        return flag_satd;
    }

    public int getGoodimg() {
        return goodimg;
    }

    public void setGoodimg(int goodimg) {
        this.goodimg = goodimg;
    }

    public void setFlag_satd(String flag_satd) {
        this.flag_satd = flag_satd;
    }

    public Order(String uname, String flag_satd, String mmoney, String numbers, String goodname, String flag,int goodimg) {
        this.uname = uname;
        this.flag_satd = flag_satd;
        this.mmoney = mmoney;
        this.numbers = numbers;
        this.goodname = goodname;
        this.flag = flag;
        this.goodimg=goodimg;
    }
}
