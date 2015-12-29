package com.myshops.shops.untils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zyh on 2015/12/24.
 * Toast 工具类
 */
public class ToastUtil {

    /**
     * Long型Toast
     * */
    public static void ToastLongs(Context context, String mesg) {
        Toast.makeText(context, mesg, Toast.LENGTH_LONG).show();
    }

    /**
     * Short型Toast
     * */
    public static void ToastShorts(Context context, String mesg) {
        Toast.makeText(context, mesg, Toast.LENGTH_SHORT).show();
    }

}
