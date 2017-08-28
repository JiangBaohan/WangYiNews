package com.example.admin.myqq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.andy.share.QQOauthUtils;
import com.bumptech.glide.Glide;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_view;
    private QQOauthUtils qqOauthUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_view = (ImageView) findViewById(R.id.iv_image);
        qqOauthUtils =new QQOauthUtils();
        iv_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qqOauthUtils=new QQOauthUtils();
                qqOauthUtils.qqLogin(MainActivity.this,new QQOauthUtils.QQCallBack(){
                    @Override
                    public void logsuccess() {

                    }

                    @Override
                    public void getuserinfo(Map<String, String> map) {

                    }

                    @Override
                    public void getUserName(String name) {

                    }

                    @Override
                    public void getImagepath(String url) {
                        Glide.with(MainActivity.this).load(url).into(iv_view);
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        qqOauthUtils.onActivityResult(requestCode, resultCode, data);
    }
}
