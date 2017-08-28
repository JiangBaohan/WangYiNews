package com.example.administrator.toutiao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.toutiao.Bean.Bean01;
import com.example.administrator.toutiao.Utils.ImagerLoderUtils;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * data:2017/8/20
 * author:汉堡(Administrator)
 * function:
 */
public class TuPian_ListviewAdpater extends BaseAdapter {
    private Context context;
    private List<Bean01.ResultBean.DataBean> data;

    public TuPian_ListviewAdpater(Context context, List<Bean01.ResultBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=View.inflate(context,R.layout.tupian_layout,null);
            holder.tv= (TextView) view.findViewById(R.id.tupian_tv);
            holder.iv= (PhotoView) view.findViewById(R.id.tupian_iv);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tv.setText(data.get(i).title);
        ImagerLoderUtils.setImageView(data.get(i).thumbnail_pic_s,context,holder.iv);
holder.iv.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
    @Override
    public void onPhotoTap(View view, float v, float v1) {

    }

    @Override
    public void onOutsidePhotoTap() {

    }
});
        return view;
    }
    class ViewHolder{
        TextView tv;
        PhotoView iv;
    }
}
