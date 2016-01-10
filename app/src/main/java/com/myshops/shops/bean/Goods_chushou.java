package com.myshops.shops.bean;

/**
 * Created by Administrator on 2015/12/23.
 */
public class Goods_chushou {
    private String img; //图片
    private String goodsId; //id
    private String goodsName; //商品名
    private String marketPrice; //市场价
    private String shopPrice;  //现价
    private String saleCount; //销售数量
    private String isBook;  //预定
    private String goodsStock; //库存
    private String creatTime; //创建时间

    public void Goods_chushou(){

    }

    public Goods_chushou(String img, String goodsId, String goodsName, String marketPrice, String shopPrice, String saleCount, String isBook, String goodsStock, String creatTime) {
        this.img = img;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.marketPrice = marketPrice;
        this.shopPrice = shopPrice;
        this.saleCount = saleCount;
        this.isBook = isBook;
        this.goodsStock = goodsStock;
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "Goods_chushou{" +
                "img='" + img + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", marketPrice='" + marketPrice + '\'' +
                ", shopPrice='" + shopPrice + '\'' +
                ", saleCount='" + saleCount + '\'' +
                ", isBook='" + isBook + '\'' +
                ", goodsStock='" + goodsStock + '\'' +
                ", creatTime='" + creatTime + '\'' +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(String saleCount) {
        this.saleCount = saleCount;
    }

    public String getIsBook() {
        return isBook;
    }

    public void setIsBook(String isBook) {
        this.isBook = isBook;
    }

    public String getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(String goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }
}
