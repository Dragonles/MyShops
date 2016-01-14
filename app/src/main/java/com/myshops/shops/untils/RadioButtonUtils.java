package com.myshops.shops.untils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RadioButton;

import com.myshops.shops.myshops.R;

/**
 * Created by Z on 2016/1/10.
 */
public class RadioButtonUtils extends RadioButton {

    private Drawable defaultDrawable,choseDrawable;//表示默认的图片对象与选中的图片对象

    private Context mContext;


    public RadioButtonUtils(Context context) {

        super(context);

        mContext=context;

        init();

    }

    public RadioButtonUtils(Context context, AttributeSet attributeSet) {

        super(context,attributeSet);

        mContext=context;

        init();

    }

	/*

	 * 初始化方法 加载图片 设置图片在edittext的右边

	 */

    public void init(){

        //加载drawable文件夹下的图片

        //defaultDrawable=mContext.getResources().getDrawable(R.drawable.qu_tu);
        Bitmap originalBitmap = BitmapFactory.decodeResource(mContext.getResources(),
                R.drawable.qu_tu);
        int originalWidth = originalBitmap.getWidth();
        int originalHeight = originalBitmap.getHeight();
        int newWidth = 150;
        int newHeight = 150; // 自定义 高度 暂时没用


        float scale = ((float) newHeight) / originalHeight;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap changedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0,
                originalWidth, originalHeight, matrix, true);
        defaultDrawable = new BitmapDrawable(changedBitmap);

        //changedBitmap
        //changedImageView.setImageBitmap(changedBitmap);//你的 ImageView

        //defaultDrawable.setBounds(0, 0,30,30);

        choseDrawable=mContext.getResources().getDrawable(R.drawable.qu_tu);
        choseDrawable = new BitmapDrawable(changedBitmap);
        //文本改变监听 当文本改变时将图片切换成选中的图片``    qa


//
        setDrawable();

    }

	/*

	 设置edittext中的图片

	 */

    public void setDrawable(){

        if(length()>1){//获取edittext中的文本长度 如果大于1显示选中的图片
            choseDrawable.setBounds(0,1000,0,0);
            setCompoundDrawablesWithIntrinsicBounds(null, choseDrawable, null, null);

        }else{

            //设置文字与图片摆放的位置 参数表示左上右下摆放的图片drawable对象

            setCompoundDrawablesWithIntrinsicBounds(null, defaultDrawable, null, null);

        }

    }

    //触摸的回调方法

    @Override

    public boolean onTouchEvent(MotionEvent event) {

        if(choseDrawable!=null && event.getAction()==MotionEvent.ACTION_UP){//获取触摸事件的动作  如果手指离开屏幕

            // 获取触摸区域的x y的坐标

            int eventX=(int) event.getRawX();

            int eventY=(int) event.getRawY();

            Rect rect=new Rect();

            getGlobalVisibleRect(rect);

            rect.left=rect.right-50;

            if(rect.contains(eventX, eventY)){

                //清空edittext的数据

                setText("");

            }

        }

        return super.onTouchEvent(event);

    }
}
