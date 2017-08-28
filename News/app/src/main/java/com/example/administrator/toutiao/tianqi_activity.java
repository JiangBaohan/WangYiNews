package com.example.administrator.toutiao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class tianqi_activity extends AppCompatActivity {
private String path="http://www.weather.com.cn/weather/101010100.shtml";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianqi_activity);
        WebView tianqi_webview= (WebView) findViewById(R.id.tianqi_webview);
        WebSettings settings = tianqi_webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置webview的默认缩放
        settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        // 支持出现放大缩小工具,拖动界面会出现，并且超出界面显示区域会出现。
        settings.setBuiltInZoomControls(true);
        settings.setPluginState(WebSettings.PluginState.ON);//状态
        tianqi_webview.setWebChromeClient(new WebChromeClient());
        tianqi_webview.loadUrl(path);
        tianqi_webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}
