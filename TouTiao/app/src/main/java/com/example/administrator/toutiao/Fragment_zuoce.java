package com.example.administrator.toutiao;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.administrator.toutiao.R.id.drawerlayout;

public class Fragment_zuoce extends Fragment {
    private List<Integer> list;
    private Fragment02 fragment2;
    private Dingyue_Fragment fragment_dinyue;
    private TuPian_Fragment fragment_tupian;
    private ShiPin_Fragment fragment_shipin;
    private GenTie_Fragment fragment_gentie;
    private ViewHolder holder;
    private DrawerLayout drawerLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment01, container, false);
        drawerLayout = (DrawerLayout)view.findViewById(drawerlayout);
        ListView list_zuoce = (ListView) view.findViewById(R.id.list_zuoce);
        list_zuoce.setDivider(null);
        list_zuoce.setDividerHeight(25);
        list_zuoce.setAdapter(new zuoceAdapter());
        fragment2 = new Fragment02();
        fragment_dinyue = new Dingyue_Fragment();
        fragment_tupian = new TuPian_Fragment();
        fragment_shipin = new ShiPin_Fragment();
        fragment_gentie = new GenTie_Fragment();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_activity, fragment_gentie).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_activity, fragment_shipin).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_activity, fragment_tupian).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_activity, fragment_dinyue).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_activity, fragment2).commit();
        getActivity().getSupportFragmentManager().beginTransaction().hide(fragment2).hide(fragment_dinyue).hide(fragment_shipin).hide(fragment_tupian).hide(fragment_gentie).commit();
        holder = new ViewHolder();

        list_zuoce.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        getActivity().getSupportFragmentManager().beginTransaction().hide(fragment2).hide(fragment_dinyue).hide(fragment_shipin).hide(fragment_tupian).hide(fragment_gentie).commit();
                        /*holder.tv.setTextColor(Color.parseColor("#ADFF2F"));
                        holder.tv.setTextColor(Color.BLACK);
                        holder.tv.setTextColor(Color.BLACK);
                        holder.tv.setTextColor(Color.BLACK);
                        holder.tv.setTextColor(Color.BLACK);*/
                        Toast.makeText(getActivity(), "新闻", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        getActivity().getSupportFragmentManager().beginTransaction().show(fragment_dinyue).hide(fragment2).hide(fragment_shipin).hide(fragment_tupian).hide(fragment_gentie).commit();
                        Toast.makeText(getActivity(), "订阅", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        getActivity().getSupportFragmentManager().beginTransaction().show(fragment_tupian).hide(fragment_dinyue).hide(fragment_shipin).hide(fragment2).hide(fragment_gentie).commit();

                        Toast.makeText(getActivity(), "图片", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        getActivity().getSupportFragmentManager().beginTransaction().show(fragment_shipin).hide(fragment_dinyue).hide(fragment2).hide(fragment_tupian).hide(fragment_gentie).commit();

                        Toast.makeText(getActivity(), "视频", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        getActivity().getSupportFragmentManager().beginTransaction().show(fragment_gentie).hide(fragment_dinyue).hide(fragment_shipin).hide(fragment_tupian).hide(fragment2).commit();

                        Toast.makeText(getActivity(), "跟帖", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.image_actionbar:
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                            //关闭抽屉
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else {
                            drawerLayout.openDrawer(GravityCompat.END);
                        }

                        break;
                    default:
                        break;
                }

            }
        });
        return view;
    }

    private class zuoceAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 5;
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
            ViewHolder holder = null;
            if (view == null) {
                holder = new ViewHolder();
                view = View.inflate(getActivity(), R.layout.list_zuoce, null);
                holder.iv = (ImageView) view.findViewById(R.id.image_zuoce);
                holder.tv = (TextView) view.findViewById(R.id.tv_zuoce);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            switch (i) {
                case 0:
                    holder.iv.setImageResource(R.drawable.xinwen);
                    holder.tv.setText("新闻");
                    break;
                case 1:
                    holder.iv.setImageResource(R.drawable.dingyue);
                    holder.tv.setText("订阅");
                    break;
                case 2:
                    holder.iv.setImageResource(R.drawable.tupian);
                    holder.tv.setText("图片");
                    break;
                case 3:
                    holder.iv.setImageResource(R.drawable.shipin);
                    holder.tv.setText("视频");
                    break;
                case 4:
                    holder.iv.setImageResource(R.drawable.gentie);
                    holder.tv.setText("跟帖");
                    break;

                default:
                    break;
            }
            return view;
        }
    }

    class ViewHolder {
        ImageView iv;
        TextView tv;
    }
}
