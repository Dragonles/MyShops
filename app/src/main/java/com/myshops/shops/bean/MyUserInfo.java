package com.myshops.shops.bean;

/**
 * Created by 陈增庆 on 2016/1/15.
 */
public class MyUserInfo {
    private String id,name,userPoto;

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

    public String getUserPoto() {
        return userPoto;
    }

    public void setUserPoto(String userPoto) {
        this.userPoto = userPoto;
    }

    public MyUserInfo() {
    }

    public MyUserInfo(String id, String name, String userPoto) {
        this.id = id;
        this.name = name;
        this.userPoto = userPoto;
    }
}
