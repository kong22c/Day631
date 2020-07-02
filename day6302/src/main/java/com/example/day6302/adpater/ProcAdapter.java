package com.example.day6302.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day6302.R;
import com.example.day6302.bean.ProcBean;

import java.util.ArrayList;

public class ProcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ProcBean.DataBean.DatasBean> list;
    private int VIEW_TYPE_ONE = 1;
    private int VIEW_TYPE_TWO = 2;

    @Override
    public int getItemViewType(int position) {
        if (position % 3 != 0) {
            return VIEW_TYPE_ONE;
        } else {
            return VIEW_TYPE_TWO;
        }
    }

    public ProcAdapter(Context context, ArrayList<ProcBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ONE) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_proc1, null);
            return new ViewHolder1(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_proc2, null);
            return new ViewHolder2(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == VIEW_TYPE_ONE) {
            ViewHolder1 holder1 = (ViewHolder1) holder;
            Glide.with(context).load(list.get(position).getEnvelopePic()).into(holder1.mIvPic);
            holder1.mTvTitle.setText(list.get(position).getTitle());
            holder1.mTvId.setText("id:"+list.get(position).getId()+"");
        }else {
            ViewHolder2 holder2 = (ViewHolder2) holder;
            Glide.with(context).load(list.get(position).getEnvelopePic()).into(holder2.mIvPic);
            holder2.mTvTitle.setText(list.get(position).getTitle());
            holder2.mTvId.setText("id:"+list.get(position).getId()+"");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickLister.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView mIvPic;
        TextView mTvTitle;
        TextView mTvId;

        public ViewHolder1(View inflate) {
            super(inflate);
            this.mIvPic = itemView.findViewById(R.id.iv_pic);
            this.mTvTitle = itemView.findViewById(R.id.tv_title);
            this.mTvId = itemView.findViewById(R.id.tv_id);
        }
    }

    private class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView mIvPic;
        TextView mTvTitle;
        TextView mTvId;
        public ViewHolder2(View inflate) {
            super(inflate);
            this.mIvPic = itemView.findViewById(R.id.iv_pic);
            this.mTvTitle = itemView.findViewById(R.id.tv_title);
            this.mTvId = itemView.findViewById(R.id.tv_id);
        }
    }
    private OnItemClickLister onItemClickLister;

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public interface OnItemClickLister{
        void onItemClick(int position);
    }
}
