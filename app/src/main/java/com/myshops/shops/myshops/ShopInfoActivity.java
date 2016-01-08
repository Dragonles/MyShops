package com.myshops.shops.myshops;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myshops.shops.bean.CircleImageView;
import com.myshops.shops.untils.ActionSheetDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;


public class ShopInfoActivity extends AppCompatActivity {

    private RelativeLayout rly_name, rly_phone, rly_zhuying,rly_mima;
    private TextView tv_name,tv_phone,tv_zhuying;
    String searchC;
    EditText et_search,et_oldpwd,et_newpwd;
    CircleImageView img;
    private Uri photoUri;
    private final int PIC_FROM_CAMERA = 1;
    private final int PIC_FROM＿LOCALPHOTO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_info);

        rly_name = (RelativeLayout) findViewById(R.id.rly_name);
        rly_phone = (RelativeLayout) findViewById(R.id.rly_phone);
        rly_zhuying = (RelativeLayout) findViewById(R.id.rly_zhuying);
        rly_mima = (RelativeLayout) findViewById(R.id.rly_mima);
        tv_name = (TextView) findViewById(R.id.ib_shopinfo_shopname_go);
        tv_phone = (TextView) findViewById(R.id.ib_settle_invoice_go);
        tv_zhuying = (TextView) findViewById(R.id.ib_shopinfo_sellwhat_go);
        img=(CircleImageView)findViewById(R.id.imgs);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActionSheetDialog(ShopInfoActivity.this)
                        .builder()
                        .setTitle("选项")
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("拍摄照片", ActionSheetDialog.SheetItemColor.Red,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        doHandlerPhoto(PIC_FROM_CAMERA);// 用户点击了从照相机获取
                                       // onActivityResult(PIC_FROM_CAMERA,0,null);
                                    }
                                })
                        .addSheetItem("选取本地", ActionSheetDialog.SheetItemColor.Red,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        doHandlerPhoto(PIC_FROM＿LOCALPHOTO);
                                    }
                                }).show();
            }
        });
        rly_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(ShopInfoActivity.this);
                LayoutInflater inflater = (LayoutInflater) ShopInfoActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialogview, null);
                dialog.setView(layout);
                et_search = (EditText) layout.findViewById(R.id.searchC);
                dialog.setTitle("输入更改店铺名");
                dialog.setIcon(android.R.drawable.btn_radio);
                dialog.setCancelable(true);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        searchC = et_search.getText().toString();
                        tv_name.setText(searchC);
                    }
                });

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });

        rly_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ShopInfoActivity.this);
                LayoutInflater inflater = (LayoutInflater) ShopInfoActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialogview, null);
                dialog.setView(layout);
                et_search = (EditText) layout.findViewById(R.id.searchC);
                dialog.setTitle("输入更改的手机号码");
                dialog.setIcon(android.R.drawable.btn_radio);
                dialog.setCancelable(true);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        searchC = et_search.getText().toString();
                        tv_phone.setText(searchC);
                    }
                });

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });

        rly_zhuying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(ShopInfoActivity.this);
                LayoutInflater inflater = (LayoutInflater) ShopInfoActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialogview, null);
                dialog.setView(layout);
                et_search = (EditText) layout.findViewById(R.id.searchC);
                dialog.setTitle("输入更改的主营项目");
                dialog.setIcon(android.R.drawable.btn_radio);
                dialog.setCancelable(true);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        searchC = et_search.getText().toString();
                        tv_zhuying.setText(searchC);
                    }
                });

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });

        rly_mima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ShopInfoActivity.this);
                LayoutInflater inflater = (LayoutInflater) ShopInfoActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.dialogview2, null);
                dialog.setView(layout);
                et_oldpwd = (EditText) layout.findViewById(R.id.et_oldpwd);
                et_newpwd = (EditText) layout.findViewById(R.id.et_newpwd);
                dialog.setTitle("输入更改的密码");
                dialog.setIcon(android.R.drawable.btn_radio);
                dialog.setCancelable(true);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });

    }

    private void doHandlerPhoto(int type) {
        try {
            // 保存裁剪后的图片文件
            File pictureFileDir = new File(
                    Environment.getExternalStorageDirectory(), "/maimai");
            if (!pictureFileDir.exists()) {
                pictureFileDir.mkdirs();
            }
            int rand=(int)(Math.random()*100000);
            Log.i("ran",rand+"");
            File picFile = new File(pictureFileDir, rand+".jpeg");
            if (!picFile.exists()) {
                picFile.createNewFile();
                Log.i("imgs",picFile.toString());
            }
            photoUri = Uri.fromFile(picFile);
            Log.i("img",photoUri.toString());
            if (type == PIC_FROM＿LOCALPHOTO) {
                Intent intent = getCropImageIntent();
                startActivityForResult(intent, PIC_FROM＿LOCALPHOTO);
            } else {
                Intent cameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(cameraIntent, PIC_FROM_CAMERA);
            }

        } catch (Exception e) {
            Log.i("HandlerPicError", "处理图片出现错误");
        }
    }

    /**
     * 调用图片剪辑程序
     */
    public Intent getCropImageIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        setIntentParams(intent);
        return intent;
    }

    /**
     * 启动裁剪
     */
    private void cropImageUriByTakePhoto() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoUri, "image/*");
        setIntentParams(intent);
        startActivityForResult(intent, PIC_FROM＿LOCALPHOTO);
    }

    /**
     * 设置公用参数
     */
    private void setIntentParams(Intent intent)
    {
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        intent.putExtra("noFaceDetection", true); // no face detection
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case PIC_FROM_CAMERA: // 拍照
                try
                {
                    cropImageUriByTakePhoto();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case PIC_FROM＿LOCALPHOTO:
                try
                {
                    if (photoUri != null)
                    {
                        Bitmap bitmap = decodeUriAsBitmap(photoUri);
                        img.setImageBitmap(bitmap);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }

    private Bitmap decodeUriAsBitmap(Uri uri)
    {
        Bitmap bitmap = null;
        try
        {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

}
