package com.myshops.shops.bean;

public class XiaoXi {
    private String name;
    private String xiaoxi;
    private String time;
    private String number;
    private String id;
    public XiaoXi() {
    }

    public XiaoXi(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXiaoxi() {
        return xiaoxi;
    }

    public void setXiaoxi(String xiaoxi) {
        this.xiaoxi = xiaoxi;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
