package com.example.day6302.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.day6302.R;
import com.example.day6302.bean.ProcBean;

import java.util.ArrayList;

public class VpAdaoter extends PagerAdapter {
    private Context context;
    private ArrayList<ProcBean.DataBean.DatasBean>list;

    public VpAdaoter(Context context, ArrayList<ProcBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ProcBean.DataBean.DatasBean datasBean = list.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_vp, null);
        ImageView iv_vp = view.findViewById(R.id.iv_vp);
        Glide.with(context).load(list.get(position).getEnvelopePic()).into(iv_vp);
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
