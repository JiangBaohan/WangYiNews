package com.example.administrator.toutiao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.administrator.toutiao.Bean.Bean01;
import com.google.gson.Gson;
import com.sn.xlistviewlibrary.XListView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * data:2017/8/20
 * author:汉堡(Administrator)
 * function:
 */
public class TuPian_Fragment extends Fragment {
    private String str;
    private List<Bean01.ResultBean.DataBean> data;




    private String path = "http://v.juhe.cn/toutiao/index?type=top&key=e76b62dbe5ce78645516fe866dc7058b";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Gson gson = new Gson();
                    Bean01 bean01 = gson.fromJson(str, Bean01.class);
                    data = bean01.result.data;
                    xListView.setAdapter(new TuPian_ListviewAdpater(getActivity(), data));
                    break;

                default:
                    break;
            }
        }
    };

    private XListView xListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pupian, container, false);
            xListView = (XListView) view.findViewById(R.id.tupian_xlist);
            xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String url = data.get(i).url;
                    Intent intent=new Intent(getActivity(),XlistViewItem.class);
                    intent.putExtra("path",url);
                    startActivity(intent);
                }
            });
            new Thread() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(path);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        //设置请求模式
                        urlConnection.setRequestMethod("GET");
                        //设置连接的超时时间，单位毫秒
                        urlConnection.setConnectTimeout(5000);
                        //设置读取的超时时间
                        urlConnection.setReadTimeout(5000);
                        //同步请求
                        int code = urlConnection.getResponseCode();
                        if (code == 200) {
                            InputStream inputStream = urlConnection.getInputStream();
                            //字节流转换成字符串
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            int ch = -1;
                            byte[] buffer = new byte[1024 * 4];
                            while ((ch = inputStream.read(buffer)) != -1) {
                                baos.write(buffer, 0, ch);
                            }
                            baos.flush();
                            str = baos.toString();

                            handler.sendEmptyMessage(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            return view;


        }

    }


