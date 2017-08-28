package com.example.administrator.toutiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.andy.share.QQOauthUtils;
import com.example.administrator.toutiao.Utils.ImagerLoderUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.administrator.toutiao.R.id.drawerlayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TabLayout tabLayout;
    //默认是日间模式
    private int theme = R.style.AppTheme;
    ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ImageView youce_dengru;
    private QQOauthUtils qqOauthUtils;
    private TextView youce_dengruming;
    private ImageView image_qiehuan;
private  List<ChannelBean> list;
    private String jsonStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
        //判断是否有主题存储,必须再setContentView之前
        if (savedInstanceState != null) {
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_main);
        initView();//初始化控件对象
        initActionBar();//初始化ActionBar
        initViewPager();//初始化ViewPager
        qqOauthUtils = new QQOauthUtils();
        image_qiehuan.setOnClickListener(this);
        QQdengru();
    }

    private void QQdengru() {
        youce_dengru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qqOauthUtils = new QQOauthUtils();
                qqOauthUtils.qqLogin(MainActivity.this, new QQOauthUtils.QQCallBack() {
                    @Override
                    public void logsuccess() {
                       Toast.makeText(MainActivity.this, "login...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void getuserinfo(Map<String, String> map) {

                    }

                    @Override
                    public void getUserName(String name) {
                        youce_dengruming.setText(name);
                    }

                    @Override
                    public void getImagepath(String url) {
                        ImagerLoderUtils.setImageView(url, MainActivity.this, youce_dengru);
                    }
                });
            }
        });
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(drawerlayout);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        youce_dengru = (ImageView) findViewById(R.id.youce_dengru);
        youce_dengruming = (TextView) findViewById(R.id.youce_dengruming);
        image_qiehuan = (ImageView) findViewById(R.id.image_qiehuan);
    }

    private void initActionBar() {
        //获取一个ActionBar
        ActionBar actionBar = getSupportActionBar();
        //给左上角的左边加一个返回的图标
        actionBar.setDisplayHomeAsUpEnabled(true);
        //这个类提供了一种方便的方式来绑定的功能
        //参数上下文 2.drawerLayout 3.4R.string.资源(照顾盲人,点击时有声音)
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        //将抽屉指示器的状态与连接的drawerlayout同步其状态
        mToggle.syncState();
        drawerLayout.addDrawerListener(mToggle);
    }

    private void initViewPager() {
        //创建一个集合,用来装fragment
        List<Fragment> fragments = new ArrayList<>();
        //把VR全景图和视频的fragment对象装入集合显示
        fragments.add(new Fragment02());
        fragments.add(new Fragment03());
        fragments.add(new Fragment04());
        fragments.add(new Fragment05());
        fragments.add(new Fragment06());

        //创建viewpager适配器对象
        MyPagerAdpater adpater = new MyPagerAdpater(getSupportFragmentManager());
        //把集合传给适配器
        adpater.setFragment(fragments);
        //viewpager设置适配器
        viewPager.setAdapter(adpater);
//-----------------tabLayout知识---要再build.gradle依赖-android.support.design.widget.TabLayout------------------------
        //tablayout指示器有几个就创建几个
        for (int i = 0; i <5 ; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        /*tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());*/
        //使用tablayout与viewpager相关联
        tabLayout.setupWithViewPager(viewPager);
        //给tablayout指示器是这文本,万物从0开始,0就是给第一个指示器设置的文本

       tabLayout.getTabAt(0).setText("头条");
        tabLayout.getTabAt(1).setText("娱乐");
        tabLayout.getTabAt(2).setText("热点");
        tabLayout.getTabAt(3).setText("体育");
        tabLayout.getTabAt(4).setText("北京");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_tianqi:
                Intent tianqi = new Intent(MainActivity.this, tianqi_activity.class);
                startActivity(tianqi);
                break;
            case R.id.action_lixian:

                break;
            case R.id.action_yejian:
                //三元运算符判断:true执行第一个:false执行第二个
                //三元运算符比if else效率高
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                //重新创建
                recreate();
                break;
            case R.id.action_sousuo:
                Intent sousuo = new Intent(MainActivity.this, SouyiSou_yuyin_activity.class);
                startActivity(sousuo);
                break;
            case R.id.action_saoyisao:
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setCaptureActivity(Saoyisao_activity.class);
                integrator.setPrompt("请扫描二维码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(false); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);//是否保留扫码成功时候的截图
                integrator.initiateScan();
                break;
            case R.id.action_shezhi:
                break;
            case R.id.image_actionbar:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    //关闭抽屉
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.END);
                }

                break;
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);//关闭抽屉
            return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);

    }

    /**
     * 获取Menu布局资源
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);//这里是调用menu文件夹中的main.xml，在登陆界面label右上角的三角里显示其他功能

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 存
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    /* *
      * 取
      * @param savedInstanceState
      */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        qqOauthUtils.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);

        //频道管理
        if (requestCode==ChannelActivity.REQUEST_CODE&&resultCode==ChannelActivity.RESULT_CODE) {
            jsonStr = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_qiehuan:

                if (list == null) {//判断集合中是否已有数据，没有则创建
                    list = new ArrayList<>();
                    //第一个是显示的条目，第二个参数是否显示
                    list.add(new ChannelBean("热点", true));
                    list.add(new ChannelBean("军事", true));
                    list.add(new ChannelBean("八卦", true));
                    list.add(new ChannelBean("游戏", true));
                    list.add(new ChannelBean("宠物", true));
                    list.add(new ChannelBean("汽车", false));
                    list.add(new ChannelBean("热卖", false));
                    list.add(new ChannelBean("外卖", false));
                    list.add(new ChannelBean("奇闻怪谈", false));
                    list.add(new ChannelBean("财经", false));
                    list.add(new ChannelBean("北京", false));
                    list.add(new ChannelBean("娱乐", false));
                    ChannelActivity.startChannelActivity(this, list);
                } else if (jsonStr != null) {//当判断保存的字符串不为空的时候，直接加载已经有了的字符串
                    ChannelActivity.startChannelActivity(this, jsonStr);
                }

                break;
            default:
                break;
        }
    }
}



