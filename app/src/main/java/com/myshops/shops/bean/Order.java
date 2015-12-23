package com.myshops.shops.bean;

/**
 * Created by Administrator on 2015/12/21.
 */
public class Order  {
    private String username; //买家名称
   // private String orderStatus; //发货状态
    private String goodsName;//商品名称
    private String goodsNums;//购买数量
    private String totalMoney;//交易金额
    private int orderStatus;//交易状态
    private String goodsThums;//商品图片

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoodsThums() {
        return goodsThums;
    }

    public void setGoodsThums(String goodsThums) {
        this.goodsThums = goodsThums;
    }

    public String getGoodsNums() {
        return goodsNums;
    }

    public void setGoodsNums(String goodsNums) {
        this.goodsNums = goodsNums;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order(String username, String goodsThums, String totalMoney, String goodsNums, String goodsName, int orderStatus) {
        this.username = username;
        this.goodsThums = goodsThums;
        this.totalMoney = totalMoney;
        this.goodsNums = goodsNums;
        this.goodsName = goodsName;
        this.orderStatus = orderStatus;
    }

    public Order() {
    }
}
