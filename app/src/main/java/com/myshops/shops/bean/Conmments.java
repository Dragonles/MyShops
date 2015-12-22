package com.myshops.shops.bean;

/**
 * Created by zyh on 2015/12/17.
 */
public class Conmments {
    private int usericon;  //用户头像
    private String username;  //用户昵称
    private String userlevel;  //用户等级
    private String usercomments;  //用户评价
    private String buyproduct; //购买的商品
    private String setmeal; //套餐
    private String date; //评价时间
    private String comment;//评论状态
    private int shuliang; //购买的商品数量
    private String danjia; //商品单价


    public Conmments(int usericon, String username, String userlevel, String usercomments, String buyproduct, String setmeal, String date) {
        this.usericon = usericon;
        this.username = username;
        this.userlevel = userlevel;
        this.usercomments = usercomments;
        this.buyproduct = buyproduct;
        this.setmeal = setmeal;
        this.date = date;
    }

    public Conmments(int usericon, String username, String userlevel, String usercomments, String buyproduct, String setmeal, String date, String comment, int shuliang, String danjia) {
        this.usericon = usericon;
        this.username = username;
        this.userlevel = userlevel;
        this.usercomments = usercomments;
        this.buyproduct = buyproduct;
        this.setmeal = setmeal;
        this.date = date;
        this.comment = comment;
        this.shuliang = shuliang;
        this.danjia = danjia;
    }

    public Conmments(int usericon, String username, String userlevel, String usercomments, String buyproduct, String setmeal, String date, String comment) {
        this.usericon = usericon;
        this.username = username;
        this.userlevel = userlevel;
        this.usercomments = usercomments;
        this.buyproduct = buyproduct;
        this.setmeal = setmeal;
        this.date = date;
        this.comment = comment;
    }

    public Conmments(String username, String userlevel, String usercomments, String buyproduct, String setmeal, String date, String comment) {
        this.username = username;
        this.userlevel = userlevel;
        this.usercomments = usercomments;
        this.buyproduct = buyproduct;
        this.setmeal = setmeal;
        this.date = date;
        this.comment = comment;
    }

    public Conmments(String username, String danjia, int shuliang, String comment, String buyproduct) {
        this.username = username;
        this.danjia = danjia;
        this.shuliang = shuliang;
        this.comment = comment;
        this.buyproduct = buyproduct;
    }

    @Override
    public String toString() {
        return "Conmments{" +
                "usericon=" + usericon +
                ", username='" + username + '\'' +
                ", userlevel='" + userlevel + '\'' +
                ", usercomments='" + usercomments + '\'' +
                ", buyproduct='" + buyproduct + '\'' +
                ", setmeal='" + setmeal + '\'' +
                ", date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public int getShuliang() {
        return shuliang;
    }

    public void setShuliang(int shuliang) {
        this.shuliang = shuliang;
    }

    public String getDanjia() {
        return danjia;
    }

    public void setDanjia(String danjia) {
        this.danjia = danjia;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUsericon() {
        return usericon;
    }

    public void setUsericon(int usericon) {
        this.usericon = usericon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel;
    }

    public String getUsercomments() {
        return usercomments;
    }

    public void setUsercomments(String usercomments) {
        this.usercomments = usercomments;
    }

    public String getBuyproduct() {
        return buyproduct;
    }

    public void setBuyproduct(String buyproduct) {
        this.buyproduct = buyproduct;
    }

    public String getSetmeal() {
        return setmeal;
    }

    public void setSetmeal(String setmeal) {
        this.setmeal = setmeal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Conmments(String username, String userlevel, String usercomments, String buyproduct, String setmeal, String date) {
        this.username = username;
        this.userlevel = userlevel;
        this.usercomments = usercomments;
        this.buyproduct = buyproduct;
        this.setmeal = setmeal;
        this.date = date;
    }
}
