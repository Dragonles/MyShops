package com.myshops.shops.bean;

/**
 * Created by Administrator on 2015/12/21.
 */
public class Order  {
    private String username; //买家名称
    private String goodsName;//商品名称
    private String goodsNums;//购买数量
    private String totalMoney;//交易金额
    private String orderStatus;//交易状态
    private String goodsThums;//商品图片
    private String createTime;  //订单创建时间
    private String isRefund;   //是否退款  0:退款  1不退款

    public String getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(String isRefund) {
        this.isRefund = isRefund;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order(String username, String goodsName, String goodsNums, String totalMoney, String orderStatus, String goodsThums, String createTime, String isRefund) {
        this.username = username;
        this.goodsName = goodsName;
        this.goodsNums = goodsNums;
        this.totalMoney = totalMoney;
        this.orderStatus = orderStatus;
        this.goodsThums = goodsThums;
        this.createTime = createTime;
        this.isRefund = isRefund;
    }

    public Order() {
    }
}
