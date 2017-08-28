package com.example.administrator.toutiao;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class XlistViewItem extends AppCompatActivity {
    private ActionBarDrawerToggle mToggle;
    private DrawerLayout drawerLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xlist_view_item);
        WebView  web_view = (WebView) findViewById(R.id.web_view);
        String path1 = getIntent().getStringExtra("path");
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        web_view.loadUrl(path1);
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        drawerLayout2= (DrawerLayout) findViewById(R.id.drawerLayout2);
        initActionBar();//初始化ActionBar
    }



    private void initActionBar() {
        //获取一个ActionBar
        ActionBar actionBar = getSupportActionBar();
        //给左上角的左边加一个返回的图标
        actionBar.setDisplayHomeAsUpEnabled(true);
        //这个类提供了一种方便的方式来绑定的功能
        //参数上下文 2.drawerLayout 3.4R.string.资源(照顾盲人,点击时有声音)
        mToggle = new ActionBarDrawerToggle(this, drawerLayout2, R.string.open, R.string.close);
        //将抽屉指示器的状态与连接的drawerlayout同步其状态
        mToggle.syncState();
        drawerLayout2.addDrawerListener(mToggle);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.menu_fenxiang:
               showShare();
                break;
            case R.id.menu_shoucang:
                break;
            case R.id.menu_jieping:

                break;
            case R.id.menu_wenzihao:
                break;
            case R.id.image_actionbar:
                if (drawerLayout2.isDrawerOpen(GravityCompat.START)) {
                    //关闭抽屉
                    drawerLayout2.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout2.openDrawer(GravityCompat.END);
                }

                break;
        }
        if (drawerLayout2.isDrawerOpen(GravityCompat.END)) {
            drawerLayout2.closeDrawer(GravityCompat.END);//关闭抽屉
            return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);

    }

    /**
     * 获取Menu布局资源
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);//这里是调用menu文件夹中的main.xml，在登陆界面label右上角的三角里显示其他功能

        return super.onCreateOptionsMenu(menu);
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
