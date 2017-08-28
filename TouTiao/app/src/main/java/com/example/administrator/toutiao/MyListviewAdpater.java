package com.example.administrator.toutiao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.toutiao.Bean.Bean01;
import com.example.administrator.toutiao.Utils.BannerUtils;
import com.example.administrator.toutiao.Utils.ImagerLoderUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2017/8/11
 * author:汉堡(Administrator)
 * function:
 */

public class MyListviewAdpater extends BaseAdapter{

        final int FristType = 0;
        final int TwoType = 1;
        final int ThreeType = 2;
        final int FourType = 3;
        private List<String> images;
    private Context context;
    private List<Bean01.ResultBean.DataBean> data;

    public MyListviewAdpater(Context context, List<Bean01.ResultBean.DataBean> data) {
        this.context = context;
        this.data = data;

    }

    @Override
        public int getViewTypeCount() {
            return 4;
        }

        @Override
        public int getItemViewType(int position) {
            if (position ==0) {
                return FourType;
            }
            if (data.get(position).thumbnail_pic_s03 != null) {
                return ThreeType;
            }
            if (data.get(position).thumbnail_pic_s02 != null) {
                return TwoType;
            }
            return FristType;

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
            int type = getItemViewType(i);
            ViewHolder01 holder01 = null;
            ViewHolder02 holder02 = null;
            ViewHolder03 holder03 = null;
            ViewHolder04 holder04 = null;
            if (view == null) {
                switch (type) {
                    case FristType:

                        holder01 = new ViewHolder01();
                        view = View.inflate(context, R.layout.fragment02_holder, null);
                        holder01.holder1_tv1 = (TextView) view.findViewById(R.id.holder1_title);
                        holder01.holder1_tv2 = (TextView) view.findViewById(R.id.holder1_date);
                        holder01.holder1_tv3 = (TextView) view.findViewById(R.id.holder1_author_name);
                        holder01.holder1_iv1 = (ImageView) view.findViewById(R.id.holder1_pic);
                        view.setTag(holder01);
                        break;
                    case TwoType:

                        holder02 = new ViewHolder02();
                        view = View.inflate(context, R.layout.fragment02_holder2, null);
                        holder02.holder2_tv1 = (TextView) view.findViewById(R.id.holder2_title);
                        holder02.holder2_tv2 = (TextView) view.findViewById(R.id.holder2_date);
                        holder02.holder2_tv3 = (TextView) view.findViewById(R.id.holder2_author_name);
                        holder02.holder2_iv1 = (ImageView) view.findViewById(R.id.holder2_pic01);
                        holder02.holder2_iv2 = (ImageView) view.findViewById(R.id.holder2_pic02);
                        view.setTag(holder02);
                        break;
                    case ThreeType:

                        holder03 = new ViewHolder03();
                        view = View.inflate(context, R.layout.fragment02_holder3, null);
                        holder03.holder3_tv1 = (TextView) view.findViewById(R.id.holder3_title);
                        holder03.holder3_tv2 = (TextView) view.findViewById(R.id.holder3_author_name);
                        holder03.holder3_iv1 = (ImageView) view.findViewById(R.id.holder3_pic01);
                        holder03.holder3_iv2 = (ImageView) view.findViewById(R.id.holder3_pic02);
                        holder03.holder3_iv3 = (ImageView) view.findViewById(R.id.holder3_pic03);
                        view.setTag(holder03);
                        break;
                    case FourType:

                        holder04 = new ViewHolder04();
                        view = View.inflate(context, R.layout.fragment02_holder4, null);
                        holder04.banner = (Banner) view.findViewById(R.id.holder4_banner);
                        String pic_s1 = data.get(i).thumbnail_pic_s;
                        String pic_s2 = data.get(i).thumbnail_pic_s02;
                        String pic_s3 = data.get(i).thumbnail_pic_s03;
                        images = new ArrayList<>();
                        images.add(pic_s1);
                        images.add(pic_s2);
                        images.add(pic_s3);
                        view.setTag(holder04);
                        break;
                    default:
                        break;
                }
            } else {
                switch (type) {
                    case FristType:
                        holder01 = (ViewHolder01) view.getTag();
                        break;
                    case TwoType:
                        holder02 = (ViewHolder02) view.getTag();
                        break;
                    case ThreeType:
                        holder03 = (ViewHolder03) view.getTag();
                        break;
                    case FourType:
                        holder04 = (ViewHolder04) view.getTag();
                    default:
                        break;
                }
            }
            switch (type) {
                case FristType:
                    holder01.holder1_tv1.setText(data.get(i).title);
                    holder01.holder1_tv2.setText(data.get(i).date);
                    holder01.holder1_tv3.setText(data.get(i).author_name);
                    ImagerLoderUtils.setImageView(data.get(i).thumbnail_pic_s,context, holder01.holder1_iv1);
                    break;
                case TwoType:
                    holder02.holder2_tv1.setText(data.get(i).title);
                    holder02.holder2_tv2.setText(data.get(i).date);
                    holder02.holder2_tv3.setText(data.get(i).author_name);
                    ImagerLoderUtils.setImageView(data.get(i).thumbnail_pic_s, context, holder02.holder2_iv1);
                    ImagerLoderUtils.setImageView(data.get(i).thumbnail_pic_s, context, holder02.holder2_iv2);
                    break;
                case ThreeType:
                    holder03.holder3_tv1.setText(data.get(i).title);
                    holder03.holder3_tv2.setText(data.get(i).author_name);
                    ImagerLoderUtils.setImageView(data.get(i).thumbnail_pic_s, context, holder03.holder3_iv1);
                    ImagerLoderUtils.setImageView(data.get(i).thumbnail_pic_s, context, holder03.holder3_iv2);
                    ImagerLoderUtils.setImageView(data.get(i).thumbnail_pic_s, context, holder03.holder3_iv3);
                    break;
                case FourType:

                    holder04.banner.setImageLoader(new BannerUtils())  //这个
                            .setImages(images) //这个
                            .isAutoPlay(true)
                            .start();
                    break;
                default:
                    break;
            }

            return view;
        }

        class ViewHolder01 {
            TextView holder1_tv1, holder1_tv2, holder1_tv3;
            ImageView holder1_iv1;
        }

        class ViewHolder02 {
            TextView holder2_tv1, holder2_tv2, holder2_tv3;
            ImageView holder2_iv1;
            ImageView holder2_iv2;
        }

        class ViewHolder03 {
            TextView holder3_tv1, holder3_tv2;
            ImageView holder3_iv1;
            ImageView holder3_iv2;
            ImageView holder3_iv3;

        }

        class ViewHolder04 {

            Banner banner;
        }


}
