package com.myshops.shops.myshops;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.myshops.shops.myshops.R;

public class MessageFahuoActivity extends Activity {

    private Button btn_back,btn_message,btn_pingjia,btn_kehu,btn_daifukuan,btn_daifahuo,btn_yifahuo,btn_tuikuanzhong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_fahuo);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_message = (Button) findViewById(R.id.btn_message);
        btn_pingjia = (Button) findViewById(R.id.btn_pingjia);
        btn_kehu = (Button) findViewById(R.id.btn_kehu);
        btn_daifukuan = (Button) findViewById(R.id.btn_daifukuan);
        btn_daifahuo = (Button) findViewById(R.id.btn_daifahuo);
        btn_yifahuo = (Button) findViewById(R.id.btn_yifahuo);
        btn_tuikuanzhong = (Button) findViewById(R.id.btn_tuikuanzhong);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageFahuoActivity.this.finish();
            }
        });
    }
}
