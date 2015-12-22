package com.myshops.shops.myshops;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ShopInfoActivity extends AppCompatActivity {

    private RelativeLayout rly_name, rly_phone, rly_zhuying,rly_mima;
    private TextView tv_name,tv_phone,tv_zhuying;
    private ImageView iv_shopinfo_shopheader;
    private static final int RESULT_LOAD_IMAGE =1 ;
    String searchC;
    public String sURL;

    EditText et_search,et_oldpwd,et_newpwd;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

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
        iv_shopinfo_shopheader = (ImageView) findViewById(R.id.iv_shopinfo_shopheader);

        iv_shopinfo_shopheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到本地选择图片
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
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

                        if(searchC == null){
                            Toast.makeText(ShopInfoActivity.this, "店铺名称不可为空", Toast.LENGTH_SHORT).show();
                        }else {
                            searchC = et_search.getText().toString();
                            tv_name.setText(searchC);
                        }
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

                        if(searchC == null){
                            Toast.makeText(ShopInfoActivity.this, "手机号码不可为空", Toast.LENGTH_SHORT).show();
                        }else {
                            searchC = et_search.getText().toString();
                            tv_phone.setText(searchC);
                        }
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

                        if(searchC == null){
                            Toast.makeText(ShopInfoActivity.this, "主营项目不可为空", Toast.LENGTH_SHORT).show();
                        }else {
                            searchC = et_search.getText().toString();
                            tv_zhuying.setText(searchC);
                        }
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
                        if(et_newpwd.getText() == null){
                            Toast.makeText(ShopInfoActivity.this, "密码不可为空", Toast.LENGTH_SHORT).show();
                        }
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null!=data){
            Uri selectedImage=data.getData();
            String [] filePathColumn={MediaStore.Images.Media.DATA};
            Cursor cursor=getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
            String picturePath=cursor.getString(columnIndex);
            cursor.close();
            sURL = picturePath;
            iv_shopinfo_shopheader.setVisibility(View.VISIBLE);
            iv_shopinfo_shopheader.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
        else{
            //Toast.makeText(getApplicationContext(), "图片选择异常", Toast.LENGTH_LONG).show();
        }
    }
}
