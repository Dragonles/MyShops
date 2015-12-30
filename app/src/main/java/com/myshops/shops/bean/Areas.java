package com.myshops.shops.bean;

/**
 * Created by xuguojunjun on 2015/12/25.
 */
public class Areas {
    private String areaId;
    private String parentId;
    private String areaName;
    private String areaType;

    public Areas() {
    }

    public Areas(String areaId, String parentId, String areaName,String areaType) {
        this.areaId = areaId;
        this.parentId = parentId;
        this.areaName = areaName;
        this.areaType = areaType;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    //这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
    public String getPickerViewText() {
        //这里还可以判断文字超长截断再提供显示
        return areaName;
    }
}

