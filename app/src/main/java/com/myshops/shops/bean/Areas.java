package com.myshops.shops.bean;

/**
 * Created by 陈增庆 on 2015/12/30.
 */
public class Areas {
    String id ;
    String name;

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

    public Areas(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
