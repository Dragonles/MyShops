package com.myshops.shops.untils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 陈增庆 on 2015/12/21.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
