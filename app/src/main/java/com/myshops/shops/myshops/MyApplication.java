package com.myshops.shops.myshops;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2015/12/22.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}