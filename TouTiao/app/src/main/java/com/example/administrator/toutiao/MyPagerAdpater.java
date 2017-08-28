package com.example.administrator.toutiao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * data:2017/8/8
 * author:汉堡(Administrator)
 * function:
 */
public class MyPagerAdpater extends FragmentPagerAdapter {
    public MyPagerAdpater(FragmentManager fm) {
        super(fm);
    }
    //传递list
    private List<Fragment> lists;
    public  void  setFragment(List<Fragment> list){
        lists=list;
    }

    @Override//这个就相当于baseAdapter中的getview
    public Fragment getItem(int position) {
        Fragment fragment=lists.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}

