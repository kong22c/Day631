package com.example.day631.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day631.R;
import com.example.day631.bean.ProcBean;

import java.util.ArrayList;

public class ProcAdapter extends RecyclerView.Adapter<ProcAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProcBean.DataBean.DatasBean> list;

    public ProcAdapter(Context context, ArrayList<ProcBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_proc, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getEnvelopePic()).into(holder.mIvPic);
        holder.mTvTitle.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvPic;
        TextView mTvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mIvPic = itemView.findViewById(R.id.iv_pic);
            this.mTvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
