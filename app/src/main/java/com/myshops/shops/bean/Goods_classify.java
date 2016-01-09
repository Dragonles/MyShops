package com.myshops.shops.bean;

/*
 * Created by Administrator on 2016/1/7.
 */
public class Goods_classify {
    private String classify_name;
    private String classify_count;

    public Goods_classify(){

    }

    public Goods_classify(String classify_name, String classify_count) {
        this.classify_name = classify_name;
        this.classify_count = classify_count;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public String getClassify_count() {
        return classify_count;
    }

    public void setClassify_count(String classify_count) {
        this.classify_count = classify_count;
    }

    @Override
    public String toString() {
        return "Goods_classify{" +
                "classify_name='" + classify_name + '\'' +
                ", classify_count='" + classify_count + '\'' +
                '}';
    }
}
