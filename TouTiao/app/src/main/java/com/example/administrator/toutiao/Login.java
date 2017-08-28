package com.example.administrator.toutiao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.administrator.toutiao.Utils.ImagerLoderUtils;

/**
 * 广告页面
 */
public class Login extends AppCompatActivity {
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
if (msg.what==0){
    Intent intent=new Intent(Login.this,MainActivity.class);
    startActivity(intent);
    finish();
}
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String url="https://link.zhihu.com/?target=https%3A//unsplash.it/400/800/%3Frandom";
        ImageView login= (ImageView) findViewById(R.id.login_iv);
        ImagerLoderUtils.setImageView(url,this,login);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(3000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

}
