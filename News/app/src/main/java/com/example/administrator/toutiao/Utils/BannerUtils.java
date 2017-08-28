package com.example.administrator.toutiao.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * data:2017/8/10
 * author:汉堡(Administrator)
 * function:
 */

public class BannerUtils extends ImageLoader{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
