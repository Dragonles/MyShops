package com.myshops.shops.bean;

/**
 * Created by Administrator on 2015/12/25.
 */
public class Tianjiashangpin {
    private String add_img;
    private String add_name;
    private String add_marketPrice;
    private String add_shopPrice;
    private String saleCount;
    private String isBlok;
    private String goodsStock;
    private String createTime;

    public void Tinajiashangpin(){

    }

    public Tianjiashangpin(String add_img, String add_name, String add_marketPrice, String add_shopPrice, String saleCount, String isBlok, String goodsStock, String createTime) {
        this.add_img = add_img;
        this.add_name = add_name;
        this.add_marketPrice = add_marketPrice;
        this.add_shopPrice = add_shopPrice;
        this.saleCount = saleCount;
        this.isBlok = isBlok;
        this.goodsStock = goodsStock;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Tianjiashangpin{" +
                "add_img='" + add_img + '\'' +
                ", add_name='" + add_name + '\'' +
                ", add_marketPrice='" + add_marketPrice + '\'' +
                ", add_shopPrice='" + add_shopPrice + '\'' +
                ", saleCount='" + saleCount + '\'' +
                ", isBlok='" + isBlok + '\'' +
                ", goodsStock='" + goodsStock + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public String getAdd_img() {
        return add_img;
    }

    public void setAdd_img(String add_img) {
        this.add_img = add_img;
    }

    public String getAdd_name() {
        return add_name;
    }

    public void setAdd_name(String add_name) {
        this.add_name = add_name;
    }

    public String getAdd_marketPrice() {
        return add_marketPrice;
    }

    public void setAdd_marketPrice(String add_marketPrice) {
        this.add_marketPrice = add_marketPrice;
    }

    public String getAdd_shopPrice() {
        return add_shopPrice;
    }

    public void setAdd_shopPrice(String add_shopPrice) {
        this.add_shopPrice = add_shopPrice;
    }

    public String getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(String saleCount) {
        this.saleCount = saleCount;
    }

    public String getIsBlok() {
        return isBlok;
    }

    public void setIsBlok(String isBlok) {
        this.isBlok = isBlok;
    }

    public String getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(String goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
